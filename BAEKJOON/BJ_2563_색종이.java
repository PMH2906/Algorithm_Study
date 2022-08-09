import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2563_색종이 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int[][] paper;
	static int area = 0;

	public static void main(String[] args) throws Exception {
		int N = Integer.parseInt(input.readLine());
		paper = new int[100][100];
		
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(input.readLine());
			int X = Integer.parseInt(tokens.nextToken());
			int Y = Integer.parseInt(tokens.nextToken());

			for(int x=X-1;x<X+9;x++) {
				for(int y=Y-1;y<Y+9;y++) paper[x][y]=1;
			}
		}
		for(int x=0;x<100;x++) {
			for(int y=0;y<100;y++){
				if(paper[x][y]==1) area+=1;
			}
		}
		System.out.println(area);

	}

}
