package com.open.cloud.webssh.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Leijian
 * @date 2021/3/11 17:55
 * @since
 */
@Mapper()
public interface SSHConfigModeConverter extends BaseConvert<InstallConfigMode, SSHConfigMode> {
	SSHConfigModeConverter INSTANCE = Mappers.getMapper(SSHConfigModeConverter.class);
}
