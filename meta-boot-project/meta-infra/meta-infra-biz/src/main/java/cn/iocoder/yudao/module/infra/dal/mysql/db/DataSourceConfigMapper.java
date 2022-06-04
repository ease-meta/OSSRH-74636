package cn.iocoder.yudao.module.infra.dal.mysql.db;

import cn.iocoder.yudao.module.infra.dal.dataobject.db.DataSourceConfigDO;
import io.github.meta.ease.mybatis.mybatis.core.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据源配置 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface DataSourceConfigMapper extends BaseMapperX<DataSourceConfigDO> {
}
