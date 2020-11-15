package com.open.cloud.auth.oauth2.authserver.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CommonResult implements Serializable {

	private static final long serialVersionUID = 6191745064790884707L;
	private String code;
	private String message;
	private Object data;
}