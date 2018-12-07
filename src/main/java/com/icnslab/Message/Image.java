package com.icnslab.Message;

import java.io.Serializable;

/**
 * Created by alicek106 on 2017-08-03.
 */
public class Image implements Serializable{
    private String name;
    private String user;
    private String created;
    private String status;
    private String metadata;
    private String baseimage;
    private String mpilib;

    public String getMpilib() {
        return mpilib;
    }

    public void setMpilib(String mpilib) {
        this.mpilib = mpilib;
    }

    public String getBaseimage() { return baseimage; }

    public void setBaseimage(String baseimage) { this.baseimage = baseimage; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }
}
