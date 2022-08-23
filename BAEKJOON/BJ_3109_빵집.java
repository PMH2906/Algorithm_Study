package Algorithm;
//String보다 char 쓰는것이 빠름
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_3109_빵집 {
	static StringBuilder output = new StringBuilder();
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int R, C,result=0,nr,nc;
	static boolean[][] map;
	static int[][] deltas= {{-1,1},{0,1},{1,1}};
	private static boolean dfs(int r, int c) {
		
		for(int d=0;d<deltas.length;d++) {
			nr = r+deltas[d][0];
			nc = c+deltas[d][1];
			if(nr<0||nr>=R||nc<0||nc>=C) continue;
			if(!map[nr][nc]) {
				map[nr][nc]=true;
				if(nc==C-1) {
					result++;
					return true;
				}
				
				if(dfs(nr,nc)) {
					return true;
				}
			}
		}
		return false;
	}
	public static void main(String[] args) throws Exception {
		tokens=new StringTokenizer(input.readLine());
		
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		map=new boolean[R][C];
		for(int r=0;r<R;r++) {
			char[] data=input.readLine().toCharArray();
			for(int c=0;c<C;c++) {
				if(data[c]=='x') map[r][c]=true;
			}
		}
		for(int r=0;r<R;r++) {
			dfs(r,0);
		}
		System.out.println(result);
		
	}
}
