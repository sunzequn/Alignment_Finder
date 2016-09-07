package com.sunzequn.af.bean;

/**
 * Created by sloriac on 16-9-6.
 */
public class Prop {

    private long id;
    private String prop;

    public Prop() {
    }

    public Prop(String prop) {
        this.prop = prop;
    }

    public Prop(long id, String prop) {
        this.id = id;
        this.prop = prop;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

    @Override
    public String toString() {
        return "Prop{" +
                "id=" + id +
                ", prop='" + prop + '\'' +
                '}';
    }
}
