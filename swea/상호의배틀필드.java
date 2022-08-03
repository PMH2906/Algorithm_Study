package s;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 상호의배틀필드 {
	static StringBuilder output = new StringBuilder();
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	static int R;
	static int C;
	static int N;
	static String[][] map;
	static int x;
	static int y;
	static int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}};
	static String[] dirS = {"^","v","<",">"};
	static int dirN;
	static int dir(String dirS) {
		if(dirS.equals("U")||dirS.equals("^")) return 0;
		else if(dirS.equals("D")||dirS.equals("v")) return 1;
		else if(dirS.equals("L")||dirS.equals("<")) return 2;
		else if(dirS.equals("R")||dirS.equals(">")) return 3;
		return -1;
	}
	static int[] move(int r, int c, int dirN){
		int nx = r+deltas[dirN][0];
		int ny = c+deltas[dirN][1];
		int[] result = {r,c};
		map[r][c]=dirS[dirN];
		if(nx<0||nx>=R||ny<0||ny>=C) return result;
		if(map[nx][ny].equals(".")) {
			map[nx][ny]=dirS[dirN];
			map[r][c]=".";
			result[0]=nx;
			result[1]=ny;
		}
		
		return result;
	}
	static void soot(int r, int c, int dirN) {
		while(true) {
			r+=deltas[dirN][0];
			c+=deltas[dirN][1];
			if(r<0||r>=R||c<0||c>=C) break;
			
			if(map[r][c].equals("*")) {
				map[r][c] = ".";
				break;
			}
			if(map[r][c].equals("#")) break;
		}		
	}
	public static void main(String args[]) throws Exception
	{
		int T;
		T=Integer.parseInt(input.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			tokens = new StringTokenizer(input.readLine());
			R = Integer.parseInt(tokens.nextToken());
			C = Integer.parseInt(tokens.nextToken());

			map = new String[R][];
			
			for(int r=0;r<R;r++) {
				String[] line = input.readLine().split("");
				map[r] = line;
				for(int c=0;c<C;c++) {
					if(map[r][c].equals(">") || map[r][c].equals("<") || map[r][c].equals("v")|| map[r][c].equals("^")) {
						x=r;y=c;
					}
				}
			}
			tokens = new StringTokenizer(input.readLine());
			N = Integer.parseInt(tokens.nextToken());
			
			String[] action = input.readLine().split("");
			
			for(int i=0;i<N;i++) {
				if(dir(action[i])==-1){
					//S
					soot(x,y,dir(map[x][y]));
				}
				else {
					//move or 방향전환
					dirN = dir(action[i]);
					int []result = move(x,y,dirN);
					x=result[0];
					y=result[1];
				}	
			}
			
			
			output.append("#"+test_case+" ");
			for(int r=0;r<R;r++) {
				for(int c=0;c<C;c++) {
					output.append(map[r][c]);
				}
				output.append("\n");
			}
		}
		System.out.println(output);
	}
}
