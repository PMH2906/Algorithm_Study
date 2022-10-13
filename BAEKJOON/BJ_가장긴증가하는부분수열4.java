package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_가장긴증가하는부분수열4 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int N;
	static int[] num, dp, dpIdx, preIdx;
	static int size;
	public static void main(String[] args) throws Exception, IOException {
		N = Integer.parseInt(input.readLine());
		
		tokens = new StringTokenizer(input.readLine());
		
		num = new int[N];
		dp = new int[N];
		dpIdx = new int[N];
		preIdx = new int[N];
		size = 0;
		for(int i=0;i<N;i++) num[i] = Integer.parseInt(tokens.nextToken());
		
		for(int i=0;i<N;i++) {
			int insertPoint = Arrays.binarySearch(dp, 0,size,num[i]);
			
			if(insertPoint>=0) continue;
			
			insertPoint = Math.abs(insertPoint)-1;
			
			dp[insertPoint]=num[i];
			if (insertPoint == size)size++;
			
			dpIdx[insertPoint]=i; // dp에서 값이 갱신될 때 해당 갱신된 값의 index저장
			preIdx[i] = insertPoint==0? -1: dpIdx[insertPoint-1]; // dp값이 갱신될 때 현재 preIdx에 그 전 dpIndex 위치를 저장(나중에 순서대로 수열 출력 위해
			 
		}
		System.out.println(size);
		
		
		// 10 20 10 30 40 50 7 8
		// DP : 10 20 30 40 50 -> 7과 8 탐색 후 -> 7 8 30 40 50이 되면서 잘못된 수열 출력됨. 이것을 방지하기 위해 아래와 같은 방법으로 출력
		// dpIdx : 1 2 3 4 5 ->  7과 8 탐색 후 -> 6 7 3 4 5
		// preIdx : -1 1 2 3 4 -1 6 
		
		int idx=dpIdx[size-1]; // 가장 긴 수열의 마지막 index 찾기(뒤에서부터 탐색)
		Stack<Integer> stack = new Stack<>();
		while(idx!=-1) { // -1이 오면 해당 수열은 종료된 것
			stack.add(num[idx]); //가장 긴 수열의 마지막 index의 위치인 num 삽입
			idx=preIdx[idx];  // 해당 index와 연결된 전 인텍스로 갱신
		}
		while(!stack.isEmpty()) System.out.print(stack.pop()+" ");

	}

}
