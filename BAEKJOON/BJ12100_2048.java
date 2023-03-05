import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ12100_2048 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N;
	static long ans = 0, map[][], newMap[][];
	static int[][] deltas = { { 0, 0 }, { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(input.readLine());
		map = new long[N][N];
		newMap = new long[N][N];
		
		for (int r = 0; r < N; r++) {
			tokens = new StringTokenizer(input.readLine());
			for (int c = 0; c < N; c++) {
				newMap[r][c] = Integer.parseInt(tokens.nextToken());
			}
		}
		fiveMove(0, new int[10]);
		System.out.println(ans);
	}

	private static void fiveMove(int nth, int[] choosed) {
		if (nth == choosed.length) {
			for(int i=0;i<N;i++) {
				map[i]=newMap[i].clone();
			}
			
			for (int i = 0; i < choosed.length; i++) {
				ans = Math.max(ans, oneMove(choosed[i]));
			}
			
			return;
		}
		for (int i = 1; i <= 4; i++) {
			choosed[nth] = i;
			fiveMove(nth + 1, choosed);
		}
	}

	public static long oneMove(int dir) {
		
		long maxNum = Integer.MIN_VALUE;
		int r=-1, c=-1;
		for(int i=0;i<N;i++) {
			if(dir==1) { // 상 
				r=0;c=i;
			} else if(dir==2) { // 하 
				r=N-1;c=i;
			} else if(dir==3) { // 좌  
				r=i;c=0;
			} else if(dir==4) { // 우 
				r=i;c=N-1;
			}
			
			int totalEmpty = 0;
			while(true) { 
				
				if(r<0||r>=N||c<0||c>=N) break;
				
				// 현재 보고 있는 위치 
				int empty = 0;
				if(map[r][c]==0) empty++;
				int nr = r, nc = c;
				
				while(true){
					
					// 현재 보고 있는 위치에서 다음 위치가 0 or 현재 보고있는 위치와 같은 수 or 현재 보고있는 위치와 다른 수로 나누어 처리 
					nr += deltas[dir][0];
					nc += deltas[dir][1];
										
					if(nr<0||nr>=N||nc<0||nc>=N) { // 만약 0으로 탐색 종료되었다면 현재 탐색한 숫자 위치 옮겨 주기 
						int movedR = deltas[dir][0]*totalEmpty*-1;
						int movedC = deltas[dir][1]*totalEmpty*-1;
						map[r+movedR][c+movedC]=map[r][c];
						if(totalEmpty!=0) map[r][c]=0;
						maxNum = Math.max(maxNum, map[r+movedR][c+movedC]);
						r=nr; c=nc;
						totalEmpty+=empty;
						break;
					}
					
					if(map[nr][nc]==0) empty++; // 0이면 빈칸 갯수 증가 
					
					else if(map[r][c]!=map[nr][nc]) { // 다르다면 현재 탐색한 숫자 위치 옮겨 주고, 다음 위치로 현재 위치를 옮겨주기 
						int movedR = deltas[dir][0]*totalEmpty*-1;
						int movedC = deltas[dir][1]*totalEmpty*-1;
						map[r+movedR][c+movedC]=map[r][c];
						if(totalEmpty!=0) map[r][c]=0;
						maxNum = Math.max(maxNum, map[r+movedR][c+movedC]);
						r=nr; c=nc;
						totalEmpty+=empty;
						break;
					}
					else if(map[r][c]==map[nr][nc]) { // 같으다면 현재 탐색한 숫자 **2 한 후 옮기고, 다음 위치+탐색방향으로 현재 위치 옮겨주기. 이때 빈칸 갯수 하나 더 증가해줘야 됨!! 
						int movedR = deltas[dir][0]*totalEmpty*-1;
						int movedC = deltas[dir][1]*totalEmpty*-1;
						map[r+movedR][c+movedC]=map[r][c]+map[r][c];
						if(totalEmpty!=0) map[r][c]=0;
						map[nr][nc]=0;
						maxNum = Math.max(maxNum, map[r+movedR][c+movedC]);
						r=nr+deltas[dir][0]; c=nc+deltas[dir][1];
						totalEmpty+=empty+1;
						break;
					}
					
				}
				
			}
		}
		return maxNum;
		
	}
}
