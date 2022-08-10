import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_17406_배열돌리기4 {
	static StringBuilder output = new StringBuilder();
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N, M, K, MinSum = Integer.MAX_VALUE;
	static List<int[]>[] loc;
	static int[][] array, calInfo, original;
	static int[] choosed, src;
	static boolean[] visited;
	static List<int[]> calOrderInfo = new ArrayList<int[]>();
	static int[][] deltas = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } }; // 하, 우, 상, 좌

	// 연산 순서
	public static void calOrder(int nth, int[] choosed, boolean[] visited) {
		if (nth == choosed.length) {
			int[] line = new int[K];
			for(int i=0;i<choosed.length;i++)
				line[i] = choosed[i];
			calOrderInfo.add(line);	
			return;
		}
		for (int i = 0; i < src.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				choosed[nth] = src[i];
				calOrder(nth + 1, choosed, visited);
				visited[i] = false;
			}
		}

	}

	public static void main(String[] args) throws Exception {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		array = new int[N][M];
		original = new int[N][M];
		for (int r = 0; r < N; r++) {
			tokens = new StringTokenizer(input.readLine());
			for (int c = 0; c < M; c++)
				original[r][c] = Integer.parseInt(tokens.nextToken());
		}
		calInfo = new int[K][3];
		choosed = new int[K];
		visited = new boolean[K];
		src = new int[K];
		for (int k = 0; k < K; k++) {
			tokens = new StringTokenizer(input.readLine());
			calInfo[k][0] = Integer.parseInt(tokens.nextToken()) - 1;
			calInfo[k][1] = Integer.parseInt(tokens.nextToken()) - 1;
			calInfo[k][2] = Integer.parseInt(tokens.nextToken());
			src[k] = k;
		}
		calOrder(0, choosed, visited);
	
		for (int i = 0; i < calOrderInfo.size(); i++) {
		
			for (int ti = 0; ti < N; ti++) {
				for (int tj = 0; tj < M; tj++)
					array[ti][tj]=original[ti][tj];
			}
			
			for (int nth : calOrderInfo.get(i)) {
				int r = calInfo[nth][0];
				int c = calInfo[nth][1];
				int s = calInfo[nth][2];

				loc = new ArrayList[s];
				for (int ti = 0; ti < s; ti++) {
					loc[ti] = new ArrayList<int[]>();
				}
				int dist_r = s * 2;
				int dist_c = s * 2;

				for (int ti = 0; ti < s; ti++) {
					int dir = -1;
					int tr = r - s + ti;
					int tc = c - s + ti;
					loc[ti].add(new int[] { tr, tc, array[tr][tc] });

					for (int tz = 0; tz < 2; tz++) {
						dir += 1;
						for (int tj = 0; tj < dist_c; tj++) {
							tr += deltas[dir][0];
							tc += deltas[dir][1];
							loc[ti].add(new int[] { tr, tc, array[tr][tc] });
						}
						dir += 1;
						for (int j = 0; j < dist_r; j++) {
							tr += deltas[dir][0];
							tc += deltas[dir][1];
							loc[ti].add(new int[] { tr, tc, array[tr][tc] });
						}
					}
					loc[ti].remove(loc[ti].size() - 1);
					dist_r -= 2;
					dist_c -= 2;
					for (int tj = 0; tj < loc[ti].size(); tj++)
						array[loc[ti].get(tj)[0]][loc[ti].get(tj)[1]] = loc[ti].get((tj + 1) % loc[ti].size())[2];
				}
			} // 연산 경우의 수 중 한번 끝
			int TSum = 0;
			int MinTSum = Integer.MAX_VALUE;
			for (int tr = 0; tr < N; tr++) {
				TSum=0;
				for (int tc = 0; tc < M; tc++)
					TSum += array[tr][tc];
				MinTSum = Math.min(TSum, MinTSum);
			}
			MinSum = Math.min(MinSum, MinTSum);
			
		}
		System.out.println(MinSum);
	}
}
