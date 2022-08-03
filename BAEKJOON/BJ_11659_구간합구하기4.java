import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_11659_구간합구하기4 {
	static StringBuilder output = new StringBuilder();
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int[] number;
	static int N;
	static int M;
	static int [][] coor;
	public static void main(String[] args) throws Exception {
		tokens = new StringTokenizer(input.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		number = new int[N];
		coor = new int[M][2];
		
		tokens = new StringTokenizer(input.readLine());
		for(int i=0;i<N;i++) {
			number[i] = Integer.parseInt(tokens.nextToken());
		}
		
		for(int r=0;r<M;r++) {
			tokens = new StringTokenizer(input.readLine());
			for(int c=0;c<2;c++) {
				coor[r][c] = Integer.parseInt(tokens.nextToken())-1;
			}
		}
		
		for(int i=1;i<N;i++) {
			number[i]+=number[i-1];
		}
		for(int i=0;i<M;i++) {
			
			if(coor[i][0]==0) output.append(number[coor[i][1]]+"\n");
			else output.append(number[coor[i][1]]-number[coor[i][0]-1]+"\n");
		}
		System.out.println(output);
	}
}
