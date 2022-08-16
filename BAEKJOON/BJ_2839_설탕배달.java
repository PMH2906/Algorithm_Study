package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2839_설탕배달 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int N, result;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(input.readLine());
		for(int i=N/5;i>=0;i--) {
			if((N-(i*5))%3==0) {
				result+=i+(N-(i*5))/3;
				System.out.println(result);
				System.exit(0);
			}
		}
		System.out.println(-1);
	}

}
