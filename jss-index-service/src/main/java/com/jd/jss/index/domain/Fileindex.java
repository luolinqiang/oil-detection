package com.jd.jss.index.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * User: luolinqiang
 */
public class Fileindex implements Serializable {

    public Long id;
    public Long pid;
    public String name;
    public Long userid;
    public String link;
    public Integer appid;
    public Integer filetype;
    public String metadata;
    public Date atime;
    public Date ctime;
    public Date mtime;
    public Long size;
    public String path;

    public int delRetryCount;
}