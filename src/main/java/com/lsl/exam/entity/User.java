package com.lsl.exam.entity;


import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("users")
public class User implements Serializable {
    private static final long serialVersionUID = 8983061158385445440L;

    private String userid;
    private String username;
    private String userpwd;
    private String truename;
    private String classid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", userpwd='" + userpwd + '\'' +
                ", truename='" + truename + '\'' +
                ", classid='" + classid + '\'' +
                '}';
    }
}
