package com.sunzequn.af.db;

/**
 * Created by sloriac on 16-9-20.
 */
public class Id {
    private String id;
    private String content;

    public Id() {
    }

    public Id(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Id{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
