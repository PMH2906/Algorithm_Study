import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2352_반도체설계 {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, port[];

    public static void main(String[] args) throws IOException {

        N=Integer.parseInt(input.readLine());
        port=new int[N];

        tokens=new StringTokenizer(input.readLine());
        for(int n=0;n<N;n++) {
            port[n]=Integer.parseInt(tokens.nextToken());
        }

        int LIS[]=new int[N];
        int size=0;

        for(int n=0;n<N;n++) {
            int insertIndex= Arrays.binarySearch(LIS, 0, size, port[n]);

            if(insertIndex>=0) continue;

            insertIndex=Math.abs(insertIndex)-1;
            LIS[insertIndex]=port[n];

            if(size==insertIndex) size++;
        }

        System.out.println(size);
    }
}
