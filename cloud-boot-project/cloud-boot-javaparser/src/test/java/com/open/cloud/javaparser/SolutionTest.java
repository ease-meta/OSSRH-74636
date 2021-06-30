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
        // parse() ���������� String, File, InputStream��
        CompilationUnit compilationUnit = StaticJavaParser.parse(new File("D:\\IdeaProjects\\open-cloud-platform\\cloud-boot-project\\cloud-boot-javaparser\\src\\main\\java\\com\\open\\cloud\\javaparser\\Solution.java"));
        //Ȼ�������Խ�import�����ӵ����뵥Ԫ���˴���
        compilationUnit.addImport("org.springframework.boot.SpringApplication");
        //�����Խ�package�����ӵ����뵥Ԫ��������ʾ��
        //compilationUnit.setPackageDeclaration("com.abc.def");
        //���Ҫ���༶�����ע�ͣ�����ʹ�����´��룺
        //classDeclaration.addAnnotation("AnyAnnotation");

        //����������������������ӷ���������������ʾ��
        //
        //MethodDeclaration methodDeclaration = classDeclaration.addMethod("anyMethodName", PUBLIC);
        //
        //methodDeclaration.setType("AnyReturnType");

        List<MethodDeclaration> mds = compilationUnit.findAll(MethodDeclaration.class);
        mds.forEach(md -> System.out.println(md.toString() + "\n------------------------------\n"));
    }


}