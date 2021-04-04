package com.example.bhm2;

public class BeehiveInfo {
    private final String id;
    private final String number;
    private final String population;

    public BeehiveInfo(String beehiveId, String beehiveNumber, String beehivePopulation){
        id = beehiveId;
        number = beehiveNumber;
        population = beehivePopulation;
    }
    public String getId(){
        return id;
    }
    public String getNumber(){
        return number;
    }
    public String getPopulation(){
        return population;
    }
}
