import java.util.*;
import java.io.*;

public class BJ17822_원판돌리기 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N, M, T, map[][], removeCnt = 0, allSum = 0, allCnt = 0;
	static int[][] deltas = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());

		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		T = Integer.parseInt(tokens.nextToken());

		map = new int[N][M];

		for (int r = 0; r < N; r++) {
			tokens = new StringTokenizer(input.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(tokens.nextToken());
			}
		}
		for (int t = 0; t < T; t++) {
			tokens = new StringTokenizer(input.readLine());
			int x = Integer.parseInt(tokens.nextToken());
			int d = Integer.parseInt(tokens.nextToken());
			int k = Integer.parseInt(tokens.nextToken())%M;

			int nth = 1;
			while (true) {
				int line = nth * x -1;
				if (line >= N)
					break;
				turn(line, d, k);
				nth++;
			}
//			for (int[] a : map) {
//				System.out.println(Arrays.toString(a));
//			}
//			System.out.println();
			visited = new boolean[N][M];
			removeCnt = 0;
			allSum = 0;
			allCnt = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (!visited[r][c] && map[r][c] != 0) {
						check(r, c);
					}
				}
			}
			if (removeCnt == 0) {
				double avg = (double)allSum / (double)allCnt;
				allSum=0;
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < M; c++) {
						if (map[r][c] != 0 && map[r][c] <avg) {
							map[r][c] += 1;
						} else if (map[r][c] != 0 && map[r][c] > avg) {
							map[r][c] -= 1;
						}
						allSum+=map[r][c];
					}
				}
			}
//			for (int[] a : map) {
//				System.out.println(Arrays.toString(a));
//			}
		}
		System.out.print(allSum);
	}

	private static void check(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		visited[r][c] = true;
		for (int d = 0; d < deltas.length; d++) {
			int nr = r + deltas[d][0];
			int nc = c + deltas[d][1];

			if (nr < 0 || nr >= N)
				continue;
			if (nc < 0)
				nc = M - 1;
			else if (nc >= M)
				nc = 0;

			if (map[r][c] == map[nr][nc] && !visited[nr][nc]) {
//				System.out.println(nr+ " "+nc);
				q.add(new int[] { nr, nc });
				visited[nr][nc] = true;
			}
		}

		if (q.isEmpty()) {
			allSum += map[r][c];
			allCnt++;
//			System.out.println(allCnt);
		} else {
			removeCnt += 1;
			map[r][c] = 0;
			while (q.size() > 0) {
				int[] now = q.poll();
				int num = map[now[0]][now[1]];
				map[now[0]][now[1]] = 0;
				for (int d = 0; d < deltas.length; d++) {
					int nr = now[0] + deltas[d][0];
					int nc = now[1] + deltas[d][1];

					if (nr < 0 || nr >= N)
						continue;
					if (nc < 0)
						nc = M - 1;
					else if (nc >= M)
						nc = 0;

					if (num == map[nr][nc] && !visited[nr][nc]) {
						q.add(new int[] { nr, nc });
						visited[nr][nc] = true;
					}
				}
			}
		}

	}

	private static void turn(int x, int d, int k) {
		int[] mapLine = new int[M];
		if (d == 1) {
			for (int m = 0; m < M; m++) {
				mapLine[(M + (m - k)) % M] = map[x][m];
			}
		} else {
			for (int m = 0; m < M; m++) {
				mapLine[(m + k) % M] = map[x][m];
			}
		}
		map[x] = mapLine;
	}

}
