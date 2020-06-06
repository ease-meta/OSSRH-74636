package com.open.cloud.tools.util;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Leijian
 * @date 2020/6/3
 */
@Data
public abstract class BasePo implements Serializable {


	private static final long serialVersionUID = -6707173699291858544L;

	private String name;

	private Integer id;

}
