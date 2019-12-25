package com.open.cloud.framework.common.responses;

import com.open.cloud.framework.utils.MessageUtils;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Ret {

    /**
     * 返回码
     */
    private Integer retCode;
    /**
     * 返回信息
     */
    private String retMsg;

    /**
     * 当前时间戳
     */
    private LocalDateTime retTime;

    public Ret(Integer retCode, String retMsg, LocalDateTime retTime) {
        this.retCode = retCode;
        this.retMsg = MessageUtils.message(retMsg);
        this.retTime = retTime;

    }
}
