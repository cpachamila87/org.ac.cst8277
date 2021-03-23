package org.ac.cst8277.ahmed.basit.util;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse implements Serializable {
    private int code;
    private BaseMessage data;
    private Collection<? extends BaseMessage> dataList;
    private String message;
}
