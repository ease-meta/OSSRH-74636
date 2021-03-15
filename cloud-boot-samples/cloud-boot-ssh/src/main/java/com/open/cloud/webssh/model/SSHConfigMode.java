package com.open.cloud.webssh.model;

/**
* @Description: webssh数据传输
* @Author: NoCortY
* @Date: 2020/3/8
*/
public class SSHConfigMode {
    //操作
    private String operate;

    private String hostname;
    //端口号默认为22
    private Integer port = 22;

    private String username;

    private String password;

    private String command = "";

    private String rootname = "root";

    private String rootpassword;

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
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

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
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
}
