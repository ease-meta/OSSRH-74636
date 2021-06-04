package com.open.cloud.maven;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

/**
 * 上传依赖到 Maven 私服
 *
 * @author liuzenghui
 * @since 2017/7/31.
 */
public class Deploy {
    /**
     * mvn -s F:\.m2\settings.xml
     * org.apache.maven.plugins:maven-deploy-plugin:2.8.2:deploy-file
     * -Durl=http://IP:PORT/nexus/content/repositories/thirdpart
     * -DrepositoryId=thirdpart
     * -Dfile=antlr-2.7.2.jar
     * -DpomFile=antlr-2.7.2.pom
     * -Dpackaging=jar
     * -DgeneratePom=false
     * -Dsources=./path/to/artifact-name-1.0-sources.jar
     * -Djavadoc=./path/to/artifact-name-1.0-javadoc.jar
     */
    public static final String BASE_CMD = "cmd /c mvn " +
            "-s D:\\work-tools\\apache-maven-3.8.1\\conf\\settings.xml " +
            "deploy:deploy-file " +
            "-Durl=http://10.7.19.173:8081/nexus/content/repositories/Galaxy3-release/ " +
            "-DrepositoryId=Galaxy3-release " +
            "-DgeneratePom=false";

    public static final Pattern DATE_PATTERN = Pattern.compile("-[\\d]{8}\\.[\\d]{6}-");

    public static final Runtime CMD = Runtime.getRuntime();

    public static final Writer ERROR;

    public static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(10);

    static {
        Writer err = null;
        try {
            err = new OutputStreamWriter(new FileOutputStream("deploy-error.log"), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        ERROR = err;
    }

    //mvn -s D:\work-tools\apache-maven-3.8.1\conf\settings.xml deploy:deploy-file -Durl=http://10.7.19.173:8081/nexus/content/repositories/Galaxy3-release/ -DrepositoryId=Galaxy3-release -DgeneratePom=false -DpomFile=D:\dcits\botj\dcits\comet-adapter\3.3.8\comet-adapter-3.3.8.pom -Dfile=D:\dcits\botj\dcits\comet-adapter\3.3.8\comet-adapter-3.3.8.pom
    public static void main(String[] args) {
        deploy(Objects.requireNonNull(new File("D:\\dcits\\botj\\dcits").listFiles()));
//        if(checkArgs(args)){
//            File file = new File(args[0]);
//            deploy(file.listFiles());
//        }
        EXECUTOR_SERVICE.shutdown();
        try {
            ERROR.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void error(String error) {
        try {
            System.err.println(error);
            ERROR.write(error + "\n");
            ERROR.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkArgs(String[] args) {
        if (args.length != 1) {
            System.out.println("用法如： java -jar Deploy D:\\some\\path\\");
            return false;
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            System.out.println(args[0] + " 目录不存在!");
            return false;
        }
        if (!file.isDirectory()) {
            System.out.println("必须指定为目录!");
            return false;
        }
        return true;
    }

    public static void deploy(File[] files) {
        if (files.length == 0) {
            //ignore
        } else if (files[0].isDirectory()) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deploy(file.listFiles());
                }
            }
        } else if (files[0].isFile()) {
            File pom = null;
            File jar = null;
            File source = null;
            File javadoc = null;
            //忽略日期快照版本，如 xxx-mySql-2.2.6-20170714.095105-1.jar
            for (File file : files) {
                String name = file.getName();
                String path = file.getAbsolutePath();
                if (DATE_PATTERN.matcher(name).find()) {
                    //skip
                } else if (name.contains("SNAPSHOT") || name.contains("snapshot")) {
                    //skip
                } else if (name.endsWith(".pom")) {
                    pom = file;
                } else if (name.endsWith("-javadoc.jar")) {
                    javadoc = file;
                } else if (name.endsWith("-sources.jar")) {
                    source = file;
                } else if (name.endsWith(".jar")) {
                    jar = file;
                }
            }
            if (pom != null) {
                if (jar != null) {
                    deploy(pom, jar, source, javadoc);
                } else if (packingIsPom(pom)) {
                    deployPom(pom);
                }
            }
        }
    }

    public static boolean packingIsPom(File pom) {
        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(new FileInputStream(pom)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().contains("<packaging>pom</packaging>")) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void deployPom(final File pom) {
        EXECUTOR_SERVICE.execute(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                StringBuffer cmd = new StringBuffer(BASE_CMD);
                cmd.append(" -DpomFile=").append(pom.getAbsolutePath());
                cmd.append(" -Dfile=").append(pom.getAbsolutePath());
                cmd.append(" -Dpackaging=pom");
                InputStream inputStream = null;
                InputStreamReader inputStreamReader = null;
                BufferedReader reader = null;
                try {
                    final Process proc = CMD.exec(cmd.toString(), null, pom.getParentFile());
                    inputStream = proc.getInputStream();
                    inputStreamReader = new InputStreamReader(inputStream);
                    reader = new BufferedReader(inputStreamReader);
                    String line;
                    StringBuffer logBuffer = new StringBuffer();
                    logBuffer.append("\n\n\n==================================\n");
                    while ((line = reader.readLine()) != null) {
                        //if (line.startsWith("[INFO]") || line.startsWith("Upload")) {
                        logBuffer.append(Thread.currentThread().getName() + " : " + line + "\n");
                        // }
                    }
                    System.out.println(logBuffer);
                    int result = proc.waitFor();
                    if (result != 0) {
                        error("上传失败：" + pom.getAbsolutePath());
                    }
                } catch (IOException e) {
                    error("上传失败：" + pom.getAbsolutePath());
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    error("上传失败：" + pom.getAbsolutePath());
                    e.printStackTrace();
                } finally {
                    reader.close();
                    inputStreamReader.close();
                    inputStream.close();
                }
            }
        });
    }

    public static void deploy(final File pom, final File jar, final File source, final File javadoc) {
        EXECUTOR_SERVICE.execute(new Runnable() {
            @Override
            public void run() {
                StringBuffer cmd = new StringBuffer(BASE_CMD);
                cmd.append(" -DpomFile=").append(pom.getAbsolutePath());
                if (jar != null) {
                    //当有bundle类型时，下面的配置可以保证上传的jar包后缀为.jar
                    cmd.append(" -Dpackaging=jar -Dfile=").append(jar.getAbsolutePath());
                } else {
                    cmd.append(" -Dfile=").append(pom.getAbsolutePath());
                }
                if (source != null) {
                    cmd.append(" -Dsources=").append(source.getAbsolutePath());
                }
                if (javadoc != null) {
                    cmd.append(" -Djavadoc=").append(javadoc.getAbsolutePath());
                }

                try {
                    final Process proc = CMD.exec(cmd.toString(), null, pom.getParentFile());
                    InputStream inputStream = proc.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    String line;
                    StringBuffer logBuffer = new StringBuffer();
                    logBuffer.append("\n\n\n=======================================\n");
                    while ((line = reader.readLine()) != null) {
                        if (line.startsWith("[INFO]") || line.startsWith("Upload")) {
                            logBuffer.append(Thread.currentThread().getName() + " : " + line + "\n");
                        }
                    }
                    System.out.println(logBuffer);
                    int result = proc.waitFor();
                    if (result != 0) {
                        error("上传失败：" + pom.getAbsolutePath());
                    }
                } catch (IOException | InterruptedException e) {
                    error("上传失败：" + pom.getAbsolutePath());
                    e.printStackTrace();
                }
            }
        });
    }
}
