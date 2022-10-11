package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_20057_마법사상어와토네이도 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int[][] deltas = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };
	static int[][] pt = { { -1, 0, 1 }, { 1, 0, 1 }, { -1, -1, 7 }, { 1, -1, 7 }, { 2, -1, 2 }, { -2, -1, 2 },
			{ -1, -2, 10 }, { 1, -2, 10 }, { 0, -3, 5 } };
	static int N, map[][], dir, dist, ans=0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(input.readLine());

		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			tokens = new StringTokenizer(input.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(tokens.nextToken());
			}
		}
		dist = 0;
		dir = -1;
		int[] now = { N / 2, N / 2 };
		while (true) {
			if(now[0]==0&&now[1]==0) break;
			
			dir = (dir + 1) % 4;
			if (dir == 0 || dir == 2)
				dist++;
			
			for (int i = 0; i < dist; i++) {
				if(now[0]==0&&now[1]==0) break;
				int nx = now[0] + deltas[dir][0];
				int ny = now[1] + deltas[dir][1];
				int m = map[nx][ny];
				map[nx][ny]=0;
				int moveM = 0;
				int totalM=0;
				for (int p = 0; p < pt.length; p++) {
					int moveX = now[0] + pt[p][0];
					int moveY = now[1] + pt[p][1];
					if (dir == 1) {
						moveX = now[0] + pt[p][1]*-1;
						moveY = now[1] + pt[p][0];
					} else if (dir == 2) {
						moveX = now[0] + pt[p][0];
						moveY = now[1] + pt[p][1]*-1;
					} else if (dir == 3) {
						moveX = now[0] + pt[p][1];
						moveY = now[1] + pt[p][0];

					}
					moveM = (m * pt[p][2])/100;
					totalM+=moveM;
					if(moveX<0||moveX>=N||moveY<0||moveY>=N) {
						ans+=moveM;
						continue;
					}
					map[moveX][moveY] +=moveM;
				}
				int moveX = now[0];
				int moveY = now[1]-2;
				if (dir == 1) {
					moveX = now[0]+2;
					moveY = now[1];
				} else if (dir == 2) {
					moveX = now[0];
					moveY = now[1]+2;
				} else if (dir == 3) {
					moveX = now[0]-2;
					moveY = now[1];
				}
				// 현재 위치 옮겨진 위치로 바꿔주기
				now[0]=nx;
				now[1]=ny;
				if(moveX<0||moveX>=N||moveY<0||moveY>=N) {
					ans+= (m - totalM);
					continue;
				}
				map[moveX][moveY] += (m - totalM);
			}
		}
		output.append(ans);
		System.out.println(ans);

	}

}
