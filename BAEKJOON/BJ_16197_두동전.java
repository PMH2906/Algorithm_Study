package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_16197_두동전 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int N, M, result = 0;
	static String[][] board;
	static Queue<int[]> coin1 = new LinkedList<>(); // 첫번째 코인
	static Queue<int[]> coin2 = new LinkedList<>(); // 두번째 코인
	static final int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void bfs() {
		while (coin1.size() > 0 && coin2.size() > 0) {
			int[] coin1P = coin1.poll();
			int[] coin2P = coin2.poll();
			if(coin1P[2]>=10) break;
			for (int i = 0; i < deltas.length; i++) {
				int coin1NX = coin1P[0] + deltas[i][0];
				int coin1NY = coin1P[1] + deltas[i][1];
				int coin2NX = coin2P[0] + deltas[i][0];
				int coin2NY = coin2P[1] + deltas[i][1];

				if ((coin1NX < 0 || coin1NX >= N || coin1NY < 0 || coin1NY >= M) // 같이 떨어진 경우
						&& (coin2NX < 0 || coin2NX >= N || coin2NY < 0 || coin2NY >= M))
					continue;
				if ((coin1NX < 0 || coin1NX >= N || coin1NY < 0 || coin1NY >= M) // 둘 중에 하나 떨어진 경우
						|| (coin2NX < 0 || coin2NX >= N || coin2NY < 0 || coin2NY >= M)) {
					output.append(coin1P[2] + 1);
					return;
				}

				if (board[coin1NX][coin1NY].equals(".")|| board[coin2NX][coin2NY].equals(".")) { // 두 동전 중 하나의 동전이라도 움직일 수 있는 경우
					if (board[coin1NX][coin1NY].equals("#")) // 첫 번째 동전이 못 움직일 경우
						coin1.add(new int[] { coin1P[0], coin1P[1], coin1P[2] + 1 }); // 현 위치 그대로를 큐에 넣어줌
					else 
						coin1.add(new int[] { coin1NX, coin1NY, coin1P[2] + 1 }); // 움직 일 수 있다면 움직인 위치를 큐에 넣어줌
					
					if (board[coin2NX][coin2NY].equals("#")) // 두 번째 코인도 동일
						coin2.add(new int[] { coin2P[0], coin2P[1], coin2P[2] + 1 });
					else
						coin2.add(new int[] { coin2NX, coin2NY, coin2P[2] + 1 });
				}
			}
		}
		output.append(-1);
		return;
	}

	public static void main(String[] args) throws Exception {
		tokens = new StringTokenizer(input.readLine());

		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		board = new String[N][M];
		for (int r = 0; r < N; r++) {
			board[r] = input.readLine().split("");
			for (int c = 0; c < M; c++) {
				if (board[r][c].equals("o")) {
					coin2.add(new int[] { r, c, 0 });
					board[r][c]=".";
				}
			}
		}
		coin1.add(coin2.poll());

		bfs();
		System.out.println(output);

	}

}
