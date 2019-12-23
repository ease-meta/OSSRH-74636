package com.open.cloud.maven.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo(name = "exportDataToFile")
public class WriteDataToFile extends AbstractMojo {
	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		//要执行的代码
	}
}