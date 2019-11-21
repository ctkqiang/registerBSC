package com.johnmelodyme.BSC;

public class realtimedata {
    private String NAME;
    private String EMAIL;
    private String PASSWORD;

    public realtimedata(){
    }

    public realtimedata(String NAME, String EMAIL, String PASSWORD){
        this.NAME = NAME;
        this.EMAIL = EMAIL;
        this.PASSWORD = PASSWORD;
    }

    public String getNAME(){
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getEMAIL(){
        return EMAIL;
    }

    public void setEMAIL(String EMAIL){
        this.EMAIL = EMAIL;
    }

    public String getPASSWORD(){
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD){
        this.PASSWORD = PASSWORD;
    }
}
