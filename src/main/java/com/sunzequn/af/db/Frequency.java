package com.sunzequn.af.db;

/**
 * Created by sloriac on 16-9-19.
 */
public class Frequency {

    private String prop;
    private String value;
    private int frequency;

    public Frequency() {
    }

    public Frequency(String prop, String value, int frequency) {
        this.prop = prop;
        this.value = value;
        this.frequency = frequency;
    }

    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "Frequency{" +
                "prop='" + prop + '\'' +
                ", value='" + value + '\'' +
                ", frequency=" + frequency +
                '}';
    }
}
