import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 1.탐색하는 인덱스 기준 왼쪽에서 가장 큰 숫자, 오른쪽에서 가장 큰 숫자 찾기
// 2.왼쪽 오른쪽 가장 큰 숫자 중 작은 숫자 - 현재 인덱스 >0 보다 크면 총 물의 양을 합산
// 3.가장 양 옆은 물이 안 고이므로 탐색 인덱스에서는 제외하지만, 오른쪽 왼쪽 가장 큰 값을 탐색할 때는 포함해주기!!! 
public class BJ14719_빗물 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int N, M, rain[],ans;
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		rain = new int[M];
		tokens = new StringTokenizer(input.readLine());
		for(int m=0;m<M;m++) {
			rain[m] = Integer.parseInt(tokens.nextToken());
		}
		
		int leftMax=rain[0], rightMax=Integer.MIN_VALUE;
		
		for(int m=1;m<M-1;m++) {
			leftMax = Math.max(leftMax, rain[m-1]); //탐색하는 인덱스 기준 왼쪽에서 가장 큰 숫자
			rightMax=Integer.MIN_VALUE;
			for(int i=m+1;i<M;i++) {
				rightMax=Math.max(rightMax, rain[i]); // 오른쪽에서 가장 큰 숫자
			}
			// 2.왼쪽 오른쪽 가장 큰 숫자 중 작은 숫자 - 현재 인덱스 >0 보다 크면 총 물의 양을 합산
			ans+=( Math.min(leftMax, rightMax)-rain[m]>0?Math.min(leftMax, rightMax)-rain[m]:0);
		}
		System.out.println(ans);
		
		
	}

}
