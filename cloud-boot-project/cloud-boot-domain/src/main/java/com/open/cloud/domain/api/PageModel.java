package com.open.cloud.domain.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
public class PageModel<T> implements Serializable {

    private Integer total;

    private List<T> data;

}
