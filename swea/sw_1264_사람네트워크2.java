package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class sw_1264_사람네트워크2 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int T, N;
	static int [][] map;
	static int INF = 9999999, min;

	public static void main(String[] args) throws Exception, IOException {
		T = Integer.parseInt(input.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			tokens = new StringTokenizer(input.readLine());
			
			N=Integer.parseInt(tokens.nextToken());
			map=new int[N][N];
			min=Integer.MAX_VALUE;
			for(int r=0;r<N;r++) {
				for(int c=0;c<N;c++) {
					map[r][c] = Integer.parseInt(tokens.nextToken());
					if(map[r][c]==0&&r!=c) map[r][c] =INF;
				}
			}
			
			for(int k=0;k<N;k++) {
				for(int i=0;i<N;i++) {
					if(k==i) continue;
					for(int j=0;j<N;j++) {
						if(k==j||i==j) continue;
						if(map[i][j]>map[i][k]+map[k][j])
							map[i][j]=map[i][k]+map[k][j];
					}
				}
			}
			
			for(int r=0;r<N;r++) {
				int sum=0;
				for(int c=0;c<N;c++) {
					sum+=map[r][c];
				}
				min=Math.min(min, sum);
			}
			output.append(String.format("#%d %d\n", test_case, min));

		}
		System.out.println(output);
	}

}
