package Algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_1861_정사각형방 {
	static StringBuilder output = new StringBuilder();
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N;
	static int[][] rooms;
	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static boolean[][] visited;
	static int resultCnt;
	static int resultRoomNum;
	static int minRoomNum;
	static int move;

	static void dfs(int r, int c) {
		for (int i=0; i < deltas.length; i++) {
			visited[r][c] = true;
			int nr = r + deltas[i][0];
			int nc = c + deltas[i][1];
			if (nr < 0 || nr >= N || nc < 0 || nc >= N)continue;
			if (!visited[nr][nc]&&(rooms[nr][nc] == rooms[r][c] - 1 || rooms[nr][nc] == rooms[r][c] + 1)) {
				minRoomNum=Math.min(minRoomNum, rooms[nr][nc]);
				move += 1;
				dfs(nr,nc);
			}
		}
	}

	public static void main(String args[]) throws Exception {
		int T;
		T = Integer.parseInt(input.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(input.readLine());

			visited = new boolean[N][N];
			rooms = new int[N][N];
			resultCnt = 0;
			resultRoomNum = 0;
			
			for (int r = 0; r < N; r++) {
				tokens = new StringTokenizer(input.readLine());
				for (int c = 0; c < N; c++)
					rooms[r][c] = Integer.parseInt(tokens.nextToken());
			}

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (!visited[r][c]) {
						minRoomNum=rooms[r][c];
						dfs(r, c);
						if(move>resultCnt) {
							resultCnt = move;
							resultRoomNum = minRoomNum;
						}
						else if(move==resultCnt)resultRoomNum=Math.min(minRoomNum, resultRoomNum);
						move = 0;
					}
				}
			}
			output.append(String.format("#%d %d %d\n", test_case,resultRoomNum, resultCnt+1));
		}
		System.out.println(output);
	}
}
