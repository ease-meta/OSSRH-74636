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
import com.open.cloud.common.utils.LocalDateTimeUtils;
import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@
public class OkHttpMain {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        Two<LinkedList, PagePo> two = load(null, null);
        linkedList.addAll(two.first);
        while (two.second.getNextPage() != null && two.second.getNextPage().trim().length() > 0) {
            two = load(two.second.getPerPage(), two.second.getNextPage());
            linkedList.addAll(two.first);
        }
        String fileName = LocalDateTimeUtils.formatNow("yyyy-MM-dd") + "测试.xlsx";
        EasyExcel.write(fileName).password("1q2w3e4r")
        // 这里放入动态头
        //.head(head())
            .sheet()
            // 当然这里数据也可以用 List<List<String>> 去传入
            .doWrite(linkedList);
    }

    private static List<List<String>> head() {
        List<List<String>> list = new ArrayList<List<String>>();
        List<String> head0 = new ArrayList<String>();
        head0.add("创建日期");
        List<String> head1 = new ArrayList<String>();
        head1.add("默认分支");
        List<String> head2 = new ArrayList<String>();
        head2.add("fork数");
        list.add(head0);
        list.add(head1);
        list.add(head2);
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
