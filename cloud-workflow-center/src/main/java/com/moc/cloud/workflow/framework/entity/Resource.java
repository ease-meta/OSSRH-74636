package com.moc.cloud.workflow.framework.entity;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@ToString
@Table(name="dc_act_sys_resource")
public class Resource {

	/**
	 * 主键
	 */
	@Id
	private String id;
	/**
	 * 资源名称
	 */
	private String resourceName;

	/**
	 * 路径映射
	 */
	private String mapping;

	/**
	 * 请求方式
	 */
	private String method;

	/**
	 * 权限认证类型
	 */
	private String authType;
	/**
	 * 权限标识
	 */
	private String perm;
	/**
	 * 修改时间
	 */
	private LocalDateTime updateTime;

}
