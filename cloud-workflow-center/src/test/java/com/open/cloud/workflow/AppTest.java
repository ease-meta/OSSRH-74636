package com.open.cloud.workflow;

import org.jasypt.util.text.BasicTextEncryptor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {
    public static void main(String[] args) {
        //ENC()
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        //加密所需的salt(盐)
        textEncryptor.setPassword("G0CvDz7oJn6");

        System.out.println(textEncryptor.encrypt("J+fBfVAugFNpZhbLBhnewsjQvQf0IVlDy7/VhJzSxhk="));
        //return;

        /*String filePath = "E:\\dcwork\\tianjin\\一期工程\\05-系统建设组\\01-新核心建设工程组\\01-分布式存款核心及会计核算引擎\\硬件资源申请\\天津银行_明文密码和密码密文比对文档.xlsx";
        List<Object> objects = ExcelUtil.readMoreThan1000Row(filePath);
        List<TableHeaderExcelProperty> list = new LinkedList<TableHeaderExcelProperty>();
       a: for (Object obj : objects) {
            TableHeaderExcelProperty tableHeaderExcelProperty = new TableHeaderExcelProperty();
            ArrayList arrayList = (ArrayList) obj;
            if (arrayList.size() == 2) {
                if (!"明文密码".equalsIgnoreCase((String) arrayList.get(0))) {
                    tableHeaderExcelProperty.setPassword1((String) arrayList.get(0));
                    tableHeaderExcelProperty.setPassword2((String) arrayList.get(1));
                    for (TableHeaderExcelProperty tableHeaderExcelProperty1 : list) {
                        if (((String) arrayList.get(0)).equals(tableHeaderExcelProperty1.getPassword1())) {
                            continue a;
                        }
                    }
                    list.add(tableHeaderExcelProperty);
                }
            } else if (arrayList.size() == 1) {
                tableHeaderExcelProperty.setPassword1((String) arrayList.get(0));
                tableHeaderExcelProperty.setPassword2("ENC(" + textEncryptor.encrypt((String) arrayList.get(0)) + ")");
                for (TableHeaderExcelProperty tableHeaderExcelProperty1 : list) {
                    if (((String) arrayList.get(0)).equals(tableHeaderExcelProperty1.getPassword1())) {
                        continue a;
                    }
                }
                list.add(tableHeaderExcelProperty);
            }
        }
        list.forEach(System.out::println);
        ExcelUtil.writeWithTemplate(filePath, list);*/
    }


}
