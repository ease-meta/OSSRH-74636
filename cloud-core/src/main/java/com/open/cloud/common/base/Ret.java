/**
 * <p>Title: Ret.java</p>
 * <p>Description: </p>
 *
 * @author leijian
 * @date 2019年1月10日
 * @version 1.0
 */
package com.open.cloud.common.base;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.open.cloud.common.utils.MessageUtils;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author leijian
 * @date 2019年1月10日
 */
@Getter
@ToString
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Ret {

	/**
	 * 返回码
	 */
	private Integer retCode;
	/**
	 * 返回信息
	 */
	private String retMsg;

	/**
	 * 当前时间戳
	 */
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime retTime;

	public Ret(Integer retCode, String retMsg, LocalDateTime retTime) {
		this.retCode = retCode;
		this.retMsg = MessageUtils.message(retMsg);
		this.retTime = retTime;

	}
}
