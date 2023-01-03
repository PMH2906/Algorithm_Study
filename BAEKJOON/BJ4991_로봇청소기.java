import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ4991_로봇청소기 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static char[][] map;
	static int W, H, dirtyCnt, minMoved, ans;
	static int[][] deltas = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };
	static int[][] point, distance;

	public static void main(String[] args) throws IOException {

		while (true) {
			tokens = new StringTokenizer(input.readLine());
			W = Integer.parseInt(tokens.nextToken());
			H = Integer.parseInt(tokens.nextToken());
			if (W == 0 && H == 0)
				break;

			// 초기화
			map = new char[H][W];
			dirtyCnt = 1;
			point = new int[11][2];
			ans = Integer.MAX_VALUE;

			for (int h = 0; h < H; h++) {
				map[h] = input.readLine().toCharArray();
				for (int w = 0; w < W; w++) {
					if (map[h][w] == '*') {
						point[dirtyCnt][0] = h; // 먼지 위치 저장
						point[dirtyCnt++][1] = w;
					} else if (map[h][w] == 'o') {
						point[0][0] = h;
						point[0][1] = w;
					}
				}
			}

			check();
//			for (int[] a : point) {
//				System.out.println(Arrays.toString(a));
//			}
			
			if (ans == Integer.MAX_VALUE)
				output.append(-1 + "\n");
			else
				output.append(ans + "\n");
		}
		System.out.println(output);
	}

	private static void check() {

		// 청소기와 먼지, 먼지와 먼지 즉, 모든 거리를 저장할 2차원 배열
		distance = new int[dirtyCnt + 1][dirtyCnt + 1];

		// 거리 저장
		for (int i = 0; i < dirtyCnt; i++) {
			for (int j = i + 1; j < dirtyCnt; j++) {
				int dist = bfs(i, j);
				if (dist == -1) { // 모든 거리를 측정했을 때, 하나라도 도달할 수 없는 경우 return
					return;
				} else {
					distance[i][j] = distance[j][i] = dist; // 반대 경우도 저장하기!!!!!
				}
			}
		}

		permutation(0, 0, 0, new boolean[dirtyCnt]);
	}

	private static void permutation(int nth, int cleanCnt, int distSum, boolean[] visited) {

		if (cleanCnt == dirtyCnt - 1) {
			ans = Math.min(ans, distSum); // 가장 짧은 거리
			return;
		}
		for (int i = 1; i < dirtyCnt; i++) {
			if (!visited[i]) {
				visited[i] = true;
				// 다음 탐색 위치, 청소 갯수, 현재 탐색하는 위치에서 다음 탐색 위치의 거리, 방문 여부
				permutation(i, cleanCnt + 1, distSum + distance[nth][i], visited);
				visited[i] = false;
			}
		}
	}

	private static int bfs(int start, int finish) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[H][W];

		q.add(point[start]);
		visited[point[start][0]][point[start][1]] = true; // 시작 위치

		int move = 0;
		while (q.size() > 0) {
			int size = q.size();
			for (int s = 0; s < size; s++) { // 거리별 탐색
				int[] now = q.poll();
				if (now[0] == point[finish][0] && now[1] == point[finish][1]) {
					System.out.println(move);
					return move; // 거리 리턴 
				}
					
				
				for (int d = 0; d < deltas.length; d++) {
					int nx = now[0] + deltas[d][0];
					int ny = now[1] + deltas[d][1];

					if (nx < 0 || nx >= H || ny < 0 || ny >= W)
						continue;

					if (map[nx][ny] == '.' && !visited[nx][ny]) {
						q.add(new int[] { nx, ny});
						visited[nx][ny] = true;
					}
				}
			}
			move++;
			
		}
		return -1;
	}

}
