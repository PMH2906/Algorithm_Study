package Algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 한빈이와SpotMark {
	static StringBuilder output = new StringBuilder();
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N;
	static int M;
	static int Max_sum;
	static int[] weights;
	static int[] choosed = new int[2];

	static void makeCombination(int nth, int[] choosed, int startIdx) {
		if (nth == choosed.length) {
			if (choosed[0]+choosed[1]>Max_sum&&choosed[0]+choosed[1]<=M)
				Max_sum = choosed[0]+choosed[1];
				return;
		}
		for (int i = startIdx; i < weights.length; i++) {
			choosed[nth] = weights[i];
			makeCombination(nth + 1, choosed, i + 1); // 사용한 요소 다음 것 부터 고려. 중복 안되므로
		}
	}

	public static void main(String args[]) throws Exception {
		int T;
		T = Integer.parseInt(input.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			Max_sum = -1;

			tokens = new StringTokenizer(input.readLine());
			N = Integer.parseInt(tokens.nextToken());
			M = Integer.parseInt(tokens.nextToken());

			weights = new int[N];
			
			tokens = new StringTokenizer(input.readLine());

			for (int i = 0; i < N; i++) {
				weights[i] = Integer.parseInt(tokens.nextToken());
			}
			makeCombination(0,choosed, 0);

			output.append(String.format("#%d %d\n", test_case,Max_sum));
		}
		System.out.println(output);
	}

}
