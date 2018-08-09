package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.lang.System.exit;


public class Process {
    public List<Model> records;
    public List<CrimeType> crime_types;
    public List<LSOACode> lsoa_codes;


    public Process(){
        records=new ArrayList<>();
        crime_types=new ArrayList<>();
        lsoa_codes=new ArrayList<>();
        String datapath = System.getProperty("user.dir")+"\\data";
        GetData(datapath);
    }

    public void GetData(String datapath){
        System.out.println("reading data...");
        File file=new File(datapath);
        if(!file.isDirectory()){
            System.out.println("read data error");
            exit(-1);
        }
        File[] files=file.listFiles();
        if(files==null){
            System.out.println("no data error");
            exit(-1);
        }
        for(File f:files){
            if(f.isDirectory()){
                File[] fs=f.listFiles();
                if(fs==null){
                    System.out.println("no data error");
                    exit(-1);
                }
                for(File f2:fs){
                    if(f2.isFile()){
                        System.out.println("reading "+f2.getAbsolutePath());
                        FileReader fr = null;
                        BufferedReader br = null;
                        try{
                            fr = new FileReader(f2);
                            br = new BufferedReader(fr);
                            br.readLine();
                            String line=null;
                            while ((line=br.readLine())!=null){
                                String[] rlt=line.split(",",12);
                                records.add(new Model(rlt[0],rlt[1],rlt[2],rlt[3],rlt[4],rlt[5],rlt[6],rlt[7],rlt[8],rlt[9],rlt[10],rlt[11]));
                            }

                            br.close();
                            br.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }finally {

                        }
                    }
                }
            }
        }
        records.sort(Comparator.comparing(o -> o.crime_type));
        System.out.println("reading done...");
    }

    public void FeatureA(){
        crime_types.clear();
        for(Model m:records){
            CrimeType c=new CrimeType(m.crime_type);
            int index= Collections.binarySearch(crime_types,c );
            if(index<0){
                crime_types.add(c);
            }
        }
        System.out.println("all crime type is below:");
        for(CrimeType c:crime_types){
            System.out.print(c.type+"\t\t");
        }
        System.out.println();
    }

    public void FeatureB(String type){
        int index= Collections.binarySearch(crime_types,new CrimeType(type));
        if(index<0){
            System.out.println("donot find the type you specified.");
            return;
        }
        System.out.println("the detail crime of type "+type+" is below:");
        for(Model m:records){
            if(m.crime_type.equals(type)){
                System.out.println(m);
            }
        }
    }

    public void FeatureC(String month){
        boolean found=false;
        int count1=0,count2=0;
        for(Model m : records){
            if(m.month.equals(month)){
                found=true;
                if(m.last_outcome_category.equals("Under investigation")){
                    count1++;
                }else if(m.last_outcome_category.equals("Investigation complete; no suspect identified")){
                    count2++;
                }
            }
        }
        if(!found){
            System.out.println("donot found data of the month given.");
        }else{
            System.out.println("Count of Under investigation is: "+count1);
            System.out.println("Count of Investigation complete; no suspect identified is: "+count2);
        }
    }

    public void FeatureD(){
        lsoa_codes.clear();
        for (Model m:records){
            LSOACode l=new LSOACode(m.lsoa_code);
            int index= Collections.binarySearch(lsoa_codes,l);
            if(index<0){
                lsoa_codes.add(l);
                lsoa_codes.sort(Comparator.comparing(o -> o.code));
            }else{
                lsoa_codes.get(index).count++;
            }
        }
        LSOACode rlt=Collections.max(lsoa_codes,Comparator.comparing(o -> o.count));
        System.out.print("the LSOA code with the highest average total crime frequency is:");
        System.out.println(rlt.code+"\t"+rlt.count);
    }

    public void FeatureE(){
        lsoa_codes.clear();
        for (Model m:records){
            if(m.last_outcome_category.equals("Investigation complete; no suspect identified")){
                LSOACode l=new LSOACode(m.lsoa_code);
                int index= Collections.binarySearch(lsoa_codes,l);
                if(index<0){
                    lsoa_codes.add(l);
                    lsoa_codes.sort(Comparator.comparing(o -> o.code));
                }else{
                    lsoa_codes.get(index).count++;
                }
            }
        }
        LSOACode rlt=Collections.max(lsoa_codes,Comparator.comparing(o -> o.count));
        System.out.print("the LSOA code with the highest average unresolved crime frequency is:");
        System.out.println(rlt.code+"\t"+rlt.count);
    }

    public void FeatureF(String type){
        int index= Collections.binarySearch(crime_types,new CrimeType(type));
        if(index<0){
            System.out.println("donot find the type you specified.");
            return;
        }
        lsoa_codes.clear();
        for (Model m:records){
            if(m.crime_type.equals(type)){
                LSOACode l=new LSOACode(m.lsoa_code);
                index= Collections.binarySearch(lsoa_codes,l);
                if(index<0){
                    lsoa_codes.add(l);
                    lsoa_codes.sort(Comparator.comparing(o -> o.code));
                }else{
                    lsoa_codes.get(index).count++;
                }
            }
        }
        LSOACode rlt=Collections.max(lsoa_codes,Comparator.comparing(o -> o.count));
        System.out.print("the LSOA code with the highest crime frequency for a user-specified crime type "+type+" is:");
        System.out.println(rlt.code+"\t"+rlt.count);
    }
}
