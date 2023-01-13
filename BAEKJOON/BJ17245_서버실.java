import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ17245_서버실 {

    static long N, computer[][], maxValue=Integer.MIN_VALUE, total=0,ans;
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(input.readLine());

        computer = new long[N][N];

        for(int r=0;r<N;r++) {
            tokens = new StringTokenizer(input.readLine());
            for(int c=0;c<N;c++) {
                computer[r][c]=Integer.parseInt(tokens.nextToken());
                maxValue=Math.max(maxValue,computer[r][c]);
                total+=computer[r][c];
            }
        }

        long left=0;
        long right=maxValue;
        long mid=0;
        while(left<=right){
//            System.out.println(mid+" "+left+" "+right);
            mid = (left+right)/2;
            long cnt = 0;
            for(int r=0;r<N;r++){
                for(int c=0;c<N;c++){
                    if(mid<=computer[r][c]){
                        cnt+=mid;
                    }else{
                        cnt+=computer[r][c];
                    }
                }
            }

            if(cnt>=total/2){
                ans=mid;
                right=mid-1;
            }else{
                left=mid+1;
            }
        }
        System.out.println(ans);
    }

}
