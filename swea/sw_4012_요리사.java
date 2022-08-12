import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class sw_4012_요리사 {
	static StringBuilder output = new StringBuilder();
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int T,N,n,result,up,down,cnt;
	static int[][] power;
	static int[] notChoosed; 
	public static void select(int nth, int[] choosed, int startIdx, boolean[] visited) {
		if(nth==choosed.length) {
			notChoosed = new int[n];
			int idx = 0;
			for(int i=0;i<N;i++) {
				if(!visited[i]) notChoosed[idx++]=i;
			}
			flavor(choosed, notChoosed);
//			cnt+=1;
			return;
		}
		for(int i=startIdx;i<N;i++) {
			choosed[nth]=i;
			visited[i]=true;
//			if(cnt<=up/down/2) select(nth+1,choosed,i+1,visited);
			select(nth+1,choosed,i+1,visited);
			visited[i]=false;
		}
	}
	public static void flavor(int[] choosed, int[] notChoosed) {

		int sumA=0;
		int sumB=0;

		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(i!=j) {
					sumA+=power[choosed[i]][choosed[j]];
					sumB+=power[notChoosed[i]][notChoosed[j]];					
				}
			}
		}
		result = Math.min(result, Math.abs(sumA-sumB));
	}
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(input.readLine());
		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = Integer.parseInt(input.readLine());
			power=new int[N][N];
			n=N/2;
			result=Integer.MAX_VALUE;
			
			up=1;
			down=1;
			cnt=0;
			for(int r=0;r<N;r++) {
				if(r>=n)up*=(r+1);
				if(r<n) down*=(r+1);
				tokens=new StringTokenizer(input.readLine());
				for(int c=0;c<N;c++) power[r][c] = Integer.parseInt(tokens.nextToken());
			}
			select(0,new int[n],1, new boolean[N]); // 0을 포함했는지 여부에 따라서 두 개로 나뉘면서 시작되므로 이것을 이미 포함했다고 가정하면 다른 반대쪽은 탐색 안 함!! 중요!!1
			output.append(String.format("#%d %d\n", test_case, result));
		}
		System.out.println(output);

	}

}