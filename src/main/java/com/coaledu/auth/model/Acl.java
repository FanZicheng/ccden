package com.coaledu.auth.model;

public class Acl {
    private Integer id;

    private Integer pid;

    private String ptype;

    private Integer rid;

    private String rtype;

    private Integer aclState;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype == null ? null : ptype.trim();
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRtype() {
        return rtype;
    }

    public void setRtype(String rtype) {
        this.rtype = rtype == null ? null : rtype.trim();
    }

    public Integer getAclState() {
        return aclState;
    }

    public void setAclState(Integer aclState) {
        this.aclState = aclState;
    }
}