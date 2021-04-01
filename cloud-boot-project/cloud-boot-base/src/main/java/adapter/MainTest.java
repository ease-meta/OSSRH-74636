/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package adapter;

/**
 * @author shadow
 * @Date 2016年8月1日下午8:01:07
 * @Fun 适配器：对象适配器、类适配器、接口适配器
 **/
public class MainTest {
    public static void main(String[] args) {
        //对象适配器
        DrawAdapter4Object objAdapter = new DrawAdapter4Object(new DrawRectangle());
        objAdapter.drawCircle();
        objAdapter.drawRectangle(" in DrawAdapter4Object");

        //类适配器
        DrawAdapter4Class adapter4 = new DrawAdapter4Class();
        adapter4.drawCircle();
        adapter4.drawRectangle(" in DrawAdapter4Class ");

        //接口适配器
        MyDrawAdapter adapter = new MyDrawAdapter();
        adapter.drawCircle();
        adapter.drawRectangle();
    }

    static class MyDrawAdapter extends DefaultDrawAdapter {
        @Override
        public void drawCircle() {
            // TODO Auto-generated method stub
            System.out.println("My DrawAdapter:Draw CirCle");
        }

        @Override
        public void drawRectangle() {
            // TODO Auto-generated method stub
            super.drawRectangle();
            System.out.println("My DrawAdapter:Draw Rectangle");
        }
    }
}
/**
 * 参考博客： http://blog.csdn.net/jason0539/article/details/22468457
 **/
