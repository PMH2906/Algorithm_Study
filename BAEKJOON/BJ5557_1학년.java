import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ5557_1학년 {
    /**
     * 해당 문제를 DFS로 접근하면 N이 3 이상 100 이하이므로 최대 2의 98승으로 시간 초과.
     * 따라서 DP로 접근해야한다.
     * **/

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N;
    static int[] num;
    static long[][] dp;

    public static void main(String[] args) throws IOException {

        N=Integer.parseInt(input.readLine());
        dp=new long[N][21];
        num=new int[N];

        tokens=new StringTokenizer(input.readLine());
        for(int n=0;n<N;n++) {
            num[n]=Integer.parseInt(tokens.nextToken());
        }

        // 처음 숫자 초기화
        dp[0][num[0]]=1;

        for(int n=1;n<N-1;n++) {
            for(int col=0;col<dp[0].length;col++) {
                if(dp[n-1][col]>0) {
                    int plus=col+num[n];
                    int minus=col-num[n];
                    if(minus>=0) {
                        // 누적된 값을 더해야 함.
                        dp[n][minus]+=dp[n-1][col];
                    }
                    if(plus<=20) {
                        // 누적된 값을 더해야 함.
                        dp[n][plus]+=dp[n-1][col];
                    }
                }
            }
        }

        System.out.println(dp[N-2][num[N-1]]);
    }
}
