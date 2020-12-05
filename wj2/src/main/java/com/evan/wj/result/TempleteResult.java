package com.evan.wj.result;

import java.util.List;

public class TempleteResult<T> {
    private int code;
    List<T> data;

    public TempleteResult(int code, List<T> data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
