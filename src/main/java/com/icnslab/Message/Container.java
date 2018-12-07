package com.icnslab.Message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by alicek106 on 2017-08-17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Container {
    public String name;
    public String user;
    public String server;
    public String alive;
    public String lastcommit;
    public String created;
    public String baseimage;
    public String mpilib;
}
