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
package com.open.cloud.core.xml;

import java.util.*;

public final class XMLCommonData implements CommonData {
    private String name;
    private String value;
    private List children;
    private Map attributes;
    private CommonData parent;

    private void init() {
        this.attributes = new HashMap(5);
    }

    public XMLCommonData() {
        this.name = "$ROOT$";

        init();
    }

    private XMLCommonData(String name, CommonData parent) {
        init();

        this.name = name;

        this.parent = parent;
    }

    public String getName() {
        return this.name;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object value) {
        //this.value = ((String)value);
        this.value = value.toString();
    }

    public Object getAttribute(String name) {
        return this.attributes.get(name);
    }

    public void setAttribute(String name, Object value) {
        this.attributes.put(name, value);
    }

    public Iterator attributeNames() {
        return this.attributes.keySet().iterator();
    }

    public CommonData addChild(String name) {
        CommonData data = new XMLCommonData(name, this);
        if (this.children == null) {
            this.children = new ArrayList(5);
        }
        this.children.add(data);

        return data;
    }

    public CommonData getChild(String name) {
        if (this.children == null) {
            return null;
        }
        int count = this.children.size();
        for (int i = 0; i < count; i++) {
            CommonData child = (CommonData) this.children.get(i);
            if (child.getName().equals(name)) {
                return child;
            }
        }
        return null;
    }

    public CommonData getChild(int index) {
        if (this.children == null) {
            return null;
        }
        return (CommonData) this.children.get(index);
    }

    public int childCount() {
        if (this.children == null) {
            return 0;
        }
        return this.children.size();
    }

    public CommonData getParent() {
        return this.parent;
    }
}
