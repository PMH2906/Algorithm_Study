import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class sw_4193_수영대회결승전 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int N, map[][];
	static boolean visited[][];
	static PriorityQueue<Info> pq = new PriorityQueue<Info>();
	static Info start, finish;
	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static class Info implements Comparable<Info> {
		int x, y, time;

		public Info(int x, int y, int time) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
		}

		@Override
		public String toString() {
			return "Info [x=" + x + ", y=" + y + ", time=" + time + "]";
		}

		@Override
		public int compareTo(sw_4193_수영대회결승전.Info o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.time, o.time);
		}

	}

	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		int T = Integer.parseInt(input.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			pq = new PriorityQueue<Info>();
			
			N = Integer.parseInt(input.readLine());

			map = new int[N][N];
			visited = new boolean[N][N];

			for (int r = 0; r < N; r++) {
				tokens = new StringTokenizer(input.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(tokens.nextToken());
				}
			}

			tokens = new StringTokenizer(input.readLine());
			start = new Info(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()), 0);

			tokens = new StringTokenizer(input.readLine());
			finish = new Info(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()), 0);

			int ans = result(start, finish);

			output.append(String.format("#%d %d\n", test_case, ans));

		}
		System.out.println(output);

	}

	private static int result(Info start, Info finish) {
		visited[start.x][start.y] = true;
		pq.add(start);

		while (pq.size() > 0) {
			Info now = pq.poll();
			if (now.x == finish.x && now.y == finish.y)
				return now.time;

			for (int d = 0; d < deltas.length; d++) {
				int nx = now.x + deltas[d][0];
				int ny = now.y + deltas[d][1];

				if (nx < 0 || nx >= N || ny < 0 || ny >= N)
					continue;

				if (now.time % 3 == 2) {
					if (map[nx][ny] != 1 && !visited[nx][ny]) {
						pq.add(new Info(nx, ny, now.time + 1));
						visited[nx][ny] = true;
					}
				} else if (now.time % 3 == 0) {
					if (map[nx][ny] == 0 && !visited[nx][ny]) {
						pq.add(new Info(nx, ny, now.time + 1));
						visited[nx][ny] = true;
					} else if (map[nx][ny] == 2 && !visited[nx][ny]) {
						pq.add(new Info(nx, ny, now.time + 3));
						visited[nx][ny] = true;
					}
				} else if (now.time % 3 == 1) {
					if (map[nx][ny] == 0 && !visited[nx][ny]) {
						pq.add(new Info(nx, ny, now.time + 1));
						visited[nx][ny] = true;
					} else if (map[nx][ny] == 2 && !visited[nx][ny]) {
						pq.add(new Info(nx, ny, now.time + 2));
						visited[nx][ny] = true;
					}
				}
			}
		}

		return -1;

	}

}
