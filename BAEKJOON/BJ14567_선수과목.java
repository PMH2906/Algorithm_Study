import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ14567_선수과목 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder output = new StringBuilder();
    static int N, M,dp[];
    static List<Integer>[] parents;
    
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		parents = new ArrayList[N+1];
		dp = new int[N+1];
		
		for(int n=1;n<=N;n++) {
			parents[n] = new ArrayList<Integer>();
			parents[n].add(0); // 초기
		}
		
		for(int m=0;m<M;m++) {
			tokens = new StringTokenizer(input.readLine());
			int first = Integer.parseInt(tokens.nextToken());
			int second = Integer.parseInt(tokens.nextToken());
			parents[second].add(first);	
		}
		for(int n=1;n<=N;n++) {
			for(int first : parents[n]) { // 선수과목 탐색 
				dp[n] = Math.max(dp[n],dp[first]+1); // 현재 선수 과목 학기와 지금 탐색하는 선수과목 학기를 비교해서 큰 걸로 갱신 
			}
			output.append(dp[n]+" ");
		}
		System.out.println(output);
	}

}
