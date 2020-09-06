package com.open.cloud.base.java.esapi;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.Codec;
import org.owasp.esapi.codecs.MySQLCodec;

/**
 * @author Leijian
 * @date 2020/9/6
 */
public class Esapi {
	public static void main(String[] args) {
		Codec codec = new MySQLCodec(MySQLCodec.Mode.ANSI);
		ESAPI.encoder().encodeForSQL(codec,"123");
	}
}
