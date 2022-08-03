import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Flatten {
	static StringBuilder output = new StringBuilder();
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int result;
	static int [] store = new int[100];
	
	static void move(int cnt) {
		if(cnt==0) {
			Arrays.sort(store);
			result = store[99]-store[0]; 
			return;
		}
		Arrays.sort(store);
		store[0]+=1;
		store[99]-=1;
		move(cnt-1);
		
	}
	public static void main(String args[]) throws Exception
	{
		
		int T = 10;

		for(int test_case = 1; test_case <= T; test_case++)
		{
			result=0;
			
			int cnt = Integer.parseInt(input.readLine());
			
			tokens = new StringTokenizer(input.readLine());
			
			for(int i=0;i<store.length;i++) {
				store[i] = Integer.parseInt(tokens.nextToken());
			}
			move(cnt);
			output.append(String.format("#%d %d\n", test_case, result));
		}
		System.out.println(output);
	}
}
