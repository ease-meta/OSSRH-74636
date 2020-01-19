package com.open.cloud.test.okhttp;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PagePo {
	private String nextPage;
	private String page;
	private String perPage;
	private String prevPage;
	private String toTal;
	private String toTalPages;


}
