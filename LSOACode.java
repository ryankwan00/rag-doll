package com.company;

public class LSOACode implements Comparable<LSOACode>{
    public String code;
    public String month;
    public int count;

    public LSOACode(String code,String month){
        this.code=code;
        this.month=month;
        count=1;
    }

    public LSOACode(String code){
        this.code=code;
        count=1;
    }

//    @Override
//    public int compareTo(LSOACode o) {
//        int rlt=this.code.compareTo(o.code);
//        return rlt==0?this.month.compareTo(o.month):rlt;
//    }

    @Override
    public int compareTo(LSOACode o) {
        return this.code.compareTo(o.code);
    }
}
