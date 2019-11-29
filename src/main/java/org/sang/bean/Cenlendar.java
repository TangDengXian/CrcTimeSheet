package org.sang.bean;

import java.io.Serializable;
import java.util.Date;

public class Cenlendar implements Serializable {
    private Date date;

    private Integer status;

    private static final long serialVersionUID = 1L;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}