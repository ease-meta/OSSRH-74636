package com.open.cloud.test.okhttp;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class GitProjectPo {
	private LocalDateTime createdAt;
	private String defaultBranch;
	private String description;
	private int forksCount;
	private String httpUrlToRepo;
	private String id;
	private LocalDateTime lastActivityAt;
	private String name;
	private String nameWithNamespace;
	private String path;
	private String pathWithNamespace;
	private String sshUrlToRepo;
	private int starCount;
	private String tagList;
	private String webUrl;
}
