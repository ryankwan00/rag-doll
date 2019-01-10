import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	final long startTime = System.currentTimeMillis();
    	Process process=new Process();

        Scanner scan = new Scanner(System.in);
        String read;
        while (true){
            read = IO.readString("please enter feature set type(A-F,q for quit):");
            if(read.equals("q")){
                break;
            }
            switch (read){
                case "A":
                    process.FeatureA();
                    break;
                case "B":
                    process.FeatureB();
                    break;
                case "C":
                    process.FeatureC();
                    break;
                case "D":
                    process.FeatureD();
                    break;
                case "E":
                    process.FeatureE();
                    break;
                case "F":
                    process.FeatureF();
                    break;
            }
            final long duration = System.currentTimeMillis() - startTime;
            System.out.println("running time" + duration + "ms");
        }




    }
}
