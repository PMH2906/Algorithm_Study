import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2961_도영이가만든맛있는음식 {
	static StringBuilder output = new StringBuilder();
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N;
	static int[][] info;
	static boolean[] checked;
	static int Min_sum = Integer.MAX_VALUE;

	static void select(int toCheck, boolean[] checked) {
		int S_sum = 1;
		int B_sum = 0;
		int cnt = 0;
		if (toCheck == checked.length) {
			for (int i = 0; i < N; i++) {
				if (checked[i]) {
					cnt += 1;
					S_sum *= info[i][0];
					B_sum += info[i][1];
				}
			}
			if (cnt == 0)
				return;
			Min_sum = Math.min(Min_sum, Math.abs(S_sum - B_sum));
			return;
		}

		checked[toCheck] = true;
		select(toCheck + 1, checked);
		checked[toCheck] = false;
		select(toCheck + 1, checked);
	}

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(input.readLine());
		info = new int[N][2];
		checked = new boolean[N];

		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(input.readLine());
			info[i][0] = Integer.parseInt(tokens.nextToken());
			info[i][1] = Integer.parseInt(tokens.nextToken());
		}

		select(0, checked);

		System.out.println(Min_sum);

	}
}
