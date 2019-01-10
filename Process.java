
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.exit;


public class Process {
    public List<Model> records;

    public Process(){
        records=new ArrayList<>();
        String datapath = "/Users/ragdoll/Desktop/secound year/data";
        GetData(datapath);
    }

    public void GetData(String datapath){
    	final long startTime = System.currentTimeMillis();
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
                        }
                        final long duration = System.currentTimeMillis() - startTime;
                        System.out.println("running time" + duration + "ms");
                    }
                }
            }
        }
        records.sort(Comparator.comparing(o -> o.crime_type));
        System.out.println("reading done...");
    }

    private Map<String, List<Model>> getRecordG(){
        return records.stream().collect(Collectors.groupingBy(Model::getCrimeType));
    }

    private List<String> getCrimeType(){
        List<String> result=new ArrayList<>(getRecordG().keySet());
        Collections.sort(result);
        return result;
    }

    public void FeatureA(){
        List<String> ctype=getCrimeType();
        System.out.println("all crime type is below:");
        for(String o:ctype){
            System.out.println(o);
        }
        System.out.println();
    }

    public void FeatureB(){
        List<String> ctype=getCrimeType();
        if(ctype.size()==0){
            System.out.println("no crime type here\n");
            return;
        }
        for(int i=1;i<=ctype.size();i++){
            System.out.println(String.format("[%d]: %s",i,ctype.get(i-1)));
        }
        int type=IO.readInt("please specified the crime type above you want to display:",ctype.size());
        System.out.println("the detail crime of type "+ctype.get(type-1)+" is below:");
        for(Model m:records){
            if(m.crime_type.equals(ctype.get(type-1))){
                System.out.println(m);
            }
        }
        System.out.println();
    }

    public void FeatureC(){
        List<String> months=new ArrayList<>(records.stream().collect(Collectors.groupingBy(Model::getMonth)).keySet());
        if(months.size()==0){
            System.out.println("no month here\n");
            return;
        }
        Collections.sort(months);
        for(int i=1;i<=months.size();i++){
            System.out.println(String.format("[%d]: %s", i,months.get(i-1)));
        }
        int month=IO.readInt("please specified a month to display:",months.size());
        int count1=0,count2=0;
        for(Model m : records){
            if(m.month.equals(months.get(month-1))){
                if(m.last_outcome_category.equals("Under investigation")){
                    count1++;
                }else if(m.last_outcome_category.equals("Investigation complete; no suspect identified")){
                    count2++;
                }
            }
        }
        System.out.println("Count of Under investigation is: "+count1);
        System.out.println("Count of Investigation complete; no suspect identified is: "+count2);
        System.out.println();
    }

    public void FeatureD(){
        Map<String, List<Model>> collect = records.stream().collect(Collectors.groupingBy(Model::getLsoaCode));
        int maxcount=0;
        String code=null;
        for(Map.Entry<String,List<Model>> entrt:collect.entrySet()){
            if(entrt.getValue().size()>maxcount){
                maxcount=entrt.getValue().size();
                code=entrt.getKey();
            }
        }
        System.out.println(String.format("the LSOA code with the highest average total crime frequency is [code]: %s , [count]: %d", code,maxcount));
        String ans=IO.readString("show the detail? ('Y/y' for yes,others for no):");
        if(ans.toLowerCase().startsWith("y")){
            List<Model> value=collect.get(code);
            for(Model m:value){
                System.out.println(m);
            }
        }
        System.out.println();
    }

    public void FeatureE(){
        Map<String, List<Model>> collect = records.stream().filter(m -> m.last_outcome_category.equals("Investigation complete; no suspect identified")).collect(Collectors.groupingBy(Model::getLsoaCode));
        int maxcount=0;
        String code=null;
        for(Map.Entry<String,List<Model>> entrt:collect.entrySet()){
            if(entrt.getValue().size()>maxcount){
                maxcount=entrt.getValue().size();
                code=entrt.getKey();
            }
        }
        System.out.println(String.format("the LSOA code with the highest average unresolved crime frequency is [code]: %s , [count]: %d", code,maxcount));
        String ans=IO.readString("show the detail? ('Y/y' for yes,others for no):");
        if(ans.toLowerCase().startsWith("y")){
            List<Model> value=collect.get(code);
            for(Model m:value){
                System.out.println(m);
            }
        }
        System.out.println();
    }

    public void FeatureF(){
        List<String> ctype=getCrimeType();
        if(ctype.size()==0){
            System.out.println("no crime type here\n");
            return;
        }
        for(int i=1;i<=ctype.size();i++){
            System.out.println(String.format("[%d]: %s",i,ctype.get(i-1)));
        }
        int type=IO.readInt("please specified the crime type above you want to display:",ctype.size());

        Map<String, List<Model>> collect = records.stream().filter(m -> m.crime_type.equals(ctype.get(type-1))).collect(Collectors.groupingBy(Model::getLsoaCode));
        int maxcount=0;
        String code=null;
        for(Map.Entry<String,List<Model>> entrt:collect.entrySet()){
            if(entrt.getValue().size()>maxcount){
                maxcount=entrt.getValue().size();
                code=entrt.getKey();
            }
        }
        System.out.println(String.format("the LSOA code with the highest crime frequency for a user-specified crime type [%s] is [code]: %s , [count]: %d",ctype.get(type-1), code,maxcount));
        String ans=IO.readString("show the detail? ('Y/y' for yes,others for no):");
        if(ans.toLowerCase().startsWith("y")){
            List<Model> value=collect.get(code);
            for(Model m:value){
                System.out.println(m);
            }
        }
        System.out.println();
    }
}
