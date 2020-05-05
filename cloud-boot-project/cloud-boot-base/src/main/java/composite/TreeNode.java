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
package composite;

import java.util.LinkedList;
import java.util.List;

/**
 * @author shadow
 * @Date 2016年8月4日下午7:59:06
 * @Fun 树节点 部分
 * 也可以自身代表树：一堆节点组成了一颗树
 **/
public class TreeNode {
    private String         name;
    private TreeNode       parent;
    private List<TreeNode> children;

    public TreeNode(String name) {
        // TODO Auto-generated constructor stub
        this.name = name;
        this.children = new LinkedList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getParent() {
        return this.parent;
    }

    public List<TreeNode> getChildren() {
        return this.children;
    }

    public void add(TreeNode node) {
        this.children.add(node);
    }

    public void remove(TreeNode node) {
        this.children.remove(node);
    }
}
