import java.util.*;
import java.io.*;

// 체스판은 검은색 흰색 번갈아서 위치함!
// 비숍은 대각선으로만 움직이므로 검은색, 흰색 위치에한 비숍은 서로에게 영향을 줄 수 없음!
// 따라서 검은색, 흰색 따로 백트래킹 탐색하면서 시간 줄이기! 
// 또한 0,0부터 탐색하므로, 탐색하는 위치의 아래 대각선에는 비숍이 없음!
// 따라서 위의 대각선에 비숍이 위치하는지만 체크하면 됨!
public class BJ1799_비숍 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int N, map[][], dx[]= {-1,-1}, dy[]= {-1,1}, ans[] = new int[2]; // 상좌, 상우 탐색 
	static boolean colors[][], locate[][];

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(input.readLine());
		map = new int[N][N];
		colors = new boolean[N][N];
		locate = new boolean[N][N];
		for (int r = 0; r < N; r++) {
			tokens = new StringTokenizer(input.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(tokens.nextToken());
				
				// 체스판 색상! 중요!!!!!!!!!
				colors[r][c] = (r % 2 == 0 && c % 2 == 0) || (r % 2 != 0 && c % 2 != 0);
			}
		}
		
		// 0,0부터 탐색하기 위해 -1 대입!
		dfs(-1, true, 0);
		dfs(-1, false, 0);
		
		System.out.println(ans[0]+ans[1]);

	}

	private static void dfs(int index, boolean black, int cnt) {
		for(int i = index+1;i<N*N; i++) {
			
			// 2차원 배열을 1차원으로 탐색하기 위해 x,y 위치를 아래와 같이 잡는다!!! 중요!!!!!!!!!
			int x = i/N; // 행 
			int y = i%N; // 열 
			
			// 현재 탐색중인 색상이 아니거나, 비숍을 놓을 수 없거나, 대각선에 비숍이 존재하면 
			if(colors[x][y]!=black || map[x][y]==0 || !check(x,y)) {
				continue;
			}
			
			// 비숍 위치하기 
			locate[x][y]=true;
			// 백트래킹 
			dfs(i,black,cnt+1); // cnt증가 후 다음 위치로 
			// 비숍 빼기 
			locate[x][y]=false; // cnt증가 안된상태에서 다음 위치로 
		}
		
		// 전체 탐색 후 가장 큰 횟수 찾기 
		ans[black?1:0] = Math.max(ans[black?1:0],cnt);
	}

	private static boolean check(int x, int y) {
		// 0,0 부터 탐색하므로 현재 아래에는 비숍이 없음. 따라서 윗대각선만 체크!
		for(int d=0;d<2;d++) {
			int nx = x;
			int ny = y;
			// map 범위에서 모든 윗 대각선 탐색 
			while(true) {
				// 범위 넘어가면 break 
				if(nx<0||nx>=N||ny<0||ny>=N) break;
				
				// 비숍이 위치하면 break
				if(locate[nx][ny]) return false;
				
				nx += dx[d];
				ny += dy[d];
			}
		}
		return true;
	}

}
