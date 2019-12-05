package com.open.cloud.es.util;

import lombok.Data;

@Data
public class ResultModel {
   private int code ;
   private String msg;
   private long count;
   private Object data;
}
