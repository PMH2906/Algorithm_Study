package s;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 
 * @author 박미희
 * @since 2022. 8. 5.
 * @see
 * @git
 * @youtube
 * @performance
 * @category #
 * @note
 */
public class BJ_2493_탑 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int N;
	static int[] maxHeightInfo;
	static int[] minHeightInfo;
	static int cnt;

	static Stack<int[]> tops = new Stack<>();

	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(input.readLine());

		tokens = new StringTokenizer(input.readLine());
		output.append(0 + " ");
		tops.add(new int[] { 1, Integer.parseInt(tokens.nextToken()) });

		for (int i = 1; i < N; i++) {
			int now = Integer.parseInt(tokens.nextToken());
			while (true) {
				int[] check = tops.peek();
				if (now > check[1]) {
					tops.pop();
					if (tops.size() == 0) {
						output.append(0 + " ");
					tops.add(new int[] { i, now });
					}
				}
				if (now < check[1]) {
					output.append(check[0] + " ");
					tops.add(new int[] { i, now });
				}
			}
		}
	}
}
