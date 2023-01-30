import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1240_노드사이의거리 {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder output = new StringBuilder();
	static int N, M, distAll[][];
	static final int INF = 10000000;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		distAll = new int[N+1][N+1];
		
		for(int n=0;n<=N;n++) {
			Arrays.fill(distAll[n], INF);
			System.out.println(distAll[n][1]);
		}
		
		for(int n=0;n<N-1;n++) {
			tokens = new StringTokenizer(input.readLine());
			
			int to = Integer.parseInt(tokens.nextToken());
			int from = Integer.parseInt(tokens.nextToken());
			int dist = Integer.parseInt(tokens.nextToken());
			
			distAll[to][from] = dist;
			distAll[from][to] = dist;
			
		}
		
		for(int k=1;k<=N;k++) {
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=N;j++) {
					if(distAll[i][j]>distAll[i][k]+distAll[k][j]) {
						distAll[i][j] = distAll[i][k]+distAll[k][j];
					}
				}
			}
		}
		
		for(int m=0;m<M;m++) {
			tokens = new StringTokenizer(input.readLine());
			
			int to = Integer.parseInt(tokens.nextToken());
			int from = Integer.parseInt(tokens.nextToken());
			
			output.append(distAll[to][from]+"\n");
		}
		System.out.println(output);
	}

}
