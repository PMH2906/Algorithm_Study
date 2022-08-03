import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 달팽이숫자 {
	static StringBuilder output = new StringBuilder();
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int[][] deltas = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static int dir = 0;
	static int cnt;
	static int[][] grid;
	static int number=1;
	public static void main(String args[]) throws Exception {

		int T = Integer.parseInt(input.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			int x = 0;
			int y = -1;
			dir = 0;
			number = 1;
			N = Integer.parseInt(input.readLine());
			cnt = N;
			grid = new int[N][N];

			while(cnt!=0) {
				for (int j = 0; j < cnt; j++) {
					x += deltas[dir][0];
					y += deltas[dir][1];
					grid[x][y] = number++;
				}

				dir = (dir + 1) % 4;

				if (dir == 1 || dir == 3) {
					cnt--;
				}
			}
			output.append("#"+test_case+"\n");
			for (int[] nums : grid) {
				for(int num : nums) {
					output.append(num+" ");
				}
				output.append("\n");
			}
		}//TC
		System.out.println(output);
	}
}
