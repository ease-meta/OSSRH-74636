package cn.iocoder.yudao.module.bpm.convert.message;

import cn.iocoder.yudao.module.system.api.sms.dto.send.SmsSendSingleToUserReqDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Map;

@Mapper
public interface BpmMessageConvert {

    BpmMessageConvert INSTANCE = Mappers.getMapper(BpmMessageConvert.class);

    default SmsSendSingleToUserReqDTO convert(Long userId, String templateCode, Map<String, Object> templateParams) {
        SmsSendSingleToUserReqDTO smsSendSingleToUserReqDTO = new SmsSendSingleToUserReqDTO();
        smsSendSingleToUserReqDTO.setUserId(userId);
        smsSendSingleToUserReqDTO.setTemplateCode(templateCode);
        smsSendSingleToUserReqDTO.setTemplateParams(templateParams);
        return smsSendSingleToUserReqDTO;
    }

}
