package Algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_16926_배열돌리기2 {
	static StringBuilder output = new StringBuilder();
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N;
	static int M;
	static int R;
	static int [][]array;
	static List<int[]>[] loc;
	static int[][] deltas= {{0,1},{1,0},{0,-1},{-1,0}}; //우, 하, 좌, 상
	
	public static void main(String[] args) throws Exception {
		tokens=new StringTokenizer(input.readLine());
		N=Integer.parseInt(tokens.nextToken());
		M=Integer.parseInt(tokens.nextToken());
		R=Integer.parseInt(tokens.nextToken());
		array = new int[N][M];
		for(int r=0;r<N;r++) {
			tokens=new StringTokenizer(input.readLine());
			for(int c=0;c<M;c++) {
				array[r][c]=Integer.parseInt(tokens.nextToken());
			}
		}
		loc = new ArrayList[Math.min(N,M)/2];
		for(int i=0;i<Math.min(N,M)/2;i++) {
			loc[i] = new ArrayList<int[]>();
		}
		int dist_r=N-1;
		int dist_c=M-1;
		
		for(int i=0;i<Math.min(N,M)/2;i++) {
			int dir=-1;
			int r=i;
			int c=i;
			loc[i].add(new int[]{r,r,array[r][r]});
			
			for(int z=0;z<2;z++) {
				dir+=1;
				for(int j=0;j<dist_c;j++) {
					r+=deltas[dir][0];
					c+=deltas[dir][1];
					loc[i].add(new int[]{r,c,array[r][c]});
				}
				dir+=1;
				for(int j=0;j<dist_r;j++) {
					r+=deltas[dir][0];
					c+=deltas[dir][1];
					loc[i].add(new int[]{r,c,array[r][c]});
				}
			}
			loc[i].remove(loc[i].size()-1);
			dist_r-=2;
			dist_c-=2;
			for(int j=0;j<loc[i].size();j++) array[loc[i].get(j)[0]][loc[i].get(j)[1]]=loc[i].get((j+R)%loc[i].size())[2];	
		}

		for(int r=0;r<N;r++) {
			for(int c=0;c<M;c++) System.out.print(array[r][c]+" ");
			System.out.println();
		}
	}
}
