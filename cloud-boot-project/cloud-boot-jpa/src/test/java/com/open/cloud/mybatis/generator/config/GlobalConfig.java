package com.open.cloud.mybatis.generator.config;


/**
 * ȫ������
 *
 * @author hubin
 * @since 2016-12-02
 */
public class GlobalConfig {

    /**
     * �����ļ������Ŀ¼�� windows:D://  linux or mac:/tmp ��
     */
    private String outputDir = System.getProperty("os.name").toLowerCase().contains("windows") ? "D://" : "/tmp";
    /**
     * �Ƿ񸲸������ļ���Ĭ�� false��
     */
    private boolean fileOverride;
    /**
     * ����
     */
    private String author = "����";

    private GlobalConfig() {
    }

    public String getOutputDir() {
        return outputDir;
    }

    public boolean isFileOverride() {
        return fileOverride;
    }


    public String getAuthor() {
        return author;
    }


    public static final class GlobalConfigBuilder {
        private String outputDir = System.getProperty("os.name").toLowerCase().contains("windows") ? "D://" : "/tmp";
        private boolean fileOverride;
        private String author = "����";

        private GlobalConfigBuilder() {
        }

        public static GlobalConfigBuilder GlobalConfig() {
            return new GlobalConfigBuilder();
        }

        public GlobalConfigBuilder withOutputDir(String outputDir) {
            this.outputDir = outputDir;
            return this;
        }

        public GlobalConfigBuilder withFileOverride(boolean fileOverride) {
            this.fileOverride = fileOverride;
            return this;
        }

        public GlobalConfigBuilder withAuthor(String author) {
            this.author = author;
            return this;
        }

        public GlobalConfig build() {
            GlobalConfig globalConfig = new GlobalConfig();
            globalConfig.outputDir = this.outputDir;
            globalConfig.fileOverride = this.fileOverride;
            globalConfig.author = this.author;
            return globalConfig;
        }
    }
}
