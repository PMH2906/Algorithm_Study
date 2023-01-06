import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ10844_쉬운계단수 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static long dp[][] = new long[101][10];
    public static void main(String[] args) throws IOException {
        dp[0][1] = 1;
        for (int j = 1; j < 10; j++) dp[1][j] = 1;

        int n = Integer.parseInt(input.readLine());

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < 10; j++) {
                if (j == 1) dp[i][j] = (dp[i - 2][j] + dp[i - 1][j + 1])%1000000000;
                else if (j == 9) dp[i][j] = (dp[i - 1][j - 1])%1000000000;
                else dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1])%1000000000;
            }
        }
        long sum = 0;
        for (int j = 1; j < 10; j++) {
            sum += dp[n][j];
        }
        System.out.println(sum%1000000000);
    }
}
