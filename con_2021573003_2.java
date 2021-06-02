import java.util.Scanner;

public class con_2021573003_2 {
    private int[] arr = null;
    public static void main (String [] args){
        con_2021573003_2 as = new con_2021573003_2();
        Scanner sc = null;
        try {
            System.out.print("Input Count : ");
            sc = new Scanner(System.in);
            int cnt = 0;
            if(sc.hasNextInt()){
                cnt = sc.nextInt();
                as.arr = new int[cnt+2]; //중간값과 양쪽 구간 값을 비교하기 위한 배열이므로 최소입력값+2개의 길이가 필요함.
                System.out.print("Input Array : ");
                for(int i=0 ; i < cnt ; i++){
                    as.arr[i] = sc.nextInt(); //입력된 수를 배열에 할당함.
                }
                System.out.println("Ouput:"+as.divideAndConqure(0,cnt)); //0부터 입력된 배열의 마지막 수까지의 구간에 대해 분할정복법으로 최대 넓이를 구한다.
            } else {
                throw new Exception("ArrayLengthException : Only numbers are allowed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }

    private int divideAndConqure(int startIdx, int endIdx) {
        int ret = 0;
        if(startIdx == endIdx) return arr[startIdx]; // 시작과 끝이 같다면 하나의 막대이며 입력된 높이와 넓이가 같으므로 배열에 입력되어 있는 높이값을 반환한다.
        else { 
            int mid = (startIdx + endIdx) / 2; // 입력된 배열의 중앙값을 찾는다.
            int low = mid; //확장을 위한 기준값으로 중간값을 할당한다. 
            int high = mid + 1; //확장을 위한 기준값으로 중간값 오른편 값을 할당한다.

            int max = Math.max(divideAndConqure(startIdx, mid), divideAndConqure(mid+1, endIdx)); // 중앙값을 중심으로 양쪽으로 나뉘어 재귀호출하여 최대 높이값을 찾는다.
            int min = Math.min(arr[low], arr[high]); //나뉘어진 위치를 기준으로 양쪽 값을 비교하여 작은 값을 찾는다.
            
            ret = 2 * min; // 재귀호출의 마지막 상태인 막대가 2개인 경우 반환값은 두 막대 중 낮은 값*2이다.

            //분할된 배열의 값이 3개 이상인 경우 아래와 같이 확장하며 계산한다.
            while(low > startIdx || high < endIdx){ // 분할한 배열의 시작값이 중간값보다 작거나 끝값이 중간값+1보다 큰 경우 수행한다.
                if(low == startIdx || arr[high+1] > arr[low-1]){ // 시작값이 중간값과 같거나 
                    high++; 
                    min = Math.min(min, arr[high]); 
                }else{ 
                    low--; 
                    min = Math.min(min, arr[low]); 
                }
                ret = Math.max(ret, (high-low+1) * min); 
            } 
            ret = Math.max(max, ret); 
        }
        return ret;
    }
}