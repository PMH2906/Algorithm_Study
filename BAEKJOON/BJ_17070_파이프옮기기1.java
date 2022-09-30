package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_17070_파이프옮기기1 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int N;
	static int[][] map, dp; //dp:해당칸으로 올수있는 방법의 수
	static int[][][] dir; // 해당칸으로 온 모든 방향 표시 0:가로, 1:세로, 2:대각선

	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		N = Integer.parseInt(input.readLine());

		map = new int[N][N];
		dp = new int[N][N];
		dir = new int[N][N][3];
		dp[0][1] = 1; // 파이프 끝 방향
		dir[0][1][0] = 1; // 파이프 첫번째 방향으로 가로이므로 가로 방향에 1 저장

		for (int r = 0; r < N; r++) {
			tokens = new StringTokenizer(input.readLine());
			for (int c = 0; c < N; c++) {

				map[r][c] = Integer.parseInt(tokens.nextToken());
			}
		}
		for (int r = 0; r < N; r++) {
			for (int c = 2; c < N; c++) {

				if (r == 0) {
					if(map[r][c]==1) break; 
					dp[r][c] = 1; // 첫번째 row는 가로로 오는 방향 뿐이므로 방법의 수는 1
					dir[r][c][0] = 1; // 첫번째 row는 가로로 오는 방향 뿐이므로 해당 row에 벽을 만나기 전까지 1저장
				} else {
					if (map[r][c] == 0) { // 현재 위치가 벽이 아니면 
						if (moveDiag(r, c)) { // 현재 위치에서 대각선으로 움직일 수 있다면 
							// 현재 칸에 대각선 방향으로 올 수 있는 칸은 r-1, c-1위치임.
							// 이전에 r-1, c-1 칸에 가로, 세로, 대각선 어떤 방향으로 오든 무조건 대각선 방향으로 r,c 칸에 올 수 있으므로
							dp[r][c] += dp[r - 1][c - 1];  // r-1, c-1 칸 경우의 수 모두 더하기
							dir[r][c][2] += dp[r - 1][c - 1]; // 대각선 방향으로 r,c칸에 도착하므로 대각선 방향에 모든 경우의 수 저장
						}
						//r-1,c칸은 r,c칸에 세로 방향으로 와야함. 세로 방향으로 움직일 수 있는 경우는 r-1,c칸에 세로 혹은 대각선으로 도착해야함
						dp[r][c] += dir[r - 1][c][1] + dir[r - 1][c][2]; //r-1,c칸에 세로 혹은 대각선으로 도착하는 경우의 수 더하기
						dir[r][c][1] += dir[r - 1][c][1] + dir[r - 1][c][2]; //r,c칸의 세로 방향에 모든 경우의 수 저장 

						// r, c-1칸은 r,c칸에 가로 방향으로 와야함. 가로 방향으로 움직일 수 있는 경우에는 r,c-1칸에 가로 혹은 대각선으로 도착해야함
						dp[r][c] += dir[r][c - 1][0] + dir[r][c - 1][2]; // r, c-1칸에 가로 혹은 대각선으로 도착하는 경우의 수 더하기
						dir[r][c][0] += dir[r][c - 1][0] + dir[r][c - 1][2]; // r,c칸의 가로 방향에 모든 경우의 수 저장
					}
				}
			}
		}

		System.out.println(dp[N - 1][N - 1]);
	}

	private static boolean moveDiag(int r, int c) { // 대각선 이동 시 비어있어야하는 칸 확인

		if (map[r - 1][c] == 0 && map[r][c - 1] == 0)
			return true;
		else
			return false;
	}

}
