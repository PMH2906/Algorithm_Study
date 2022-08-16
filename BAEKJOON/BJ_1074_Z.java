package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1074_Z {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int N, r, c, result=0;
	
	private static void find(int N, int r, int c) {
		if(N==1) return;
		if(r<N/2&&c<N/2) find(N/2, r, c);
		else if(r<N/2&&c>=N/2) {
			result+=(N*N)/4;
			find(N/2,r,c-N/2);
		}else if(r>=N/2&&c<N/2) {
			result+=((N*N)/4)*2;
			find(N/2,r-N/2,c);
		}else {
			result+=((N*N)/4)*3;
			find(N/2,r-N/2,c-N/2);
		}
	}
	
	public static void main(String[] args) throws Exception {
		tokens = new StringTokenizer(input.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		r = Integer.parseInt(tokens.nextToken());
		c = Integer.parseInt(tokens.nextToken());
		
		find((int)Math.pow(2, N), r, c);
		System.out.println(result);
	}
	

}
