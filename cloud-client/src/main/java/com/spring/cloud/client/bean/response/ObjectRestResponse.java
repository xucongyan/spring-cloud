package com.spring.cloud.client.bean.response;

/**
 * @author xucongyan
 */
public class ObjectRestResponse<T> extends BaseResponse {

    private T data;
    private boolean rel;

    public ObjectRestResponse rel(boolean rel) {
        this.setRel(rel);
        return this;
    }


    public ObjectRestResponse data(T data) {
        this.setData(data);
        return this;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isRel() {
        return rel;
    }

    public void setRel(boolean rel) {
        this.rel = rel;
    }
}
