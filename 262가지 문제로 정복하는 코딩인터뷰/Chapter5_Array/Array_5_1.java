import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Array_5_1 {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int pivotIdx, size;
    static int[] array;

    public static void main(String[] args) throws Exception {

        pivotIdx = Integer.parseInt(input.readLine());
        size = Integer.parseInt(input.readLine());

        array = new int[size];

        tokens = new StringTokenizer(input.readLine());
        for(int i=0;i<size;i++) {
            array[i] = Integer.parseInt(tokens.nextToken());
        }

        rearrange();
        System.out.println(Arrays.toString(array));
    }

    public static void rearrange() {
        int pivot = array[pivotIdx];

        // index 앞부분부터 차례대로 pivot보다 작은 수 채우기
        for(int i=0; i<size; i++) {
            for(int j=i+1;j<size;j++) {
                if(array[j]<pivot) {
                    swap(i,j);
                    break;
                }
            }
        }

        // index 뒷부분부터 차례대로 pivot보다 큰 수 채우기
        // 단, pivot보다 작은 index를 만나면 for문 종료
        for(int i=size-1; i>=0 && array[i]>=pivot; i--) {
            for(int j=i-1;j>=0 && array[j]>=pivot;j--) {
                if(array[j]>pivot) {
                    swap(i,j);
                    break;
                }
            }
        }
    }

    public static void swap(int x, int y) {
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }
}