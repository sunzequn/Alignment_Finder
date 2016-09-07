package com.sunzequn.af.bean;

/**
 * Created by sloriac on 16-9-6.
 */
public class Uri {

    private long id;
    private String uri;

    public Uri() {
    }

    public Uri(String uri) {
        this.uri = uri;
    }

    public Uri(long id, String uri) {
        this.id = id;
        this.uri = uri;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "Uri{" +
                "id=" + id +
                ", uri='" + uri + '\'' +
                '}';
    }
}
