import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_11660_구간합구하기5 {

	static StringBuilder output = new StringBuilder();
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int[][] table;
	static int[][] coor;

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		int N = Integer.parseInt(tokens.nextToken());
		int M = Integer.parseInt(tokens.nextToken());

		table = new int[N][N];
		coor = new int[M][4];
		for (int r = 0; r < N; r++) {
			tokens = new StringTokenizer(input.readLine());
			for (int c = 0; c < N; c++) {
				table[r][c] = Integer.parseInt(tokens.nextToken());
			}
		}
		for (int r = 0; r < M; r++) {
			tokens = new StringTokenizer(input.readLine());
			for (int c = 0; c < 4; c++) {
				coor[r][c] = Integer.parseInt(tokens.nextToken())-1;
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if(r==0&&c!=0) {
					table[r][c]+=table[r][c-1];
				}
				else if(c==0&&r!=0) {
					table[r][c]+=table[r-1][c];
				}
				else if(c!=0&&r!=0) table[r][c] += table[r][c-1]+table[r-1][c]-table[r-1][c-1];
			}
		}

		for(int i=0;i<M;i++) {
			if(coor[i][0]!=0&&coor[i][1]!=0)
				output.append(table[coor[i][2]][coor[i][3]]-table[coor[i][0]-1][coor[i][3]]-table[coor[i][2]][coor[i][1]-1]+table[coor[i][0]-1][coor[i][1]-1]+"\n");
			else if(coor[i][0]==0&&coor[i][1]==0) output.append(table[coor[i][2]][coor[i][3]]+"\n");
			else if(coor[i][0]!=0&&coor[i][1]==0) output.append(table[coor[i][2]][coor[i][3]]-table[coor[i][0]-1][coor[i][3]]+"\n");
			else output.append(table[coor[i][2]][coor[i][3]]-table[coor[i][2]][coor[i][1]-1]+"\n");
		}
		
		
		System.out.println(output);
	}

}
