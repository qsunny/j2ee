package com.aaron.joddlibs.bean;

public class Foo {
    private String readwrite;   // with getter and setter
    private String readonly;    // with getter

    public String getReadwrite() {
        return readwrite;
    }

    public void setReadwrite(String readwrite) {
        this.readwrite = readwrite;
    }

    public String getReadonly() {
        return readonly;
    }

    public void setReadonly(String readonly) {
        this.readonly = readonly;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "readwrite='" + readwrite + '\'' +
                ", readonly='" + readonly + '\'' +
                '}';
    }
}
