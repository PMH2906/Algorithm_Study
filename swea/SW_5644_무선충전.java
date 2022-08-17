import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**

@author 박미희
@since 2022. 8. 17.
@see
@git
@youtube
@performance
@category #
@note */
public class SW_5644_무선충전{
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens,tokens2;
	static int M, A;
	static int [] moveA, moveB, aP, bP;
	static final int[][] deltas = {{0,0},{0,-1},{1,0},{0,1},{-1,0}}; // 이동X, 상, 우, 하, 좌
	static int[][] battery; // x,y,c(범위),p(처리량)
	
	public static boolean distance(int BCX, int BCY, int x, int y, int C) {
		return Math.abs(BCX-x)+Math.abs(BCY-y)<=C?true:false;
	}
	
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			tokens = new StringTokenizer(input.readLine());
			M = Integer.parseInt(tokens.nextToken()); // 이동시간
 			A = Integer.parseInt(tokens.nextToken()); // BC 갯수
 			moveA = new int[M];
 			moveB = new int[M];
 			battery = new int[A][4];
 			aP = new int[]{1,1};
 			bP = new int[]{10,10};

 			tokens = new StringTokenizer(input.readLine());
 			tokens2 = new StringTokenizer(input.readLine());
 			for(int i=0;i<M;i++) {
 				moveA[i] = Integer.parseInt(tokens.nextToken()); 
 				moveB[i] = Integer.parseInt(tokens2.nextToken()); 
 			}
 			for(int i=0;i<A;i++) {
 				tokens = new StringTokenizer(input.readLine());
 				for(int j=0;j<4;j++) battery[i][j] = Integer.parseInt(tokens.nextToken());
 			}
 			boolean checkA;
 			boolean checkB;
 			int result=0;
 	
 			//int maxSum=Integer.MIN_VALUE;
 			for(int m=0;m<M+1;m++) {
 				int [] P=new int[3];
 				for(int a=0;a<A;a++) {
 					checkA=distance(battery[a][0],battery[a][1],aP[0],aP[1],battery[a][2]);
 					checkB=distance(battery[a][0],battery[a][1],bP[0],bP[1],battery[a][2]);
 					if(checkA&&checkB) {
 						if(P[0]!=0) {
 							P[1]=Math.max(P[1], Math.min(P[0], battery[a][3]));
 							P[2]=Math.max(P[2], Math.min(P[0], battery[a][3]));
 						}
 						P[0] = Math.max(P[0], battery[a][3]);
 					}
 					else if(checkA) P[1] = Math.max(P[1], battery[a][3]);
 					else if(checkB) P[2] = Math.max(P[2], battery[a][3]);
 				}
 				Arrays.sort(P);

 				result+=P[2]+P[1];

 				if(m==M) break;
 				aP[0]+=deltas[moveA[m]][0];
 				aP[1]+=deltas[moveA[m]][1];
 				bP[0]+=deltas[moveB[m]][0];
 				bP[1]+=deltas[moveB[m]][1];
 			}
 			output.append(String.format("#%d %d\n", test_case,result));
		}
		System.out.println(output);
	}
}
