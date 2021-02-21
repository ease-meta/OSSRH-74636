package org.elasticsearch.util;

import lombok.Data;


/**
 * @ClassName: ResultModel
 * @Description: java类作用描述
 * @Author: liyuq
 * @CreateDate: 2019/9/4 18:38
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Data
public class ResultModel {
   private int code ;
   private String msg;
   private long count;
   private Object data;
}
