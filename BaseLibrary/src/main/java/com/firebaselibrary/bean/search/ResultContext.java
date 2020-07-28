package com.firebaselibrary.bean.search;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResultContext {
    private String message;
    private Date timestamp;
    private int status;
}
