package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class sw_3307_최장증가부분수열 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int T,N, arr[], LIS[];
	public static void main(String[] args) throws Exception, IOException {
		T = Integer.parseInt(input.readLine());
		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = Integer.parseInt(input.readLine());
			
			tokens = new StringTokenizer(input.readLine());
			
			arr=new int[N];
			LIS=new int[N];
			for(int i=0;i<N;i++) arr[i]=Integer.parseInt(tokens.nextToken());
			
			int size = 0;
			
			for(int i=0;i<N;i++) {
				int insertIdx = Arrays.binarySearch(LIS,0,size,arr[i]);
				
				if(insertIdx>0) continue;
				
				insertIdx = Math.abs(insertIdx)-1;
				LIS[insertIdx] = arr[i];
				
				if(insertIdx==size) size++;
			}
			output.append(String.format("#%d %d\n", test_case,size));
		}
		System.out.println(output);
	}

}
