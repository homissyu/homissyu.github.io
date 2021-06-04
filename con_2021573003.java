import java.util.ArrayList;
import java.util.Scanner;

public class con_2021573003 {
    private ArrayList<Integer> aList = new ArrayList<Integer>();
    public static void main (String [] args){
        con_2021573003 as = new con_2021573003();
        Scanner sc = null;
        try {
            sc = new Scanner(System.in);
            System.out.println("\nFirst, select assignment");
            System.out.println("1. Up to Sum");
            System.out.println("2. Rectangle in Histogram\n");
            System.out.print("Assignment no : ");
            switch(sc.nextInt()){
                case 1:
                    System.out.print("\nUp to how much? : ");
                    int input = sc.nextInt();
                    System.out.println("Ouput : "+as.up2Sum(input));
                    System.out.println();
                break;
                case 2:
                    System.out.print("\nInput Count : ");
                    int cnt = 0;
                    if(sc.hasNextInt()){
                        cnt = sc.nextInt();
                        System.out.print("Input Array : ");
                        for(int i=0 ; i < cnt ; i++){
                            as.aList.add(sc.nextInt()); // 입력된 숫자목록을 ArrayList에 추가한다.
                        }
                        as.aList.add(0); //중간값과 양쪽 구간 값을 비교하기 위한 리스트이므로 최소입력값+1개의 길이가 필요함.
                        System.out.println("Ouput : "+as.getMaxRectangle(0,cnt)); //0부터 입력된 리스트의 마지막 수까지의 구간에 대해 분할정복법으로 최대 넓이를 구한다.
                        System.out.println();
                    } else {
                        throw new Exception("ArrayLengthException : Only numbers are allowed.");
                    }
                break;
                default:
                    System.out.println("Please check the usage and try again.");
                    System.exit(0);
                break;
            };
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }

    private int up2Sum(int input){
        int ret = 0;
        if(input <= 1) ret = input; // 1보다 작을 때 입력값과 동일하다.
        else if(input % 2 == 1) ret = up2Sum(input - 1) + input; // 홀수이면 마지막 값을 제외하고 재귀호출한 후 마지막에 더한다.
        else ret = up2Sum(input / 2) * 2 + (input / 2) * (input / 2); // (1 + 2 + ... + n//2) * 2 + (n//2 * n//2) 
        return ret;
    }

    private int getMaxRectangle(int startIdx, int endIdx) {
        int ret = aList.get(startIdx); // 리턴값 선언 및 초기화

        if(startIdx == endIdx) return ret; // 시작과 끝이 같다면 하나의 막대이며 입력된 높이와 넓이가 같으므로 배열에 입력되어 있는 높이값을 반환한다.
        
        int mid = (startIdx + endIdx) / 2; // 입력된 배열의 중앙값을 찾는다.
        
        int height = aList.get(mid); // 입력된 배열 중 최고높이값, 중간값으로 초기화됨.
        
        int leftIdx = mid -1 ; // 왼쪽으로 하나 확장한 인덱스
        int rightIdx = mid + 1; // 오른쪽으로 하나 확장한 인덱스

        while(startIdx <= leftIdx || rightIdx <= endIdx){ // 양쪽으로 확장할 수 있을 때 까지
            if(startIdx > leftIdx || aList.get(leftIdx)<= aList.get(rightIdx)){ //오른쪽으로 확장
                height = Math.min(height, aList.get(rightIdx));
                rightIdx++;
            }else{ //왼쪽으로 확장
                height = Math.min(height, aList.get(leftIdx));
                leftIdx--;
            }
            ret = Math.max(ret, (rightIdx - leftIdx - 1) * height);	// rightIdx부터 leftIdx사이에서 모두 걸친 최대값의 직사각형을 찾는다.
        }

        ret = Math.max(ret, Math.max(getMaxRectangle(startIdx, mid), getMaxRectangle(mid+1, endIdx))); // 재귀호출하여 분할된 오른쪽, 왼쪽과 중앙에 걸친 직사각형 중 최대값을 찾는다.
        return ret;
    }
}