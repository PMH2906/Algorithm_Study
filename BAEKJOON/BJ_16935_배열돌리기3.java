import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_16935_배열돌리기3 {
	static StringBuilder output = new StringBuilder();
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N;
	static int M;
	static int R;
	static int[][] array;

	static void upDown() {
		for (int r = 0; r < N / 2; r++) {
			for (int c = 0; c < M; c++) {
				int temp = array[r][c];
				array[r][c] = array[N - r - 1][c];
				array[N - r - 1][c] = temp;
			}
		}
	}

	static void rightLeft() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M / 2; c++) {
				int temp = array[r][c];
				array[r][c] = array[r][M - c - 1];
				array[r][M - c - 1] = temp;
			}
		}
	}

	static void clockWise90() {
			int[][] temp=new int[M][N];
			for(int r=0;r<M;r++) {
				for(int c=0;c<N;c++) {
					temp[r][c]=array[N-c-1][r];
				}
			}
			array=temp;
			N=array.length;
			M=array[0].length;
	}

	static void counterClockWise90() {
		int[][] temp=new int[M][N];
		for(int r=0;r<M;r++) {
			for(int c=0;c<N;c++) {
				temp[r][c]=array[c][M-r-1];
			}
		}
		array=temp;
		N=array.length;
		M=array[0].length;
	}
	static void groupClockWise() {
			for(int r=0;r<N/2;r++) {
				for(int c=0;c<M/2;c++) {
					int temp=array[r][c];
					array[r][c]=array[N/2+r][c];
					array[N/2+r][c]=array[N/2+r][M/2+c];
					array[N/2+r][M/2+c]=array[r][M/2+c];
					array[r][M/2+c]=temp;
					
				}
			}
	}
	static void groupCounterClockWise() {
		for(int r=0;r<N/2;r++) {
			for(int c=0;c<M/2;c++) {
				int temp=array[r][c];
				array[r][c]=array[r][M/2+c];
				array[r][M/2+c]=array[N/2+r][M/2+c];
				array[N/2+r][M/2+c]=array[N/2+r][c];
				array[N/2+r][c]=temp;
				
			}
		}
	}
	public static void main(String[] args) throws Exception {
		tokens=new StringTokenizer(input.readLine());
		N=Integer.parseInt(tokens.nextToken());
		M=Integer.parseInt(tokens.nextToken());
		R=Integer.parseInt(tokens.nextToken());
		array=new int[N][M];
		for(int r=0;r<N;r++) {
			tokens=new StringTokenizer(input.readLine());
			for(int c=0;c<M;c++) {
				array[r][c]=Integer.parseInt(tokens.nextToken());
			}
		}
		tokens=new StringTokenizer(input.readLine());
		while(tokens.hasMoreTokens()) {
			String n = tokens.nextToken();
			if(n.equals("1")) upDown();
			else if(n.equals("2")) rightLeft();
			else if(n.equals("3")) clockWise90();
			else if(n.equals("4")) counterClockWise90(); 
			else if(n.equals("5")) groupClockWise();
			else if(n.equals("6")) groupCounterClockWise();
			
		}
		for(int r=0;r<N;r++) {
			for(int c=0;c<M;c++) {
				output.append(array[r][c]+" ");
			}
			output.append("\n");
		}
		System.out.println(output);
	}

}
