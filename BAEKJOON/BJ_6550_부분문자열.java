package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_6550_부분문자열 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static String data;
	static String s;
	static String t;
	static int idx;

	public static void main(String[] args) throws Exception {
		while ((data = input.readLine()) != null && data.length() > 0) {
			tokens = new StringTokenizer(data);
			s = tokens.nextToken();
			t = tokens.nextToken();

			idx = 0;
			for(int i=0;i<t.length();i++) {
				if(s.charAt(idx)==t.charAt(i)) idx++;
				if(idx==s.length()) break;
			}
			if(idx==s.length()) output.append("Yes\n");
			else output.append("No\n");
		}
		System.out.println(output);
	}
}
