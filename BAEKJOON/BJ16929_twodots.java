import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// dfs탐색 => 첫번째 점 포함 4개 이상 방문하면서, 첫시작점과 인접하면 사이클 존재 
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
					visited[r][c]=true; // 이미 사이클이 존재하지 않는 점은 더 이상 탐색하지 X => visited 리셋 X 
					if (dfs(r, c, 1, r, c)) {
						System.out.print("Yes");
						return;
					}
				}
			}
		}
		System.out.println("No");

	}

	private static boolean dfs(int r, int c, int cnt, int sR, int sC) {
		// 점을 4개 이상 방문하면서, 시작점과 인접하면 사이클 존재 
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
					return true; // 이미 사이클 존재하므로 더 이상 탐색하지 X, 바로 리턴해주기 
				};
				visited[nx][ny]=false; // 같은 알파벳이지만, 시작점이 다른 사이클도 탐색가능하도록 visited 돌려주기!!!! 
			}

		}

		return false;
	}

	// 체크할 점이 시작점의 동서남북 중 하나에 위치하면 사이클 생성! 
	private static boolean check(int r, int c, int sR, int sC) {
		for(int d=0;d<deltas.length;d++) {
			if(r==sR+deltas[d][0]&&c==sC+deltas[d][1]) return true; 
		}
		
		return false;
	}

}