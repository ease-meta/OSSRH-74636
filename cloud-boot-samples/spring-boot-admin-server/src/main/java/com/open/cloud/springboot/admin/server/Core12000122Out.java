package com.open.cloud.springboot.admin.server;

import lombok.Data;

/***
 * @description 客户级限制
 * @version V1.0
 * @author wanghy
 * @update 20160601
 */
@Data
public class Core12000122Out {

	/***
	 * @fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 限制信息序号<br>
	 * RESTRAINT_SEQ_NO<br>
	 * seqNo:1<br>
	 * dataType:String<br>
	 * length:30<br>
	 * cons:
	 */
	private String restraintSeqNo;

}
