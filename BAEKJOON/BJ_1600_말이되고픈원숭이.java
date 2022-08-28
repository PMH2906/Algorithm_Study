package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import Algorithm.BJ_1600_말이되고픈원숭이.Monkey;

/**
 * 
 * @author 박미희
 * @since 2022. 8. 26.
 * @see
 * @git
 * @youtube
 * @performance
 * @category #
 * @note
 */
public class BJ_1600_말이되고픈원숭이 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int[][] horse = { { -2, -1 }, { -2, 1 }, { -1, -2 }, { -1, 2 }, { 1, -2 }, { 1, 2 }, { 2, -1 }, { 2, 1 } };
	static int[][] monkey = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int[][] grid;
	static boolean[][][] visited; // 0:말 1:원숭이
	static int K, W, H, ans;

	public static class Monkey {
		int x, y, k, cnt, status;

		public Monkey(int x, int y, int k, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.k = k;
			this.cnt = cnt;
		}

	}

	public static void main(String[] args) throws IOException {
		K = Integer.parseInt(input.readLine());
		tokens = new StringTokenizer(input.readLine());
		W = Integer.parseInt(tokens.nextToken()); // 열
		H = Integer.parseInt(tokens.nextToken()); // 행
		grid = new int[H][W];
		visited = new boolean[H][W][K+1];
		ans = Integer.MAX_VALUE;

		for (int r = 0; r < H; r++) {
			tokens = new StringTokenizer(input.readLine());
			for (int c = 0; c < W; c++) {
				grid[r][c] = Integer.parseInt(tokens.nextToken());
			}
		}
		bfs();
		if (ans == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(ans);
	}

	private static void bfs() {
		Queue<Monkey> q = new LinkedList<Monkey>();
		q.add(new Monkey(0, 0, 0, 0)); // x, y, k, cnt(동작수)
		visited[0][0][0] = true;
		
		while (!q.isEmpty()) {
			
			Monkey now = q.poll();
			if (now.x == H - 1 && now.y == W - 1) {
				ans = Math.min(ans, now.cnt);
			}
			for (int d = 0; d < monkey.length; d++) {
				int nx = now.x + monkey[d][0];
				int ny = now.y + monkey[d][1];

				if (nx < 0 || nx >= H || ny < 0 || ny >= W)
					continue;

				if (!visited[nx][ny][now.k] && grid[nx][ny] != 1) {
					visited[nx][ny][now.k] = true;
					q.add(new Monkey(nx, ny, now.k, now.cnt + 1));
				}
			}
			if (now.k<K) {
				for (int d = 0; d < horse.length; d++) {
					int nx = now.x + horse[d][0];
					int ny = now.y + horse[d][1];

					if (nx < 0 || nx >= H || ny < 0 || ny >= W)
						continue;
					if (!visited[nx][ny][now.k+1] && grid[nx][ny] != 1) { // 말 움직임으로 확인하는 것이므로 말로 움직였다는 것은 k+1의 위치인 visited를 확인하고, 해당 visited에 true를 해줘야함!!!
						visited[nx][ny][now.k+1] = true;
						q.add(new Monkey(nx, ny, now.k + 1, now.cnt + 1));
					}
				}
			}
		}
		return;
	}
}
