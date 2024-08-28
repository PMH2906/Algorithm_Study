import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ15486_퇴사2 {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, ans=Integer.MIN_VALUE;
    static int[] time, price, dp;

    public static void main(String[] args) throws IOException {
        N=Integer.parseInt(input.readLine());
        time=new int[N+1];
        price=new int[N+1];
        dp=new int[N+2];

        for(int n=1;n<=N;n++) {
            tokens=new StringTokenizer(input.readLine());
            time[n]=Integer.parseInt(tokens.nextToken());
            price[n]=Integer.parseInt(tokens.nextToken());
        }

        for(int n=1;n<=N;n++) {

            if(n+time[n]<=N+1) {
                dp[n+time[n]]=Math.max(price[n]+dp[n], dp[n+time[n]]);
                ans=Math.max(dp[n+time[n]],ans);
            }

            // 상담을 안 하고 넘어가는 것도 존재. 해당 날짜에 상담하는 것보다 다음 날 상담하는 비용이 크다면 해당 날짜는 패스할 수 있음
            // 따라서 dp[현재]와 dp[다음 날]을 비교하여 누적 비용 많은 걸로 대체
            dp[n+1]=Math.max(dp[n+1],dp[n]);
        }

        System.out.println(dp[N+1]);
    }
}
