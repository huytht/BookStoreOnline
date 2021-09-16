package com.hcmue.models;

public class WebUser {

    private String uid;
    private String pwd;
    private int authLevel;

    public WebUser() { }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getAuthLevel() {
        return authLevel;
    }

    public void setAuthLevel(int authLevel) {
        this.authLevel = authLevel;
    }

    @Override
    public String toString() {
        return "WebUser{" +
                "uid='" + uid + '\'' +
                ", pwd='" + pwd + '\'' +
                ", authLevel=" + authLevel +
                '}';
    }
}
