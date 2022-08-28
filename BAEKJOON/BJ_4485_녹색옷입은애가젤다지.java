package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**

@author 박미희
@since 2022. 8. 26.
@see
@git
@youtube
@performance
@category #
@note */
public class BJ_4485_녹색옷입은애가젤다지{
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int N, t=0;
	static int[][] map;
	static int [][] deltas = {{-1,0},{1,0},{0,-1},{0,1}};
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	static int[][] distance;
	public static class Node implements Comparable<Node>{
		int x, y, cnt;

		public Node(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cnt, o.cnt);
		}
	}
	
	public static void main(String[] args) throws IOException {
		while(true) {
			N = Integer.parseInt(input.readLine());
			if(N==0) break;
			t++;
			map = new int[N][N];
			distance = new int[N][N];
			for(int r=0;r<N;r++) {
				tokens = new StringTokenizer(input.readLine());
				for(int c=0;c<N;c++) {
					map[r][c] = Integer.parseInt(tokens.nextToken());
					distance[r][c] = Integer.MAX_VALUE;
				}
			}
			pq.add(new Node(0,0,map[0][0]));
			distance[0][0] = map[0][0];
			find();
			output.append(String.format("Problem %d: %d\n",t, distance[N-1][N-1]));
		}
		System.out.println(output);
	}

	private static void find() {

		while(!pq.isEmpty()) {
			Node now = pq.poll();

			for(int d=0;d<deltas.length;d++) {
				int nx = now.x+deltas[d][0];
				int ny = now.y+deltas[d][1];
				if(nx<0||nx>=N||ny<0||ny>=N) continue;
				if(now.cnt+map[nx][ny] <distance[nx][ny]) {
					pq.add(new Node(nx,ny,now.cnt+map[nx][ny]));
					distance[nx][ny] = now.cnt+map[nx][ny];
				}
			}
		}
	}
	
}