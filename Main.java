package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Process process=new Process();

        Scanner scan = new Scanner(System.in);
        String read;
        while (true){
            System.out.print("please enter feature set type(A-F,q for quit):");
            read = scan.nextLine();
            if(read.equals("q")){
                break;
            }
            switch (read){
                case "A":
                    process.FeatureA();
                    break;
                case "B":
                    process.FeatureA();
                    System.out.print("please specified the crime type above you want to display:");
                    read = scan.nextLine();
                    process.FeatureB(read);
                    break;
                case "C":
                    System.out.print("please specified a month to display:");
                    read = scan.nextLine();
                    process.FeatureC(read);
                    break;
                case "D":
                    process.FeatureD();
                    break;
                case "E":
                    process.FeatureE();
                    break;
                case "F":
                    process.FeatureA();
                    System.out.print("please specified the crime type above you want to display:");
                    read = scan.nextLine();
                    process.FeatureF(read);
                    break;
            }
        }




    }
}
