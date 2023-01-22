import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2579_계단오르기 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, score[], dp[];
	public static void main(String[] args) throws Exception, IOException {
		N = Integer.parseInt(input.readLine());
		
		score = new int[301];
		dp = new int[301]; // 최고점 누적. 계단갯수가 3보다 작을 경우도 있으므로 초기를 301로 고정 
		for(int n=1;n<=N;n++) {
			score[n] = Integer.parseInt(input.readLine());
		}
		
		dp[1] = score[1];
		dp[2] = score[1]+score[2];
		dp[3] = Math.max(score[1]+score[3], score[2]+score[3]); // 1->3과 2->3을 비교 
		
		for(int n=4;n<=N;n++) {
			// n-1 -> n을 갈 경우에는 n-1 이전에 n-3만 올 수 있으므로 dp[n-3]+score[n-1]로 계산해준다!! 
			// n-2 -> n을 갈 경우에는 n-2 이전에 무엇이 왔든 상관없으므로 dp[n-2]로 확인 
			dp[n]= Math.max(score[n-1]+dp[n-3],dp[n-2])+score[n]; 
		}
		
		System.out.println(dp[N]);
	}

}
