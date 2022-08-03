package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_14891_톱니바퀴 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int[][] status = new int[4][8];
	static int N;
	static int[][] info;
	static int[] deltas = { -1, 1 };
	static int[] scores = {1,2,4,8};
	static int score;
	
	//회전
	static void turn(int who, int dir) {
		int[] after = new int[8];

		if (dir == 1) {
			for (int i = 0; i < 8; i++) {
				if (i == 0) {
					after[i] = status[who - 1][7];
				}
				else after[i] = status[who - 1][i - 1];
			}
		} else {
			for (int i = 0; i < 8; i++) {
				if (i == 7) {
					after[i] = status[who - 1][0];
				}
				else after[i] = status[who - 1][i + 1];
			}
		}
		status[who - 1] = after;
	}
    // 회전 여부
	static boolean check(int L, int R) {
		return status[L - 1][2] != status[R - 1][6];
	}

	public static void main(String[] args) throws Exception {

		score =0;
		
		for (int r = 0; r < 4; r++) {
			String[] line = input.readLine().split("");
			for(int c = 0; c < 8; c++) {
				status[r][c] = Integer.parseInt(line[c]);
			}

		}

		N = Integer.parseInt(input.readLine());

		info = new int[N][2];

		for (int r = 0; r < N; r++) {
			tokens = new StringTokenizer(input.readLine());
			for (int c = 0; c < 2; c++) {
				info[r][c] = Integer.parseInt(tokens.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			int who = info[i][0];
			int dir = info[i][1];
			
			boolean [] checked = new boolean[4];
			int [] dirList = new int[4];
			
			int L = who;
			int dirSide = dir;
			
			while (true) { //좌 탐색
				L += deltas[0];
				if(L==0) break;
				if (check(L, L-deltas[0])) {
					dirSide *= -1;
					dirList[L-1]=dirSide;
					checked[L-1]=true;
				}
				else break;
			}
			
			int R = who;
			dirSide = dir;
			while (true) { //우 탐색
				R += deltas[1];
				if(R==5) break;
				if (check(R-deltas[1], R)) {
					dirSide *= -1;
					dirList[R-1]=dirSide;
					checked[R-1]=true;
				}
				else break;
			}
			
			// 회전
			for(int t=0;t<4;t++) {
				if(checked[t]) turn(t+1,dirList[t]);
			}
			turn(who, dir);
		}
		
		// 점수
		for(int i = 0;i<4;i++) {
			if(status[i][0]==1) score+=scores[i];
		}
		System.out.println(score);
	}
}
