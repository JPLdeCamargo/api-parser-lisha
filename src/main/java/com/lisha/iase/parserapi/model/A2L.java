package com.lisha.iase.parserapi.model;

public class A2L {
    private String name;
    private byte[] file;

    private  String parsed;

    public String getName(){ return name; }
    public void setName(String name){ this.name = name; }
    public byte[] getFile(){ return file; }
    public void setFile(byte[] file){ this.file = file; }
    public String getParsed(){ return parsed; }
    public void setParsed(String parsed){ this.parsed = parsed; }
}
