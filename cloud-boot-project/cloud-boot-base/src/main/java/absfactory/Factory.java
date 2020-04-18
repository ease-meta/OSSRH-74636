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
package absfactory;

import com.leijian.moc.SubClass1;
import com.leijian.moc.SubClass2;

/**
 * @author shadow
 * @Date 2016年8月18日下午7:07:15
 * @Fun 实体工厂 创建实体产品，返回类型为抽象产品
 **/
public class Factory implements IFactory {

	@Override
	public SubClass1 createSubClass1() {
		return null;
	}

	@Override
	public SubClass2 createSubClass2() {
		return null;
	}

	@Override
	public IProduct1 createProduct1A() {
		// TODO Auto-generated method stub
		return new GradeProduct1A();
	}

	@Override
	public IProduct1 createProduct1B() {
		// TODO Auto-generated method stub
		return new GradeProduct1B();
	}

	@Override
	public IProduct2 createProduct2A() {
		// TODO Auto-generated method stub
		return new GradeProduct2A();
	}

	@Override
	public IProduct2 createProduct2B() {
		// TODO Auto-generated method stub
		return new GradeProduct2B();
	}

}
