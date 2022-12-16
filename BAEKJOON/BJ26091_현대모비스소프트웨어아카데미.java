import java.util.*;
import java.io.*;

public class BJ26091_현대모비스소프트웨어아카데미 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N, M, score[], ans;

	public static void main(String args[]) throws Exception {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());

		score = new int[N];

		tokens = new StringTokenizer(input.readLine());
		for (int i = 0; i < N; i++) {
			score[i] = Integer.parseInt(tokens.nextToken());
		}

		Arrays.sort(score);

		int start = 0, back = N - 1;
		while (true) {
			if (start >= back)
				break;
			if (score[start] + score[back] >= M) {
				ans++;
				start++;
				back--;
			} else {
				start++;
			}
		}

		System.out.println(ans);
	}
}
