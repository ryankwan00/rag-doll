package com.company;

public class Model implements Comparable<Model>{
    public String crime_id;
    public String month;
    public String reported_by;
    public String falls_within;
    public String longitude;
    public String latitude;
    public String location;
    public String lsoa_code;
    public String lsoa_name;
    public String crime_type;
    public String last_outcome_category;
    public String context;


    public Model(String p1,String p2,String p3,String p4,String p5,String p6,String p7,String p8,String p9,String p10,String p11,String p12){
        crime_id=p1;
        month=p2;
        reported_by=p3;
        falls_within=p4;
        longitude=p5;
        latitude=p6;
        location=p7;
        lsoa_code=p8;
        lsoa_name=p9;
        crime_type=p10;
        last_outcome_category=p11;
        context=p12;
    }

    public String toString(){
        return crime_id+","+
                month+","+
                reported_by+","+
                falls_within+","+
                longitude+","+
                latitude+","+
                location+","+
                lsoa_code+","+
                lsoa_name+","+
                crime_type+","+
                last_outcome_category+","+
                context;
    }

    @Override
    public int compareTo(Model o) {
        return o.crime_type.hashCode();
    }
}

