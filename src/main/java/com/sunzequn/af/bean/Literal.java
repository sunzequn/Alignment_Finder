package com.sunzequn.af.bean;

/**
 * Created by sloriac on 16-9-6.
 */
public class Literal {

    private long id;
    private String literal;

    public Literal() {
    }

    public Literal(String literal) {
        this.literal = literal;
    }

    public Literal(long id, String literal) {
        this.id = id;
        this.literal = literal;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLiteral() {
        return literal;
    }

    public void setLiteral(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return "Literal{" +
                "id=" + id +
                ", literal='" + literal + '\'' +
                '}';
    }
}
