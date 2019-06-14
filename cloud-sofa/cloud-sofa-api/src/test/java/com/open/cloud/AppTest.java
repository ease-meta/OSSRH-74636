package com.open.cloud;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.open.cloud.api.model.RbAcctTransferIn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * Unit test for simple App.
 */
@Slf4j
public class AppTest 
{
    public static void main(String[] args) {
        RbAcctTransferIn rbAcctTransferIn = new RbAcctTransferIn();
        rbAcctTransferIn.setBaseAcctNo("123456");
        log.info("{}",rbAcctTransferIn);
        BaseAcctNo baseAcctNo = new BaseAcctNo();
        log.info("{}",baseAcctNo);
        BeanUtils.copyProperties(rbAcctTransferIn,baseAcctNo);
        log.info("{}",baseAcctNo);
        JSONObject json = JSON.parseObject(JSON.toJSONString(rbAcctTransferIn));

        JSONObject headJSON = json.getJSONObject("head");
    }
}
