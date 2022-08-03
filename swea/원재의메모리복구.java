package s;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 원재의메모리복구 {
	
	static StringBuilder output = new StringBuilder();
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static int result;
	static String check;
	public static void main(String args[]) throws Exception
	{
	
		int T;
		T=Integer.parseInt(input.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			String[] memory = input.readLine().split("");
			
			check = "0";
			result=0;
			
			for(int i=0;i<memory.length;i++) {
				if(!check.equals(memory[i])) {
					result+=1;
					check=memory[i];
				}
			}
			output.append(String.format("#%d %d\n", test_case, result));
		}
		System.out.println(output);
	}
}
