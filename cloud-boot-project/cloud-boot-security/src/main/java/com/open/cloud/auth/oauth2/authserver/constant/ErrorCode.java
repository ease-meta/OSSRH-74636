package com.open.cloud.auth.oauth2.authserver.constant;

public class ErrorCode {

	public static final String SUCCESS = "0000";
	public static final String NO_AUTH = "0401";
	public static final String BAD_CREDENTIALS = "0400";
	public static final String SERVER_INTERNAL_ERROR = "1000";
	public static final String PARAMETER_MISSING_ERROR = "1001";
	public static final String PARAMETER_ILLEGAL_ERROR = "1002";
	public static final String RESOURCE_NOT_FOUND_ERROR = "1003";
	public static final String USER_NOT_FOUND_ERROR = "1004";
	public static final String PASSWORD_INCORRECT = "1005";
	public static final String DATABASE_OPERATION_ERROR = "1006";
	public static final String SESSION_EXPIRED = "1007";
	public static final String NO_PERMISSIONS = "1008";
	public static final String NO_LOGIN = "1009";
	public static final String DEPARTMENT_CAN_NOT_REMOVE = "1010";
	public static final String DEPARTMENT_HAS_USER = "1011";
	public static final String ROLE_SAVE_ERROR = "1012";
	public static final String ROLE_UPDATE_ERROR = "1013";
	public static final String ROLE_DELETE_ERROR = "1014";
	public static final String MENU_SAVE_ERROR = "1015";
	public static final String MENU_UPDATE_ERROR = "1016";
	public static final String MENU_DELETE_ERROR = "1017";
	public static final String MENU_HAS_CHILD = "1018";
	public static final String USER_ALREADY_EXISTS = "1019";
	public static final String USER_SAVE_ERROR = "1020";
	public static final String USER_UPDATE_ERROR = "1021";
	public static final String USER_DELETE_ERROR = "1022";
	public static final String WRONG_PASSWORD = "1023";
	public static final String MODIFY_PASSWORD_ERROR = "1024";
	public static final String CAN_NOT_RESET_ADMIN_PASSWORD = "1025";
	public static final String RESET_PASSWORD_ERROR = "1026";
	public static final String REVOKE_TOKEN_ERROR = "1027";
	public static final String CAN_NOT_DELETE_SELF = "1028";
	public static final String DICT_SAVE_ERROR = "1029";
	public static final String DICT_UPDATE_ERROR = "1030";
	public static final String DICT_DELETE_ERROR = "1031";
	public static final String DICT_NOT_EXIST = "1032";
	public static final String USER_DISABLE = "1033";
	public static final String ADMIN_REFEUSED = "1034";
	public static final String RESOURCE_NOT_FOUND = "1000";
	public static final String REQUEST_PARAM_ERROR = "1001";
	public static final String RESOURCE_CONFLICT = "1002";
	public static final String AUTH_FAILED_ERROR = "1007";
	public static final String JOB_OPERATE_ERROR = "1009";
	public static final String DATA_MIGRATE_ERROR = "1010";
	public static final String SERVICE_DISABLE = "1011";
	public static final String DATABASE_EXCEPTION = "9999";

	public static final String SUCCESS_MSG = "Success";

	private ErrorCode() {

	}
}
