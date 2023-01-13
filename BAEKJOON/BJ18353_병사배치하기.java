import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ18353_병사배치하기 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int N, num[], LIS[];
	
	public static void main(String[] args) throws Exception, IOException {
		N = Integer.parseInt(input.readLine());
		num = new int[N];
		LIS = new int[N];
		tokens = new StringTokenizer(input.readLine());
		
		for(int i=0;i<N;i++) num[i]=Integer.parseInt(tokens.nextToken());
		
		int len = 0; //길이 
		
		for(int i=N-1;i>=0;i--) {
			// (-(insertion point) - 1)
			int idx = Arrays.binarySearch(LIS, 0, len, num[i]);
	
			if(idx==0) continue; // 이미 LIS에 포함되므 갱신해도 의미X
			
			int insertPos = Math.abs(idx)-1; // 맨뒤 또는 기존 원소 자리 대체 
			LIS[insertPos]=num[i];
			
			if(insertPos == len) len++; // 맨뒤 추가된 경우 길이 증가 
		}
		System.out.println(N-len);
	}

}
