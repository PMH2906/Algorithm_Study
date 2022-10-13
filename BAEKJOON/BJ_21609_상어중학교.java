package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_21609_상어중학교 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int N, M, maxBlockCnt, maxRainbowCnt;
	static int grid[][], score;
	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());

		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());

		grid = new int[N][N];
		score = 0;

		for (int r = 0; r < N; r++) {
			tokens = new StringTokenizer(input.readLine());
			for (int c = 0; c < N; c++) {
				grid[r][c] = Integer.parseInt(tokens.nextToken());
			}
		}

		while (true) {
			maxBlockCnt = Integer.MIN_VALUE;// 블록 크기
			maxRainbowCnt = Integer.MIN_VALUE;
			visited = new boolean[N][N];
			int[] cnt;
			int[] bigBlockP = new int[] { -1, -1 };
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (!visited[r][c] && grid[r][c] > 0) {
						cnt = bfs(r, c, grid[r][c]);
						if (maxBlockCnt < cnt[0]) { // 현재 블록이 큰 블록이라면
							bigBlockP = new int[] { r, c }; // 큰 블록 위치
							maxBlockCnt = cnt[0];
							maxRainbowCnt = cnt[1];
						} else if (maxBlockCnt == cnt[0]) { // 현재 블록 크기가 동일하다면 무지개 블록 갯수 비교
							if (maxRainbowCnt <= cnt[1]) { // 무지개 블록 갯수 동일해도 가장 큰 행, 큰 열을 선택해야하므로 계속해서 갱신
								bigBlockP = new int[] { r, c }; // 큰 블록 위치
								maxBlockCnt = cnt[0];
								maxRainbowCnt = cnt[1];
							}
						}
					}
//					System.out.println("cnt" + maxBlockCnt + "rain" + maxRainbowCnt);
//					System.out.println(bigBlockP[0] + " " + bigBlockP[1]);
				}
			}

			if (maxBlockCnt < 2) // 블록 크기 2보다 작으면 게임 종료
				break;
			destroy(bigBlockP[0], bigBlockP[1], grid[bigBlockP[0]][bigBlockP[1]]); // 파괴
			score += maxBlockCnt * maxBlockCnt; // 점수 획득
			gravity(); // 중력
			cwRotate90(); // 반시계방향90도회전
			gravity(); // 중력
		}
		System.out.println(score);
	}

	private static void cwRotate90() {
		List<Integer>[] temp = new LinkedList[N];
		for (int r = 0; r < N; r++)
			temp[r] = new LinkedList<>();

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				temp[(N - 1) - c].add(grid[r][c]);
			}
		}
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				grid[r][c] = temp[r].get(c);
			}
		}
//		for (int[] g : grid) {
//			System.out.println(Arrays.toString(g));
//		}
//		System.out.println();

	}

	private static void gravity() {
		int empty;

		for (int c = 0; c < N; c++) {
			empty = 0;
			for (int r = N - 1; r >= 0; r--) {
				if (grid[r][c] == -2)
					empty += 1; // 빈칸
				else if (grid[r][c] == -1)
					empty = 0; // 검은 블록
				else {
					grid[r + empty][c] = grid[r][c];// 빈칸 채우기
					if(empty!=0)grid[r][c] = -2; // 빈칸으로 만들기
				}
			}
		}
//		for (int[] g : grid) {
//			System.out.println(Arrays.toString(g));
//		}
//		System.out.println();

	}

	private static void destroy(int r, int c, int blockNum) {
		Queue<int[]> q = new LinkedList<>();

		boolean[][] visited = new boolean[N][N];
		visited[r][c] = true;
		grid[r][c] = -2;
		q.add(new int[] { r, c });
		while (q.size() > 0) {
			int[] now = q.poll();
			for (int d = 0; d < deltas.length; d++) {
				int nx = now[0] + deltas[d][0];
				int ny = now[1] + deltas[d][1];

				if (nx < 0 || nx >= N || ny < 0 || ny >= N)
					continue;

				if ((grid[nx][ny] == blockNum || grid[nx][ny] == 0) && !visited[nx][ny]) {
					q.add(new int[] { nx, ny });
					visited[nx][ny] = true;
					grid[nx][ny] = -2;
				}

			}
		}
//		for (int[] g : grid) {
//			System.out.println(Arrays.toString(g));
//		}
//		System.out.println();

	}

	private static int[] bfs(int r, int c, int blockNum) {
		Queue<int[]> q = new LinkedList<>();
		Queue<int[]> zero = new LinkedList<>();

		visited[r][c] = true;
		int cnt = 1;
		q.add(new int[] { r, c });
		while (q.size() > 0) {
			int[] now = q.poll();
			for (int d = 0; d < deltas.length; d++) {
				int nx = now[0] + deltas[d][0];
				int ny = now[1] + deltas[d][1];

				if (nx < 0 || nx >= N || ny < 0 || ny >= N)
					continue;

				if ((grid[nx][ny] == blockNum || grid[nx][ny] == 0) && !visited[nx][ny]) {
					q.add(new int[] { nx, ny });
					if (grid[nx][ny] == 0)
						zero.add(new int[] { nx, ny });
					visited[nx][ny] = true;
					cnt += 1;
				}

			}
		}
		int rainbowCnt = zero.size();
		while (zero.size() > 0) { // 무지개 블록은 다른 일반 블록에서 또 방문할 수 있도록 visited를 false로 갱신
			int[] now = zero.poll();
			visited[now[0]][now[1]] = false;
		}
		return new int[] { cnt, rainbowCnt };
	}

}
