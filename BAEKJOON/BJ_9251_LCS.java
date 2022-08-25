package Algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// LCS(Longest Common Subsequence, 최장 공통 부분 수열)

public class BJ_9251_LCS {
	static StringBuilder output = new StringBuilder();
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static char[] str1, str2;
	static int[][] len;

	public static void main(String[] args) throws Exception {
		str1 = input.readLine().toCharArray(); // 열
		str2 = input.readLine().toCharArray(); // 행
		len = new int[str2.length+1][str1.length+1]; // LCS 길이를 메모이제이션 하기 위해 
		for(int i=1;i<=str2.length;i++) {
			for(int j=1;j<=str1.length;j++) { 
				if(str2[i-1]==str1[j-1]) { // 확인하는 str2문자와 str1의 문자가 같은 경우
					len[i][j] = len[i-1][j-1]+1; // str2의 전 문자열의 경우의 수 중 현재 확인하는 str1의 -1 위치의 경우의 수(왼쪽 위 대각선)+1해주기
				}
				else { // 다른 경우
					len[i][j] = Math.max(len[i][j-1], len[i-1][j]); // str2의 전 문자열의 경우의 수 중 현재 확인하는 str1의 경우의 수와 str2의 현 문자열의 경우의 수 중 현재 확인하는 str1의 -1위치의 경우의 수를 비교하여 큰 값 넣어주기
				}
			}
		}
		System.out.println(len[str2.length][str1.length]);
	}

}
