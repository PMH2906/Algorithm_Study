import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ16929_twodots {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N, M;
	static char[][] map;
	static boolean[][] visited;
	static int[][] deltas = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

	public static void main(String[] args) throws Exception {
		tokens = new StringTokenizer(input.readLine());

		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());

		map = new char[N][M];
		visited = new boolean[N][M];

		for (int r = 0; r < N; r++) {
			map[r] = input.readLine().toCharArray();
		}
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (!visited[r][c]) {
					visited[r][c]=true;
					if (dfs(r, c, 1, r, c)) {
						System.out.print("Yes");
						return;
					}
				}
			}
		}
		System.out.println("No");

	}

//    private static boolean bfs(int x, int y) {
//        char color = map[x][y];
//        Queue<int[]> q = new LinkedList<>();
//        q.add(new int[]{x,y});
//
//        while(q.size()>0){
//            int now[] = q.poll();
//            if(now[0]==x&&now[1]==y) return true;
//
//            for(int d=0;d<deltas.length;d++){
//                int nx = now[0]+deltas[d][0];
//                int ny = now[1]+deltas[d][1];
//
//                if(nx<0||nx>=N||ny<0||ny>=M) continue;
//
//                if(!visited[nx][ny]&&map[nx][ny]==color){
//                    q.add(new int[]{nx,ny});
//                    visited[nx][ny]=true;
//                }
//            }
//
//        }
//        return false;
//    }
	private static boolean dfs(int r, int c, int cnt, int sR, int sC) {
//		System.out.println(r+" "+c+" "+sR+" "+sC+map[r][c]+" "+map[sR][sC]+" "+cnt);
		if (cnt >= 4 && check(r, c, sR, sC)) {
			return true;
		}

		for (int d = 0; d < deltas.length; d++) {
			int nx = r + deltas[d][0];
			int ny = c + deltas[d][1];

			if (nx < 0 || nx >= N || ny < 0 || ny >= M)
				continue;

			if (!visited[nx][ny] && map[nx][ny] == map[sR][sC]) {
				visited[nx][ny]=true;
				if(dfs(nx, ny, cnt + 1, sR, sC)) {
					return true;
				};
				visited[nx][ny]=false;
			}

		}

		return false;
	}

	private static boolean check(int r, int c, int sR, int sC) {
		for(int d=0;d<deltas.length;d++) {
//			System.out.println(r+" "+c+" "+(sR+deltas[d][0])+" "+(sC+deltas[d][1]));
			if(r==sR+deltas[d][0]&&c==sC+deltas[d][1]) return true;
		}
		
		return false;
	}

}