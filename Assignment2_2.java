import java.util.Scanner;

public class Assignment2_2 {
    private int[] arr = null;
    public static void main (String [] args){
        Assignment2_2 as = new Assignment2_2();
        Scanner sc = null;
        try {
            System.out.print("Input Count : ");
            sc = new Scanner(System.in);
            int cnt = 0;
            if(sc.hasNextInt()){
                cnt = sc.nextInt();
                as.arr = new int[cnt+2];
                System.out.print("Input Array : ");
                for(int i=0 ; i < cnt ; i++){
                    as.arr[i] = sc.nextInt();
                }
                System.out.println("Ouput:"+as.divideAndConqure(0,cnt));
            } else {
                throw new Exception("배열의 수는 숫자만 입력해 주세요.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }

    private int divideAndConqure(int startIdx, int endIdx) {
        int ret = 0;
        if(startIdx == endIdx) return arr[startIdx]; 
        else { 
            int mid = (startIdx + endIdx) / 2; 
            int max = Math.max(divideAndConqure(startIdx, mid), divideAndConqure(mid+1, endIdx)); 
            int min = Math.min(arr[mid], arr[mid+1]); 
            // int min_height=Math.min(al.get(mid),al.get(mid+1)); 
            int tmpMax = 2 * min; 
            int lo = mid; 
            int hi = mid + 1; 
            while(lo > startIdx || hi < endIdx){ 
                if(lo == startIdx || arr[hi+1] > arr[lo-1]){ 
                    hi++; 
                    min = Math.min(min, arr[hi]); 
                }else{ 
                    lo--; 
                    min = Math.min(min, arr[lo]); 
                }
                tmpMax = Math.max(tmpMax, (hi-lo+1) * min); 
            } 
            ret = Math.max(max, tmpMax); 
        }
        return ret;
    }
}