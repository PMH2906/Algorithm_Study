package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_17143_낚시왕 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int R, C, M, ans = 0;
//	static List<Shark> sharks = new LinkedList<>();
	static Shark[] sharks;
	static int[][] map, deltas = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	public static class Shark {
		int x, y, s, d, z;

		public Shark(int x, int y, int s, int d, int z) {
			super();
			this.x = x;
			this.y = y;
			this.s = s;
			this.d = d;
			this.z = z;
		}

	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());

		map = new int[R][C];
		sharks = new Shark[M];
		for (int m = 0; m < M; m++) {
			tokens = new StringTokenizer(input.readLine());
			int x = Integer.parseInt(tokens.nextToken()) - 1;
			int y = Integer.parseInt(tokens.nextToken()) - 1;
			int s = Integer.parseInt(tokens.nextToken());
			int d = Integer.parseInt(tokens.nextToken()) - 1;
			int z = Integer.parseInt(tokens.nextToken());

			map[x][y] = m + 1;// 상어 번호+1
			sharks[m] = new Shark(x, y, s, d, z);
		}
		for (int c = 0; c < C; c++) { // 낚시왕 이동
			for (int[] m : map) {
				System.out.println(Arrays.toString(m));
			}
			System.out.println();
			for (int r = 0; r < R; r++) {
				if (map[r][c] != 0) { // 상어 있으면 잡기
					ans += sharks[map[r][c] - 1].z; // 상어 크기 합
					sharks[map[r][c] - 1] = null; // 상어 잡음
					map[r][c] = 0; // 상어 잡았으므로 해당 위치 삭제
					break;
				}
			}

			if (c == C)
				break;
			moveShark();
		}
		System.out.println(ans);
	}

	private static void moveShark() {

		for (Shark shark : sharks) {
			if (shark == null)
				continue;
			int nx = shark.x;
			int ny = shark.y;

			int nth = map[nx][ny]; // 상어 번호
			map[nx][ny] = 0;// 현재 상어의 위치는 옮겨질 것이므로 0;
			if (shark.d == 0 || shark.d == 1) { // 위, 아래
				shark.s %= R * 2-2;
				int m = shark.s * deltas[shark.d][0];
				if (m+nx < 0) {
					nx =Math.abs(m+nx)%R;
					if(Math.abs(m+nx)/2==0)shark.d = shark.d == 0 ? 1 : 0;
				}
				if(m+nx>=R) {
					shark.d = shark.d == 0 ? 1 : 0;
					nx=2*(R-1)-m-nx;
				}
			} else if (shark.d == 2 || shark.d == 3) { // 좌,우
				shark.s %= C * 2-2;
				int m = shark.s * deltas[shark.d][1];
				if (m+ny < 0) {
					ny =Math.abs(m+ny);
					shark.d = shark.d == 2 ? 3 : 2;
				}
				else if(m+ny>=R) {
					shark.d = shark.d == 0 ? 1 : 0;
					ny=2*(C-1)-m-ny;
				}
			}
			System.out.println(nx+" "+ny);
			// 만약 옮긴 위치 같으면
			if (map[nx][ny] != 0) {
				if (sharks[map[nx][ny] - 1].z < shark.z) {
					sharks[map[nx][ny] - 1] = null;
					map[nx][ny] = nth;
					shark.x = nx;
					shark.y = ny;
				} else
					shark = null;
			} else {
				map[nx][ny] = nth;
				shark.x = nx;
				shark.y = ny;
			}

		}

	}

}
