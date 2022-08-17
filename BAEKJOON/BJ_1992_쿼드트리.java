package Algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1992_쿼드트리 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int N;
	static boolean check;
	static String[][] picture;
	private static void find(int N, int R, int C) {
		check=false;
		output.append("(");
		for(int r=0;r<N/2;r++) {
			for(int c=0;c<N/2;c++) {
				if(!picture[R+r][C+c].equals(picture[R][C])) check=true;
			}
		}
		if(check) find(N/2,R,C);
		else output.append(picture[R][C]);
		check=false;
		
		for(int r=0;r<N/2;r++) {
			for(int c=N/2;c<N;c++) {
				if(!picture[R+r][C+c].equals(picture[R][C+N/2])) check=true;
			}
		}
		if(check) find(N/2,R,C+N/2);
		else output.append(picture[R][C+N/2]);
		check=false;
		
		for(int r=N/2;r<N;r++) {
			for(int c=0;c<N/2;c++) {
				if(!picture[R+r][C+c].equals(picture[R+N/2][C])) check=true;
			}
		}
		if(check) find(N/2,R+N/2,C);
		else output.append(picture[R+N/2][C]);
		check=false;
		
		for(int r=N/2;r<N;r++) {
			for(int c=N/2;c<N;c++) {
				if(!picture[R+r][C+c].equals(picture[R+N/2][C+N/2])) check=true;
			}
		}
		if(check) find(N/2,R+N/2,C+N/2);
		else output.append(picture[R+N/2][C+N/2]);
		check=false;
		output.append(")");
		if(N/2==1) return;
	}
	
	public static void main(String[] args) throws Exception {
		tokens = new StringTokenizer(input.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		picture=new String[N][];
		for(int r=0;r<N;r++) {
			picture[r]=input.readLine().split("");
			for(int c=0;c<N;c++) {
				if(!picture[0][0].equals(picture[r][c])) check=true;
			}
		}
		if(!check) System.out.println(picture[0][0]);
		else find(N, 0,0);
		System.out.println(output);
	}
	

}
