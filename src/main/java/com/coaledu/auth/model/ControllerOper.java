package com.coaledu.auth.model;

public class ControllerOper {
    private Integer id;

    private String name;

    private String methodName;

    private Integer indexpos;

    private String sn;

    private Integer rid;

    private String rsn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName == null ? null : methodName.trim();
    }

    public Integer getIndexpos() {
        return indexpos;
    }

    public void setIndexpos(Integer indexpos) {
        this.indexpos = indexpos;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRsn() {
        return rsn;
    }

    public void setRsn(String rsn) {
        this.rsn = rsn == null ? null : rsn.trim();
    }
}