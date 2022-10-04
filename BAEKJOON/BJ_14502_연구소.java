package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_14502_연구소 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int N, M, maxCnt = Integer.MIN_VALUE;
	static int zeroCnt = 0;
	static boolean[][] visited;
	static int[][] map, deltas = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static List<Position> position = new LinkedList<>();
	static Queue<Position> q = new LinkedList<>();
	
	public static class Position {
		int x, y;

		public Position(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	public static void main(String[] args) throws IOException {

		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		map = new int[N][M];
		for (int r = 0; r < N; r++) {
			tokens = new StringTokenizer(input.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(tokens.nextToken());
				if (map[r][c] == 0) {
					position.add(new Position(r, c));
					zeroCnt++;
				}

			}
		}

		select(0, 0, new Position[3]);
		System.out.println(maxCnt);
	}
	//벽 세울 위치 조합으로 선정
	private static void select(int nth, int start, Position[] choosed) {
		//System.out.println(position.size());
		if (nth == 3) {
			maxCnt = Math.max(maxCnt, countSafety(choosed));
			return;
		}
		for (int i = start; i < position.size(); i++) {
			choosed[nth] = position.get(i);
			select(nth+1, i + 1, choosed);
		}

	}

	// 바이러스 퍼진 뒤 안전 영역 갯수 세기
	private static int countSafety(Position[] choosed) {
		visited = new boolean[N][M];
		int changeZero=0;
		for (Position c : choosed) { // 벽 세우기
			map[c.x][c.y]=1;
		}
		for(int r=0;r<N;r++) {
			for(int c=0;c<M;c++) {
				if(map[r][c]==2&&!visited[r][c]) {
					visited[r][c]=true;
					q.add(new Position(r, c));
					while(q.size()>0) {
						Position now = q.poll();
						//System.out.println(now.x+" "+now.y);
						for(int d=0;d<deltas.length;d++) {
							int nx = now.x+deltas[d][0];
							int ny = now.y+deltas[d][1];
							if(nx<0||nx>=N||ny<0||ny>=M) continue;
							
							if(map[nx][ny]==0&&!visited[nx][ny]) {
								visited[nx][ny]=true;
								q.add(new Position(nx,ny));
								changeZero++;
							}
						}
					}
				}
			}
		}
		
		for (Position c : choosed) { // 다른 경우도 보기 위해 벽 철거
			map[c.x][c.y]=0;
		}
		return zeroCnt-(changeZero+3);
	}

}
