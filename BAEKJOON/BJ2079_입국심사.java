import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 매우 어려움!!!!!!!!!!!!! 이분탐색, 매개탐
public class BJ2079_입국심사 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N, time[];
	static long ans, M; // long은 64bit 정수이며, int는 32bit정수임!!!!!! 웬만한 범위의 int형은 long으로 표현 가능 
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		time = new int[N];
		
		int timeMax = Integer.MIN_VALUE;
		
		for(int i=0;i<N;i++) {
			time[i] = Integer.parseInt(input.readLine());
			timeMax = Math.max(timeMax, time[i]);
		}
		
		long left=0, right = M * timeMax; // 이분 탐색을 위해 나올 수 있는 최소값 : left 최댓값 : right로 설정 
//		System.out.println(right+" "+left);
		while(right>=left) { // 최솟값이 최대값을 넘을 경우에만 종료.
//			System.out.println(right+" "+left); 
			long mid=left+(right-left)/2; // 이분탐색을 위한 중간값 
			long cnt = 0;
			
			for(int i=0;i<N;i++) {
				cnt+=(mid/time[i]); // mid초 동안 x시간이 걸리는 심사관은 총 mid/x명을 처리 가능 => 각 합을 모두 더한값! => cnt
			}
			if(cnt>=M) { // cnt가 m명과 같거나 넘는다면 최소값이 아니므로 최대값 -1  
				ans=mid; // 미리 최솟값 저장(답) 한 후 right-1처리!!!!!!!!! 
				right=mid-1; 
			}else { // 반대로 m명 보다 작으면 최솟값 +1 
				left=mid+1; 
			}
		}
		System.out.println(ans);
	}

}
