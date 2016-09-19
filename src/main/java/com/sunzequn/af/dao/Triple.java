package com.sunzequn.af.dao;

import java.io.Serializable;

/**
 * Created by sloriac on 16-8-29.
 * <p>
 * 三元组的JavaBean
 */
public class Triple implements Serializable {

    private String s;
    private String p;
    private String o;

    public Triple() {
    }

    public Triple(String s, String p, String o) {
        this.s = s;
        this.p = p;
        this.o = o;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getO() {
        return o;
    }

    public void setO(String o) {
        this.o = o;
    }

    @Override
    public String toString() {
        return "Triple{" +
                "s='" + s + '\'' +
                ", p='" + p + '\'' +
                ", o='" + o + '\'' +
                '}';
    }
}
