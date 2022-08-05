package s;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_12891_DNA비밀번호 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int s;
	static int p;
	static String[] str;
	static int[] hasCnt = new int[4];
	static int[] newHasCnt = {0, 0, 0, 0};
	static int result;

	static int toIdx(String ch) {
		if (ch.equals("A"))
			return 0;
		else if (ch.equals("C"))
			return 1;
		else if (ch.equals("G"))
			return 2;
		else if (ch.equals("T"))
			return 3;
		return -1;

	}

	public static void main(String[] args) throws Exception {
		result = 0;
		tokens = new StringTokenizer(input.readLine());
		s = Integer.parseInt(tokens.nextToken());
		p = Integer.parseInt(tokens.nextToken());

		str = input.readLine().split("");

		tokens = new StringTokenizer(input.readLine());

		for (int i = 0; i < 4; i++) {
			hasCnt[i] = Integer.parseInt(tokens.nextToken());
		}

		for(int i=0;i<p;i++) {
			newHasCnt[toIdx(str[i])]++;
		}
		int cnt=0;
		for (int j = 0; j < 4; j++) {
			if (newHasCnt[j] >= hasCnt[j])
				cnt += 1;
			if (cnt == 4)
				result += 1;
		}
		for (int i = 1; i <= s - p; i++) {
			newHasCnt[toIdx(str[i-1])]--;
			newHasCnt[toIdx(str[i-1+p])]++;
			
			cnt=0;
			for (int j = 0; j < 4; j++) {
				if (newHasCnt[j] >= hasCnt[j])
					cnt += 1;
				if (cnt == 4)
					result += 1;
			}
		}
		System.out.println(result);
	}

}
