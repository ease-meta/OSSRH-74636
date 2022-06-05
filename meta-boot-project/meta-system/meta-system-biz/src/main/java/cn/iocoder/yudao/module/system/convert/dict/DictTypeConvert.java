package cn.iocoder.yudao.module.system.convert.dict;

import cn.iocoder.yudao.module.system.controller.admin.dict.vo.type.DictTypeCreateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.dict.vo.type.DictTypeExcelVO;
import cn.iocoder.yudao.module.system.controller.admin.dict.vo.type.DictTypeRespVO;
import cn.iocoder.yudao.module.system.controller.admin.dict.vo.type.DictTypeSimpleRespVO;
import cn.iocoder.yudao.module.system.controller.admin.dict.vo.type.DictTypeUpdateReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.dict.DictTypeDO;
import io.github.meta.ease.common.pojo.PageResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DictTypeConvert {

    DictTypeConvert INSTANCE = Mappers.getMapper(DictTypeConvert.class);

    PageResult<DictTypeRespVO> convertPage(PageResult<DictTypeDO> bean);

    DictTypeRespVO convert(DictTypeDO bean);

    DictTypeDO convert(DictTypeCreateReqVO bean);

    DictTypeDO convert(DictTypeUpdateReqVO bean);

    List<DictTypeSimpleRespVO> convertList(List<DictTypeDO> list);

    List<DictTypeExcelVO> convertList02(List<DictTypeDO> list);
}
