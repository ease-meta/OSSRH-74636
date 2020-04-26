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

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class OkHttpMain {
    public static void main(String[] args) {
		LinkedList<GitProjectPo> temp = new LinkedList();
		String fileName = "测试.xlsx";
		String password = "1q2w3e4r";

		List<GitProjectPo> readSync = new LinkedList<>();
		try {
			readSync = EasyExcel.read(fileName).password(password).head(GitProjectPo.class).sheet().doReadSync();
		} catch (Exception e) {
			//TODO
		}

		Two<LinkedList, PagePo> two = load(null, null);
		temp.addAll(two.first);

		while (two.second.getNextPage() != null && two.second.getNextPage().trim().length() > 0) {
			two = load(two.second.getPerPage(), two.second.getNextPage());
			temp.addAll(two.first);
		}
		List<GitProjectPo> linkedList = temp.stream().sorted(Comparator.comparing(GitProjectPo::getId)).collect(Collectors.toList());
		for (int i = 0; i < readSync.size(); i++) {
			for (int j = 0; j < linkedList.size(); j++) {
				if (readSync.get(i).getId() == linkedList.get(j).getId()) {
					linkedList.get(j).setExist(readSync.get(i).getExist());
				}
			}
		}
		Collections.sort(linkedList, new Comparator<GitProjectPo>() {
			@Override
			public int compare(GitProjectPo o1, GitProjectPo o2) {
				return o1.getHttpUrlToRepo().compareTo(o2.getHttpUrlToRepo());
			}
		});
		EasyExcel.write(fileName).password(password)
				// 这里放入动态头
				.head(head())
				.sheet()
				// 当然这里数据也可以用 List<List<String>> 去传入
				.doWrite(linkedList);
	}

    private static List<List<String>> head() {
        List<List<String>> list = new ArrayList<List<String>>();
        List<String> head0 = new ArrayList<String>();
        head0.add("序号");
        List<String> head1 = new ArrayList<String>();
        head1.add("状态");
        List<String> head2 = new ArrayList<String>();
        head2.add("地址");
        List<String> head3 = new ArrayList<String>();
        head3.add("创建日期");
        List<String> head4 = new ArrayList<String>();
        head4.add("更新日期");
        List<String> head5 = new ArrayList<String>();
        head5.add("描述信息");
        list.add(head0);
        list.add(head1);
        list.add(head2);
        list.add(head3);
        list.add(head4);
        list.add(head5);
        return list;
    }

    public static String getPath() {
        return OkHttpMain.class.getResource("/").getPath();
    }

    public static Two<LinkedList, PagePo> load(String per_page, String page) {
		//String url = "http://10.7.20.144/api/v3/projects?simple=true";
		//String url = "http://10.7.20.144/api/v3/projects/8/issues/8/notes?per_page=50";
		if (per_page == null) {
			per_page = "100";
		}
		if (page == null) {
			page = "1";
		}
		String url = String.format("http://10.7.20.144/api/v4/projects?per_page=%s&page=%s&simple=true", per_page, page);
		OkHttpClient okHttpClient = new OkHttpClient();
		final Request request = new Request.Builder()
				.url(url)
				.addHeader("PRIVATE-TOKEN", "x3ghr4ao3Dy3m4i11JVP")
				.get()//默认就是GET请求，可以不写
				.build();
		Call call = okHttpClient.newCall(request);
		Two<LinkedList, PagePo> two = new Two<>();
		try {
			Response response = call.execute();
			Headers headers = response.headers();
			headers.names().forEach(
					s -> {
						log.info(s + ":" + response.header(s));
					}
			);
			String body = response.body().string();
			Object json = JSON.parse(body);
			if (json instanceof JSONArray) {
				JSONArray jsonArray = (JSONArray) json;
				LinkedList linkedList = new LinkedList();
				((JSONArray) json).forEach(o -> {
					GitProjectPo gitProjectPo = JSON.parseObject(JSON.toJSONString(o), GitProjectPo.class);
					linkedList.add(gitProjectPo);
				});
				two.first = linkedList;
			} else {
				//TODO nothing
			}

			PagePo pagePo = PagePo.builder()
					.nextPage(response.header("X-Next-Page"))
					.page(response.header("X-Page"))
					.perPage(response.header("X-Per-Page"))
					.prevPage(response.header("X-Prev-Page"))
					.toTal(response.header("X-Total"))
					.toTalPages(response.header("X-Total-Pages")).build();
			two.second = pagePo;
		} catch (IOException e) {
			log.error("{},{}", e, e.getMessage());
		}
		return two;
	}
}
