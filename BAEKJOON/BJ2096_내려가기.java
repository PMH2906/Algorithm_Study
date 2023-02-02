import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2096_내려가기 {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N, scoreMin[][], scoreMax[][];
	
	public static void main(String[] args) throws Exception, IOException {
		N = Integer.parseInt(input.readLine());
		
		scoreMax = new int[N+1][3];
		scoreMin = new int[N+1][3];
		
		for(int n=1;n<=N;n++) {
			tokens = new StringTokenizer(input.readLine());
			int f = Integer.parseInt(tokens.nextToken());
			int s = Integer.parseInt(tokens.nextToken());
			int t = Integer.parseInt(tokens.nextToken());
			
			scoreMax[n][0] = Math.max(scoreMax[n-1][0], scoreMax[n-1][1]) + f; // 현재 첫번째 칸일때, 그 전 줄의 칸은 1,2 만 가능!! 그 중 큰 걸로!!
			scoreMax[n][1] = Math.max(Math.max(scoreMax[n-1][0], scoreMax[n-1][1]), scoreMax[n-1][2]) + s;
			scoreMax[n][2] = Math.max(scoreMax[n-1][1], scoreMax[n-1][2]) + t;
			
			scoreMin[n][0] = Math.min(scoreMin[n-1][0], scoreMin[n-1][1]) + f;
			scoreMin[n][1] = Math.min(Math.min(scoreMin[n-1][0], scoreMin[n-1][1]), scoreMin[n-1][2]) + s;
			scoreMin[n][2] = Math.min(scoreMin[n-1][1], scoreMin[n-1][2]) + t;
			
		}
		Arrays.sort(scoreMax[N]);
		Arrays.sort(scoreMin[N]);
		System.out.println(scoreMax[N][2]+" "+scoreMin[N][0]);

	}

}
