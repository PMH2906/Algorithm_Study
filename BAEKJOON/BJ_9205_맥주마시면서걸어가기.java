package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_9205_맥주마시면서걸어가기 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int T, N, nowX, nowY, finishX, finishY;
	static int[][] pos;
	static Queue<int[]> q;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(input.readLine());

		for (int t = 0; t < T; t++) {
			q = new LinkedList<>();
			N = Integer.parseInt(input.readLine());
			tokens = new StringTokenizer(input.readLine());
			nowX = Integer.parseInt(tokens.nextToken());
			nowY = Integer.parseInt(tokens.nextToken());
			pos = new int[N][2];
			visited = new boolean[N];
			for (int n = 0; n < N; n++) {
				tokens = new StringTokenizer(input.readLine());
				int x = Integer.parseInt(tokens.nextToken());
				int y = Integer.parseInt(tokens.nextToken());
				pos[n] = new int[] { x, y };
			}
			tokens = new StringTokenizer(input.readLine());
			finishX = Integer.parseInt(tokens.nextToken());
			finishY = Integer.parseInt(tokens.nextToken());
			bfs();
		}
		System.out.println(output);

	}

	private static void bfs() {
		if(Math.abs(nowX - finishX) + Math.abs(nowY - finishY) <= 1000) {
			output.append("happy\n");
			return;
		}
		for (int n = 0; n < N; n++) {
			if (!visited[n]) {
				if (Math.abs(nowX - pos[n][0]) + Math.abs(nowY - pos[n][1]) <= 1000) {
					q.add(new int[] { pos[n][0], pos[n][1] });
					visited[n] = true;
				}

			}
		}
		while (q.size() > 0) {
			int[] now = q.poll();
			if (Math.abs(now[0] - finishX) + Math.abs(now[1] - finishY) <= 1000) {
				output.append("happy\n");
				return;
			}
			for (int n = 0; n < N; n++) {
				if (!visited[n]) {
					if (Math.abs(now[0] - pos[n][0]) + Math.abs(now[1] - pos[n][1]) <= 1000) {
						q.add(new int[] { pos[n][0], pos[n][1] });
						visited[n] = true;
					}

				}
			}
		}
		output.append("sad\n");

	}

}
