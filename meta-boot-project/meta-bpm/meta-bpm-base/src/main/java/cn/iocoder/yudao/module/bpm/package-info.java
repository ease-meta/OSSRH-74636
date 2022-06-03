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
 * bpm 包下，业务流程管理（Business Process Management），我们放工作流的功能。
 * 例如说：流程定义、表单配置、审核中心（我的申请、我的待办、我的已办）等等
 * <p>
 * bpm 解释：https://baike.baidu.com/item/BPM/1933
 * <p>
 * 1. Controller URL：以 /bpm/ 开头，避免和其它 Module 冲突
 * 2. DataObject 表名：以 bpm_ 开头，方便在数据库中区分
 */
package cn.iocoder.yudao.module.bpm;

