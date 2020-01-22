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
package state;

/**
 * @author shadow
 * @Date 2016年8月12日下午8:34:09
 * @Fun
 **/
public class WindowState {
    private String stateValue;

    public WindowState(String stateValue) {
        this.stateValue = stateValue;
    }

    public String getStateValue() {
        return this.stateValue;
    }

    public void setStateValue(String stateValue) {
        this.stateValue = stateValue;
    }

    public void handle() {
        if ("窗口".equals(stateValue)) {
            switchWindow();
            this.stateValue = "全屏";
        } else if ("全屏".equals(stateValue)) {
            switchFullscreen();
            this.stateValue = "窗口";
        }
    }

    private void switchWindow() {
        System.out.println("切换为窗口状态");
    }

    private void switchFullscreen() {
        System.out.println("切换为全屏状态");
    }
}
