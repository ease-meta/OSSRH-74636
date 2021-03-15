package com.open.cloud.webssh.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 表格实体
 */
public class InstallConfigMode {

	@ExcelProperty(index = 3)
	private String hostname;

	@ExcelProperty(index = 4)
	private String username;

	@ExcelProperty(index = 5)
	private String password;

    @ExcelIgnore
	private String rootname = "root";

	@ExcelProperty(index = 8)
	private String rootpassword;

    @ExcelIgnore
	private int port = 22;

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRootname() {
		return rootname;
	}

	public void setRootname(String rootname) {
		this.rootname = rootname;
	}

	public String getRootpassword() {
		return rootpassword;
	}

	public void setRootpassword(String rootpassword) {
		this.rootpassword = rootpassword;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.DEFAULT_STYLE);
	}
}