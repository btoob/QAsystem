package com.ryz.qasystem.model;

public class RespBean {
    private Integer status;
    private String msg;
    private Object object;

    public static RespBean ok(String msg){
        return new RespBean(200, msg, null);
    }

    public static RespBean ok(String msg, Object obj){
        return new RespBean(200, msg, obj);
    }

    public static RespBean error(String msg){
        return new RespBean(500, msg, null);
    }

    public static RespBean error(String msg, Object obj){
        return new RespBean(500, msg, obj);
    }

    public static RespBean build(){
        return new RespBean();
    }


    private RespBean() {
    }

    private RespBean(Integer status, String message, Object object) {
        this.status = status;
        this.msg = message;
        this.object = object;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
