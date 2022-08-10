package Algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_1010_다리놓기 {
	static StringBuilder output = new StringBuilder();
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N;
	static int M;
	static int T;
	static int [][] combiResult= new int[30][30];

	static int combination(int m, int n) {
		if(combiResult[m][n]>0) return combiResult[m][n];
		
		if(n==m || n==0) return combiResult[m][n]=1;
		
		return combiResult[m][n]=combination(m-1,n-1)+combination(m-1,n);
	}
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(input.readLine());

		for (int t = 0; t < T; t++) {
			tokens = new StringTokenizer(input.readLine());
			N = Integer.parseInt(tokens.nextToken());
			M = Integer.parseInt(tokens.nextToken());
			output.append(combination(M,N)+"\n");
		
		}
		System.out.println(output);
	}
}
