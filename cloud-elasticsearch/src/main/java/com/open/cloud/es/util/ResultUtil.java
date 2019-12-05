package com.open.cloud.es.util;

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
