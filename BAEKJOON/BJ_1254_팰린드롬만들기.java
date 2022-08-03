package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_1254_팰린드롬만들기 {
	public static void main(String[] args) throws Exception {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		String str = input.readLine();
		int result = 0;
		
		out:for(int i=0;i<str.length();i++) {
			for(int j=str.length()-1,z=0;j>=i;j--,z++) {
				if(str.charAt(i+z)!=str.charAt(j))
					break;
				if(i==j) {
					result = str.length()+i;
					break out;
				}
			}
		}
		System.out.println(result);
		
	}
}
