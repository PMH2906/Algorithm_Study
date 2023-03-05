import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ12100_2048 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N;
	static float ans = Integer.MIN_VALUE, map[][];;
	static int[][] deltas = { { 0, 0 }, { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(input.readLine());
		map = new float[N][N];

		for (int r = 0; r < N; r++) {
			tokens = new StringTokenizer(input.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(tokens.nextToken());
			}
		}
		fiveMove(0, new int[4]);
		System.out.println(ans);
	}

	private static void fiveMove(int nth, int[] choosed) {
		if (nth == choosed.length) {
			for (int i = 0; i < choosed.length; i++) {
				ans = Math.max(ans, oneMove(choosed[i]));
			}
			for (float a[] : map) {
				System.out.println(Arrays.toString(a));
			}
			System.out.println();
			return;
		}
		for (int i = 1; i <= 4; i++) {
			choosed[nth] = i;
			fiveMove(nth + 1, choosed);
		}
	}

	public static float oneMove(int dir) {
		float maxNum = Integer.MIN_VALUE;
		if (dir == 1 || dir == 2) { // 상하
			
				
		} else { // 좌우
			
		}
		return maxNum;
	}
}
