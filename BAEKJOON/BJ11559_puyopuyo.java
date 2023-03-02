import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ11559_puyopuyo {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer token;
	static StringBuilder output = new StringBuilder();
	static int ans = 0;
	static char[][] map;
	static int deltas[][] = {{0,-1},{0,1},{-1,0},{1,0}};

	public static void main(String[] args) throws IOException {
		map = new char[12][6];
		
		for(int i=0;i<12;i++) {
			map[i] = input.readLine().toCharArray();
		}

		
		while(true) {
			
			int cnt = bomb();
			
			if(cnt==0) break;
			ans++;
			gravity();
		}
		
		System.out.println(ans);
	}
	
	public static void gravity() {
		for(int c=0;c<6;c++) {
			int emptyCnt=0;
			for(int r=11;r>=0;r--) {
				if(map[r][c]!='.'&&emptyCnt!=0) {
					map[r+emptyCnt][c]=map[r][c];
					map[r][c]='.';
				}else if(map[r][c]=='.'){
					emptyCnt++;
				}
			}
		}
	}
	
	public static int bomb() {
		boolean visited[][] = new boolean[12][6];
		Queue<int[]> q = new LinkedList<>();
		Queue<int[]> selected = new LinkedList<>();
		int bombCnt=0;
		
		for(int r=0;r<12;r++) {
			for(int c=0;c<6;c++) {
				if(map[r][c]!='.') {
					q = new LinkedList<>();
					selected = new LinkedList<>();
					
					q.add(new int[] {r,c,map[r][c]});
					selected.add(new int[] {r,c});
					visited[r][c] = true;
					while(q.size()>0) {
						int[] now = q.poll();
						
						for(int d=0;d<deltas.length;d++) {
							int nx = now[0]+deltas[d][0];
							int ny = now[1]+deltas[d][1];
							
							if(nx<0||nx>=12||ny<0||ny>=6) continue;
							
							if(map[nx][ny]==(char)now[2]&&!visited[nx][ny]) {
								q.add(new int[] {nx,ny,map[nx][ny]});
								selected.add(new int[] {nx,ny});
								visited[nx][ny] = true;
								
							}
						}
					}
					if(selected.size()>=4) {
						while(selected.size()>0) {
							int[] now = selected.poll();
							map[now[0]][now[1]]='.';
						}
						bombCnt++;
					}
				}
			}
		}
		
		return bombCnt;
	}
	
}
