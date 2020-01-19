package com.open.cloud.test.okhttp;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class GitProjectPo {
	private Date createdAt;
	private String defaultBranch;
	private String description;
	private int forksCount;
	private String httpUrlToRepo;
	private String id;
	private Date lastActivityAt;
	private String name;
	private String nameWithNamespace;
	private String path;
	private String pathWithNamespace;
	private String sshUrlToRepo;
	private int starCount;
	private String tagList;
	private String webUrl;
}
