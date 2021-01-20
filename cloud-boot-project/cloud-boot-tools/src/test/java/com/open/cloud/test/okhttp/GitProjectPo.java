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
package com.open.cloud.test.okhttp;

import java.util.Date;
import java.util.Objects;

public class GitProjectPo {
	private int id;
	private String exist = "0";
	private String httpUrlToRepo;
	private Date createdAt;
	private Date lastActivityAt;
	private String description;

    /*@JSONField(serialize = false)
    private String defaultBranch;
    @JSONField(serialize = false)
    private int forksCount;
    @JSONField(serialize = false)
    private String name;
    @JSONField(serialize = false)
    private String nameWithNamespace;
    @JSONField(serialize = false)
    private String path;
    @JSONField(serialize = false)
    private String pathWithNamespace;
    @JSONField(serialize = false)
    private String sshUrlToRepo;
    @JSONField(serialize = false)
    private int starCount;
    @JSONField(serialize = false)
    private String tagList;
    @JSONField(serialize = false)
    private String webUrl;*/

	@Override
	public int hashCode() {
		return Objects.hash(id, httpUrlToRepo, createdAt, lastActivityAt, description);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GitProjectPo that = (GitProjectPo) o;
		return Objects.equals(String.valueOf(id), String.valueOf(that.id));
		//	Objects.equals(httpUrlToRepo, that.httpUrlToRepo) &&
		//	Objects.equals(createdAt, that.createdAt) &&
		//	Objects.equals(lastActivityAt, that.lastActivityAt) &&
		//	Objects.equals(description, that.description);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getExist() {
		return exist;
	}

	public void setExist(String exist) {
		this.exist = exist;
	}

	public String getHttpUrlToRepo() {
		return httpUrlToRepo;
	}

	public void setHttpUrlToRepo(String httpUrlToRepo) {
		this.httpUrlToRepo = httpUrlToRepo;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getLastActivityAt() {
		return lastActivityAt;
	}

	public void setLastActivityAt(Date lastActivityAt) {
		this.lastActivityAt = lastActivityAt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
