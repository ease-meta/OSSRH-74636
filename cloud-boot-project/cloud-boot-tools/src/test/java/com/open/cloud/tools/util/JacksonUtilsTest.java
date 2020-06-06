package com.open.cloud.tools.util;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

public class JacksonUtilsTest {
	String string = "{\n" +
			"  \"appHead\" : {\n" +
			"    \"currentNum\" : \"0\",\n" +
			"    \"pageEnd\" : \"0\",\n" +
			"    \"pageStart\" : \"0\",\n" +
			"    \"pgupOrPgdn\" : \"0\",\n" +
			"    \"totalNum\" : \"-1\"\n" +
			"  },\n" +
			"  \"body\" : {\n" +
			"    \"baseAcctNo\" : \"24000200000006048\"\n" +
			"  },\n" +
			"  \"localHead\" : { },\n" +
			"  \"sysHead\" : {\n" +
			"    \"apprFlag\" : \"\",\n" +
			"    \"apprUserId\" : \"\",\n" +
			"    \"authFlag\" : \"N\",\n" +
			"    \"authPassword\" : \"\",\n" +
			"    \"authUserId\" : \"\",\n" +
			"    \"branchId\" : \"2000\",\n" +
			"    \"destBranchNo\" : \"2000\",\n" +
			"    \"messageType\" : \"1400\",\n" +
			"    \"messageCode\" : \"2101\",\n" +
			"    \"moduleId\" : \"CL\",\n" +
			"    \"programId\" : \"4100\",\n" +
			"    \"reversalTranType\" : \"\",\n" +
			"    \"sceneId\" : \"01\",\n" +
			"    \"seqNo\" : \"68f728852cc36723142306371\",\n" +
			"    \"serverId\" : \"192.168.161.156\",\n" +
			"    \"serviceCode\" : \"MbsdCore\",\n" +
			"    \"sourceBranchNo\" : \"9903\",\n" +
			"    \"sourceType\": \"MT\",\n" +
			"    \"tranCode\" : \"\",\n" +
			"    \"tranDate\" : \"20240710\",\n" +
			"    \"tranMode\" : \"ONLINE\",\n" +
			"    \"tranTimestamp\" : \"142306371\",\n" +
			"    \"tranType\" : \"\",\n" +
			"    \"userId\" : \"CP0101\",\n" +
			"    \"userLang\" : \"CHINESE\",\n" +
			"    \"wsId\" : \"05\"\n" +
			"  }\n" +
			"}";

	@Test
	public void testObject2Json() throws Exception {
		Person person = new Person();
		Person.Body body = new Person.Body();
		body.setAge(1);
		body.setAcctName("雷建");
		person.setBody(body);
		person.setId(2);
		String obj2json = JacksonUtils.obj2jsonIgnoreNull(person);
	}

	@Test
	public void testString2Json() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(string);
		Request request = mapper.readValue(string, Request.class);
	}

}