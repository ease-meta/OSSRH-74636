/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.iocoder.yudao.module.bpm.service.definition;

import cn.hutool.core.lang.Assert;
import cn.iocoder.yudao.module.bpm.controller.admin.definition.vo.form.BpmFormCreateReqVO;
import cn.iocoder.yudao.module.bpm.controller.admin.definition.vo.form.BpmFormPageReqVO;
import cn.iocoder.yudao.module.bpm.controller.admin.definition.vo.form.BpmFormUpdateReqVO;
import cn.iocoder.yudao.module.bpm.convert.definition.BpmFormConvert;
import cn.iocoder.yudao.module.bpm.dal.dataobject.definition.BpmFormDO;
import cn.iocoder.yudao.module.bpm.dal.mysql.definition.BpmFormMapper;
import cn.iocoder.yudao.module.bpm.enums.definition.BpmModelFormTypeEnum;
import cn.iocoder.yudao.module.bpm.service.definition.dto.BpmFormFieldRespDTO;
import cn.iocoder.yudao.module.bpm.service.definition.dto.BpmModelMetaInfoRespDTO;
import io.github.meta.ease.bpm.enums.ErrorCodeConstants;
import io.github.meta.ease.common.pojo.PageResult;
import io.github.meta.ease.common.util.json.JsonUtils;
import io.github.meta.ease.common.util.validation.ValidationUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static io.github.meta.ease.bpm.enums.ErrorCodeConstants.FORM_NOT_EXISTS;
import static io.github.meta.ease.bpm.enums.ErrorCodeConstants.MODEL_DEPLOY_FAIL_FORM_NOT_CONFIG;
import static io.github.meta.ease.bpm.enums.ErrorCodeConstants.MODEL_KEY_VALID;
import static io.github.meta.ease.common.exception.util.ServiceExceptionUtil.exception;


/**
 * 动态表单 Service 实现类
 *
 * @author 风里雾里
 */
@Service
@Validated
public class BpmFormServiceImpl implements BpmFormService {

    @Resource
    private BpmFormMapper formMapper;

    @Override
    public Long createForm(BpmFormCreateReqVO createReqVO) {
        this.checkFields(createReqVO.getFields());
        // 插入
        BpmFormDO form = BpmFormConvert.INSTANCE.convert(createReqVO);
        formMapper.insert(form);
        // 返回
        return form.getId();
    }

    @Override
    public void updateForm(BpmFormUpdateReqVO updateReqVO) {
        this.checkFields(updateReqVO.getFields());
        // 校验存在
        this.validateFormExists(updateReqVO.getId());
        // 更新
        BpmFormDO updateObj = BpmFormConvert.INSTANCE.convert(updateReqVO);
        formMapper.updateById(updateObj);
    }

    @Override
    public void deleteForm(Long id) {
        // 校验存在
        this.validateFormExists(id);
        // 删除
        formMapper.deleteById(id);
    }

    private void validateFormExists(Long id) {
        if (formMapper.selectById(id) == null) {
            throw exception(FORM_NOT_EXISTS);
        }
    }

    @Override
    public BpmFormDO getForm(Long id) {
        return formMapper.selectById(id);
    }

    @Override
    public List<BpmFormDO> getFormList() {
        return formMapper.selectList();
    }

    @Override
    public List<BpmFormDO> getFormList(Collection<Long> ids) {
        return formMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<BpmFormDO> getFormPage(BpmFormPageReqVO pageReqVO) {
        return formMapper.selectPage(pageReqVO);
    }

    @Override
    public BpmFormDO checkFormConfig(String configStr) {
        BpmModelMetaInfoRespDTO metaInfo = JsonUtils.parseObject(configStr,
                BpmModelMetaInfoRespDTO.class);
        if (metaInfo == null || metaInfo.getFormType() == null) {
            throw exception(MODEL_DEPLOY_FAIL_FORM_NOT_CONFIG);
        }
        // 校验表单存在
        if (Objects.equals(metaInfo.getFormType(), BpmModelFormTypeEnum.NORMAL.getType())) {
            BpmFormDO form = getForm(metaInfo.getFormId());
            if (form == null) {
                throw exception(FORM_NOT_EXISTS);
            }
            return form;
        }
        return null;
    }

    private void checkKeyNCName(String key) {
        if (!ValidationUtils.isXmlNCName(key)) {
            throw exception(MODEL_KEY_VALID);
        }
    }

    /**
     * 校验 Field，避免 field 重复
     *
     * @param fields field 数组
     */
    private void checkFields(List<String> fields) {
        Map<String, String> fieldMap = new HashMap<>(); // key 是 vModel，value 是 label
        for (String field : fields) {
            BpmFormFieldRespDTO fieldDTO = JsonUtils.parseObject(field, BpmFormFieldRespDTO.class);
            Assert.notNull(fieldDTO);
            String oldLabel = fieldMap.put(fieldDTO.getVModel(), fieldDTO.getLabel());
            // 如果不存在，则直接返回
            if (oldLabel == null) {
                continue;
            }
            // 如果存在，则报错
            throw exception(ErrorCodeConstants.FORM_FIELD_REPEAT, oldLabel,
                    fieldDTO.getLabel(), fieldDTO.getVModel());
        }
    }
}
