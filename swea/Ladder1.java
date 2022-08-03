import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Ladder1 {
	static StringBuilder output = new StringBuilder();
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int [][] ladder = new int[100][100];
	static int X;
	static int y;
	static int T;
	static void move(int r, int c) {
		ladder[r][c]=0;
		if(r==0) {
			X=c;
			return;
		}
		if(c-1>=0) {
			if(ladder[r][c-1]==1) {
				move(r,c-1);
				return ;
			}			
		}
		if(c+1<100) {
			if(ladder[r][c+1]==1) {
				move(r,c+1);
				return ;
			}			
		}
		move(r-1,c);
	}
	
	public static void main(String args[]) throws Exception
	{
		
		T = 10;

		for(int test_case = 1; test_case <= T; test_case++)
		{
			
			int tcNum = Integer.parseInt(input.readLine());
			for(int r=0;r<100;r++) {
				tokens = new StringTokenizer(input.readLine());
				for(int c=0;c<100;c++) {
					ladder[r][c]=Integer.parseInt(tokens.nextToken());
				}
			}
			
			for(int c=0;c<100;c++) {
				if(ladder[99][c]==2) {
					y=c; // 도착지점의 열 위치
					break;
				}
			}
	
			move(99,y);
			
			output.append(String.format("#%d %d\n", test_case, X ));

		}
		System.out.println(output);
	}
}
