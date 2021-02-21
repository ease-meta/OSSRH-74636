package org.elasticsearch.util;
/**
 * @ClassName: ResultUtil
 * @Description: java类作用描述
 * @Author: liyuq
 * @CreateDate: 2019/9/4 18:43
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ResultUtil {

    public  static  ResultModel success(int code, String msg, long count, Object data){
        ResultModel resultModel= new ResultModel();
        resultModel.setCode(code);
        resultModel.setMsg(msg);
        resultModel.setData(data);
        resultModel.setCount(count);
        return  resultModel;
    }
}
