import java.util.Scanner;

public class con_2021573003_1{
    int i=0;
    public static void main (String [] args){
        con_2021573003_1 as = new con_2021573003_1();
        Scanner sc = null;
        try {
            System.out.print("Up to how much? :");
            sc = new Scanner(System.in);
            int input = sc.nextInt();
            System.out.println("Ouput : "+as.divideAndConqure(input));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }

    private int divideAndConqure(int input){
        int ret = 0;
        if(input <= 1) ret = 1;
        else if(input % 2 == 1) ret = divideAndConqure(input - 1) + input;
        else ret = divideAndConqure(input / 2) * 2 + (input / 2) * (input / 2);
        return ret;
    }
}