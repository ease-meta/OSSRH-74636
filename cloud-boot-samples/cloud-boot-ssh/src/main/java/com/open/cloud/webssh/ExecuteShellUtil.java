package com.open.cloud.webssh;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.open.cloud.webssh.model.SSHConfigMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class ExecuteShellUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExecuteShellUtil.class);

	/** 未调用初始化方法 错误提示信息 */
	private static final String DONOT_INIT_ERROR_MSG = "please invoke init(...) first!";

	private Session rootSession;

	private Channel rootChannel;

	private ChannelExec rootChannelExec;

	private ExecuteShellUtil() {
	}

	/**
	 * 获取ExecuteShellUtil类实例对象
	 *
	 * @return 实例
	 * @date 2019/4/29 16:58
	 */
	public static ExecuteShellUtil getInstance() {
		return new ExecuteShellUtil();
	}

	public void init(SSHConfigMode sshConfigMode) throws JSchException {
		JSch jsch = new JSch();
		jsch.getSession(sshConfigMode.getRootname(), sshConfigMode.getHostname(), sshConfigMode.getPort());
		rootSession = jsch.getSession(sshConfigMode.getRootname(), sshConfigMode.getHostname(), sshConfigMode.getPort());
		rootSession.setPassword(sshConfigMode.getRootpassword());
		Properties sshConfig = new Properties();
		sshConfig.put("StrictHostKeyChecking", "no");
		rootSession.setConfig(sshConfig);
		rootSession.connect(60 * 1000);
		LOGGER.info("Session connected!");
		// 打开执行shell指令的通道
		rootChannel = rootSession.openChannel("exec");
		rootChannelExec = (ChannelExec) rootChannel;
	}

	/**
	 * 执行一条命令
	 */
	public String execCmd(String command) throws Exception {
		if (rootSession == null || rootChannel == null || rootChannelExec == null) {
			throw new Exception(DONOT_INIT_ERROR_MSG);
		}
		LOGGER.info("execCmd command - > {}", command);
		rootChannelExec.setCommand(command);
		rootChannel.setInputStream(null);
		rootChannelExec.setErrStream(System.err);
		rootChannel.connect();
		StringBuilder sb = new StringBuilder(16);
		try (InputStream in = rootChannelExec.getInputStream();
			 InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
			 BufferedReader reader = new BufferedReader(isr)) {
			String buffer;
			while ((buffer = reader.readLine()) != null) {
				sb.append("\n").append(buffer);
			}
			// 释放资源
			close();
			LOGGER.info("execCmd result - > {}", sb);
			return sb.toString();
		}
	}

	/**
	 * 释放资源
	 *
	 * @date 2019/3/15 12:47
	 */
	private void close() {
		if (rootChannelExec != null && rootChannelExec.isConnected()) {
			rootChannelExec.disconnect();
		}
		if (rootChannel != null && rootChannel.isConnected()) {
			rootChannel.disconnect();
		}
		if (rootSession != null && rootSession.isConnected()) {
			rootSession.disconnect();
		}
	}

}