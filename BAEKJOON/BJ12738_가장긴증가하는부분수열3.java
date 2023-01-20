import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ12738_가장긴증가하는부분수열3 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static long LIS[],num[];
	static int N;
	
	public static void main(String[] args) throws Exception, IOException {
		N = Integer.parseInt(input.readLine());
		num = new long[N];
		LIS = new long[N];
		
		tokens = new StringTokenizer(input.readLine());
		
		for(int n=0;n<N;n++) {
			num[n] = Long.parseLong(tokens.nextToken());
		}
		
		int size = 0;
		for(int n=0;n<N;n++) {
			int idx = Arrays.binarySearch(LIS,0,size,num[n]);
			System.out.println(idx);
			if(idx>=0) continue; // 가장 작은 숫자와 같은 숫자는 insert가 -1이므로 idx가 0이 나옴. 따라서 0이상인 양의 정수는 무조건 넘어가기!!!! 
			
			int insert = Math.abs(idx)-1;
			
			LIS[insert] = num[n];
			
			if(insert==size) size++;
		}
		System.out.println(size);
	}

}
