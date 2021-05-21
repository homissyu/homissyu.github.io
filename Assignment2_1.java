import java.util.Scanner;

public class Assignment2_1{
    public static void main (String [] args){
        Assignment2_1 as = new Assignment2_1();
        try {
            System.out.print("Input:");
            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();
            System.out.println("Ouput:"+as.divideAndConqure(input));
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Exception Occured:"+e.getLocalizedMessage());
        }
    }

    private int divideAndConqure(int input){
        int ret = 0;
        if(input <= 1) ret = 1;
        else if(input%2==1) ret = divideAndConqure(input - 1) + input;
        else ret = divideAndConqure(input/2)*2 + (input/2)*(input/2);
        return ret;
    }
}