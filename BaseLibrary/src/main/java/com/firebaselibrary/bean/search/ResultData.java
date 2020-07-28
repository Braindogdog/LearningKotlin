package com.firebaselibrary.bean.search;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResultData<T> {
//    private int totalItems;
    private T resultList;
}
