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
package com.open.cloud.tools.util;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;

public class JacksonUtilsTest {
	String string = "{\n" + "  \"appHead\" : {\n" + "    \"currentNum\" : \"0\",\n"
			+ "    \"pageEnd\" : \"0\",\n" + "    \"pageStart\" : \"0\",\n"
			+ "    \"pgupOrPgdn\" : \"0\",\n" + "    \"totalNum\" : \"-1\"\n" + "  },\n"
			+ "  \"body\" : {\n" + "    \"baseAcctNo\" : \"24000200000006048\"\n"
			+ "  },\n" + "  \"localHead\" : { },\n" + "  \"sysHead\" : {\n"
			+ "    \"apprFlag\" : \"\",\n" + "    \"apprUserId\" : \"\",\n"
			+ "    \"authFlag\" : \"N\",\n" + "    \"authPassword\" : \"\",\n"
			+ "    \"authUserId\" : \"\",\n" + "    \"branchId\" : \"2000\",\n"
			+ "    \"destBranchNo\" : \"2000\",\n" + "    \"messageType\" : \"1400\",\n"
			+ "    \"messageCode\" : \"2101\",\n" + "    \"moduleId\" : \"CL\",\n"
			+ "    \"programId\" : \"4100\",\n" + "    \"reversalTranType\" : \"\",\n"
			+ "    \"sceneId\" : \"01\",\n"
			+ "    \"seqNo\" : \"68f728852cc36723142306371\",\n"
			+ "    \"serverId\" : \"192.168.161.156\",\n"
			+ "    \"serviceCode\" : \"MbsdCore\",\n"
			+ "    \"sourceBranchNo\" : \"9903\",\n" + "    \"sourceType\": \"MT\",\n"
			+ "    \"tranCode\" : \"\",\n" + "    \"tranDate\" : \"20240710\",\n"
			+ "    \"tranMode\" : \"ONLINE\",\n"
			+ "    \"tranTimestamp\" : \"142306371\",\n" + "    \"tranType\" : \"\",\n"
			+ "    \"userId\" : \"CP0101\",\n" + "    \"userLang\" : \"CHINESE\",\n"
			+ "    \"wsId\" : \"05\"\n" + "  }\n" + "}";

	@Test
	public void readTree() throws Exception {
		JsonNode jsonNode = JacksonHelper.readTree(string);
	}

	@Test
	public void writeValueAsStringIgnoreNull() throws Exception {
		Person person = new Person();
		Person.Body body = new Person.Body();
		body.setAge(1);
		body.setAcctName("雷建");
		person.setBody(body);
		person.setId(2);
		String obj2json = JacksonHelper.writeValueAsStringIgnoreNull(person);
	}

	@Test
	public void writeValueAsStringUpperIgnoreNull() throws Exception {
		Person person = new Person();
		Person.Body body = new Person.Body();
		body.setAge(1);
		body.setAcctName("雷建");
		person.setBody(body);
		person.setId(2);
		String obj2json = JacksonHelper.writeValueAsStringSnakeCaseStrategyIgnoreNull(person);
	}

	@Test
	public void writeValueAsStringSnakeCaseStrategyUpperIgnoreNull() throws Exception {
		Person person = new Person();
		Person.Body body = new Person.Body();
		body.setAge(1);
		body.setAcctName("雷建");
		person.setBody(body);
		person.setId(2);
		String obj2json = JacksonHelper.writeValueAsStringSnakeCaseStrategyUpperIgnoreNull(person);
	}

	@Test
	public void writeValueAsStringUpperCameLCaseIgnoreNull() throws Exception {
		Person person = new Person();
		Person.Body body = new Person.Body();
		body.setAge(1);
		body.setAcctName("雷建");
		person.setBody(body);
		person.setId(2);
		String obj2json = JacksonHelper.writeValueAsStringUpperCameLCaseIgnoreNull(person);
	}

}