import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ17394_핑거스냅 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder output = new StringBuilder();
    static int T, N, A, B, cnt[];
    static boolean divisor[] = new boolean[1000001];
   
	public static void main(String[] args) throws Exception, IOException {
		T = Integer.parseInt(input.readLine());
		
		findDivisor();
		//System.out.println(Arrays.toString(divisor));
		for(int t=0;t<T;t++) {
			cnt = new int[1000001];

			tokens = new StringTokenizer(input.readLine());
			
			N = Integer.parseInt(tokens.nextToken());
			A = Integer.parseInt(tokens.nextToken());
			B = Integer.parseInt(tokens.nextToken());
			
			output.append(bfs()+"\n");
		}
		System.out.println(output);
		
		
	}
	private static int bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(N);

		while(q.size()>0) {
			int cur = q.poll();
			
			if(A<=cur&&cur<=B&&divisor[cur]) return cnt[cur];
			
			int next = cur/2;
			if(0<=next&&next<=1000000&&cnt[next]==0) {
				cnt[next]=cnt[cur]+1;
				q.add(next);
			}
			
			next = cur/3;
			if(0<=next&&next<=1000000&&cnt[next]==0) {
				cnt[next]=cnt[cur]+1;
				q.add(next);
			}
			next = cur+1;
			if(0<=next&&next<=1000000&&cnt[next]==0) {
				cnt[next]=cnt[cur]+1;
				q.add(next);
			}
			next = cur-1;
			if(0<=next&&next<=1000000&&cnt[next]==0) {
				cnt[next]=cnt[cur]+1;
				q.add(next);
			}
			
		}
		return -1;
		
		
		
	}
	private static void findDivisor() {
		
		Arrays.fill(divisor, true); // 모두 소수로 초기화 
		//System.out.println(Arrays.toString(divisor));
		for(int i=2; i<=Math.sqrt(1000000);i++) {
			// 1. 탐색하지 않은 남은 수 중 가장 작은 수 탐색
			// 2. 해당 수의 배수를 모두 false로 갱신 -> 소수 아니므로 	
			if(divisor[i]==true) { // 소수인 경우 (남은 수 중 작은 수 
				int j = 2; // 배수 구할 수 있도록 초기화 
				while(i*j<=1000000) {
					divisor[i*j] = false; // 배수이면 소수가 아니므로 false 
					j+=1; // 다음 배수 구할 수 있도록 
				}
			}
		}
		
//		for(int i=2;i<100;i++) {
//			if(divisor[i]) System.out.println("소수"+i);
//		}
		
	}
}
