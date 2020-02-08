package com.open.cloud.common.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author leijian
 * @date 2019年1月13日
 */
@Getter
@ToString
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Ret ret;

	private T result;

}
