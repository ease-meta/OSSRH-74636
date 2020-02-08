package com.open.cloud.auth.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
public class UserPo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * 账号
	 */
	private String userName;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 昵称
	 */
	private String nickName;

}
