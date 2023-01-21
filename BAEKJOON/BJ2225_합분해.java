import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2225_합분해 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static long dp[][]; // 행 : 숫자의 갯수 / 열 : 합이 될 수 
    static int N,K;
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		
		dp = new long[K+1][N+1];
		dp[0][0]=1;
		
		for(int r=1;r<=K;r++) {
			for(int c=0;c<=N;c++) {
				for(int m=0;m<=c;m++) {
					dp[r][c] += dp[r-1][c-m];
					dp[r][c] %= 1000000000;
				}
			}
		}
		
		System.out.println(dp[K][N]);
	}

}
