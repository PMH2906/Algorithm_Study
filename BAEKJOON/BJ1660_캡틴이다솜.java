import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1660_캡틴이다솜 {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int dp1[], dp2[], N;
	public static void main(String[] args) throws Exception, IOException {
		N = Integer.parseInt(input.readLine());
		
		dp1 = new int[N+1];
		dp2 = new int[N+1];
		
		for(int i=1;i<=N;i++) {
			dp1[i] += dp1[i-1]+(i*(i+1))/2; // 정삼각형에 필요한 갯수 ex) 2 = (2*3/2)=3 
			
		}
		
		Arrays.fill(dp2, Integer.MAX_VALUE);
		//System.out.println(Arrays.toString(dp1));
		dp2[0] = 0;
		dp2[1] = 1;
		
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				if(dp1[j]>i) break;
				dp2[i] = Math.min(dp2[i], dp2[i-dp1[j]]+1);
			}
		}
		System.out.println(dp2[N]);
	}
	
}
