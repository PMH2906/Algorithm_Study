package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1194_달이차오른다가자 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static char[][] map;
	static int N, M;
	static Queue<Info> q = new LinkedList<>();
	static int[][] deltas = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };
	static boolean[][][] visited; // 3번재 배열 : 각 열쇠를 가지고 있는지 여부에 따른 비트마스킹으로 해당 visited배열 접근

	public static class Info {
		int key;
		int x, y, cnt;

		public Info(int key, int x, int y, int cnt) {
			super();
			this.key = key;
			this.x = x;
			this.y = y;
			this.cnt = cnt;

		}

	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M][128]; // 6자리 수의 비트마스킹으로 표현되므로 127이 최대 표현 수. 0은 아무 열쇠도 가지지 않을 때
		for (int r = 0; r < N; r++) {
			map[r] = input.readLine().toCharArray();
			for (int c = 0; c < M; c++) {
				if (map[r][c] == '0') {
					q.add(new Info(0, r, c, 0));
					visited[r][c][0] = true;
					map[r][c] = '.';
				}
			}
		}
		while (q.size() > 0) {
			Info now = q.poll();
			for (int d = 0; d < 4; d++) {
				int keyCheck = now.key;
				int nx = now.x + deltas[d][0];
				int ny = now.y + deltas[d][1];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M)
					continue;

				if (map[nx][ny] == '.' && !visited[nx][ny][now.key]) {// 현재 열쇠를 가지고 있는 visited 배열 접근해서 방문 여부 확인
					visited[nx][ny][now.key] = true; 
					q.add(new Info(keyCheck, nx, ny, now.cnt + 1));
				} else if (map[nx][ny] >= 'a' && map[nx][ny] <= 'z') { // 열쇠면
					keyCheck |= 1 << (map[nx][ny] - 'a' + 1); // 비트마스킹 연산해서 1로 만들어주기
					if (!visited[nx][ny][keyCheck]) { // 비트마스킹 연산 후 해당 visited접근해서 방문 여부확인
						visited[nx][ny][keyCheck] = true;
						q.add(new Info(keyCheck, nx, ny, now.cnt + 1));
					}

				} else if (map[nx][ny] >= 'A' && map[nx][ny] <= 'Z' && !visited[nx][ny][now.key]) { // 문이면 현재 열쇠를 가지고 있는 visited 배열 접근해서 방문 여부 확인
					if ((keyCheck & 1 << (map[nx][ny] - 'A' + 1)) != 0) { // 비트마스킹으로 된 keyCheck로 열쇠 있는지 확인
						visited[nx][ny][now.key] = true;
						q.add(new Info(keyCheck, nx, ny, now.cnt + 1));
					}

				} else if (map[nx][ny] == '1') { // 탈출
					System.out.println(now.cnt + 1);
					return;
				}
			}
		}
		System.out.println(-1);

	}

}
