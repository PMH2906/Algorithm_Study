import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ2294_동전2 {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int dp[] = new int[10001];
    static int N, K;
    static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {

        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());
        Arrays.fill(dp,Integer.MAX_VALUE-1);
        dp[0]=0;

        for(int i=0;i<N;i++) {
            list.add(Integer.parseInt(input.readLine()));
        }
        Collections.sort(list);

        for(int k=1;k<=K;k++) {
            for(int value : list) {
                if(k<value) break;
                dp[k]=Math.min(dp[k-value]+1, dp[k]);
            }
        }

        if (dp[K] == Integer.MAX_VALUE-1)  System.out.print(-1);
        else System.out.print(dp[K]);
    }
}
