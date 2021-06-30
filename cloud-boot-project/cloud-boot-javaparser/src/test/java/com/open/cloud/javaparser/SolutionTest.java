package com.open.cloud.javaparser;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/6/30 17:25
 */
public class SolutionTest {

    public static void main(String[] args) throws FileNotFoundException {
        // parse() 参数可以是 String, File, InputStream等
        CompilationUnit compilationUnit = StaticJavaParser.parse(new File("D:\\IdeaProjects\\open-cloud-platform\\cloud-boot-project\\cloud-boot-javaparser\\src\\main\\java\\com\\open\\cloud\\javaparser\\Solution.java"));
        //然后，您可以将import语句添加到编译单元，此处：
        compilationUnit.addImport("org.springframework.boot.SpringApplication");
        //您可以将package语句添加到编译单元，如下所示：
        //compilationUnit.setPackageDeclaration("com.abc.def");
        //如果要在类级别添加注释，可以使用以下代码：
        //classDeclaration.addAnnotation("AnyAnnotation");

        //您可以在新声明的类中添加方法声明，如下所示：
        //
        //MethodDeclaration methodDeclaration = classDeclaration.addMethod("anyMethodName", PUBLIC);
        //
        //methodDeclaration.setType("AnyReturnType");

        List<MethodDeclaration> mds = compilationUnit.findAll(MethodDeclaration.class);
        mds.forEach(md -> System.out.println(md.toString() + "\n------------------------------\n"));
    }


}