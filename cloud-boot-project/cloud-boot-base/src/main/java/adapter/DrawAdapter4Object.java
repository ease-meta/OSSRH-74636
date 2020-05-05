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
/**
 * @author shadow
 * @Date 2016年8月1日下午8:04:42
 * @Fun 对象适配器： 使用对象组合的方式，是动态组合的方式。
 * 既能画方又能画圆。DrawAdapter是适配器，DrawRectangle属于Adapter，是被适配者，
 * 适配器将被适配者和适配目标（DrawCircle）进行适配。
 **/
package adapter;

public class DrawAdapter4Object implements IDrawCircle {

    private DrawRectangle drawRectangle;

    public DrawAdapter4Object(DrawRectangle drawRectangle) {
        // TODO Auto-generated constructor stub
        this.drawRectangle = drawRectangle;
    }

    @Override
    public void drawCircle() {
        // TODO Auto-generated method stub
        System.out.println("DrawAdapter4Object:drawCircle");
    }

    public void drawRectangle(String msg) {
        drawRectangle.drawRectangle(msg);
    }

}
