package com.firebaselibrary.bean.search;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SearchDBResult <T> {
    private ResultData<T> data;
    private ResultContext context;
}

