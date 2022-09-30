package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2206_벽부수고이동하기_v2 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int N, M, ans = 0;
	static int[][] deltas = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static char [][] map;
	static Queue<int[]> q = new LinkedList<>(); // r,c,파괴여부
	static int[][][] visited; //[r][c][0] : 벽 파괴하지 X경로 [r][c][1] : 벽 파괴한 경로

	public static void main(String[] args) throws IOException {

		tokens = new StringTokenizer(input.readLine());

		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());

		map = new char[N][M];
		visited = new int[N][M][2];
		for (int r = 0; r < N; r++) {
			map[r]= input.readLine().toCharArray();
		}
		q.add(new int[] { 0, 0, 0 });
		while (q.size() > 0) {
			int[] now = q.poll();
			if (now[0] == N - 1 && now[1] == M - 1) { // 끝 위치 도착 시 종료
				System.out.println(visited[N - 1][M - 1][now[2]] + 1);
				return;
			}
			for (int d = 0; d < 4; d++) {
				int nx = now[0] + deltas[d][0];
				int ny = now[1] + deltas[d][1];

				if (nx < 0 || nx >= N || ny < 0 || ny >= M)
					continue;

				if (map[nx][ny] == '0') { // 벽이 아닐 경우
					if (now[2] == 0 && visited[nx][ny][0] == 0) { // 벽을 한번도 파괴하지 않은 경우
						q.add(new int[] { nx, ny, 0 });
						visited[nx][ny][0] = visited[now[0]][now[1]][0] + 1;
					}
					if (now[2] == 1 && visited[nx][ny][1] == 0) { // 벽을 한번이라도 파괴한 경우
						q.add(new int[] { nx, ny, 1 });
						visited[nx][ny][1] = visited[now[0]][now[1]][1] + 1;
					}
				}

				if (map[nx][ny] == '1') { // 벽인 경우
					if (now[2] == 0 && visited[nx][ny][1] == 0) { // 벽을 한번도 파괴하지 않았다면 파괴한 후 거리 측정
						q.add(new int[] { nx, ny, 1 });
						visited[nx][ny][1] = visited[now[0]][now[1]][0] + 1;
					}
				}
			}
		}
		System.out.println(-1);

	}

}
