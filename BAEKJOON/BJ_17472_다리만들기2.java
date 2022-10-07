package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_17472_다리만들기2 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int N, M, map[][], parent[], city, ans;
	static boolean[][] visited;
	static int[][] deltas = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static PriorityQueue<Info> pq = new PriorityQueue<>();

	public static class Info implements Comparable<Info> {
		int a, b, dist;

		public Info(int a, int b, int dist) {
			super();
			this.a = a;
			this.b = b;
			this.dist = dist;
		}

		@Override
		public int compareTo(Info o) {
			return Integer.compare(this.dist, o.dist);
		}

		@Override
		public String toString() {
			return "Info [a=" + a + ", b=" + b + ", dist=" + dist + "]";
		}
		
	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		map = new int[N][M];
		//dist = new int[N][M];
		visited = new boolean[N][M];

		for (int r = 0; r < N; r++) {
			tokens = new StringTokenizer(input.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(tokens.nextToken());
			}
		}
		city = 1;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (!visited[r][c] && map[r][c] == 1) {
					visited[r][c] = true;
					map[r][c] = city;
					bfs(city, r, c);
					city += 1;
				}
			}
		}
//		for (int[] m : map) {
//			System.out.println(Arrays.toString(m));
//		}
		build();
		MST();
		int root = find(parent[1]);
		for(int i=1;i<city;i++) {
			if(root!=find(parent[i])) {
				System.out.println(-1);
				return;
			}
		}
		System.out.println(ans);
	}

	private static void MST() {
		parent = new int[city];
		for (int i = 1; i < parent.length; i++) {
			parent[i] = i;
		}

		while(pq.size()>0) {
			Info info = pq.poll();
			if(union(info.a,info.b)) {
				ans+=info.dist;
			}
		}
	}

	private static int find(int curr) {
		
		if(curr==parent[curr]) return curr;
		return parent[curr]=find(parent[curr]);

	}

	private static boolean union(int i, int j) {
		int iRoot = find(i);
		int jRoot = find(j);
		if (iRoot == jRoot) return false;

		parent[jRoot] = iRoot;
		return true;

	}

	private static void build() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] != 0) {
					int root = map[r][c];
					int nextRoot = map[r][c];
					int moveR = 0, moveC = 0;
					for (int i = r + 1; i < N; i++) {
						nextRoot = map[i][c];
						if (nextRoot == root || (nextRoot != root&&nextRoot!=0))
							break;
						moveR += 1;
					}
					
					if (nextRoot != root && moveR > 1&&nextRoot!=0) {
						pq.add(new Info(root,nextRoot,moveR));
					}
					nextRoot = map[r][c];
					for (int i = c + 1; i < M; i++) {
						nextRoot = map[r][i];
						if (nextRoot == root || (nextRoot != root&&nextRoot!=0))
							break;
						moveC += 1;
					}
					if (nextRoot != root && moveC > 1&&nextRoot!=0) {
						pq.add(new Info(root,nextRoot,moveC));
					}
				}
			}
		}
	}

	private static void bfs(int city, int r, int c) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] { r, c });
		while (q.size() > 0) {
			int[] now = q.poll();

			for (int d = 0; d < deltas.length; d++) {
				int nx = now[0] + deltas[d][0];
				int ny = now[1] + deltas[d][1];

				if (nx < 0 || nx >= N || ny < 0 || ny >= M)
					continue;

				if (map[nx][ny] == 1 && !visited[nx][ny]) {
					q.add(new int[] { nx, ny });
					visited[nx][ny] = true;
					map[nx][ny] = city;
				}
			}
		}

	}

}
