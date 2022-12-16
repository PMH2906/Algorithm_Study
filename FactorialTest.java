package com.ssafy.online1;

public class FactorialTest {
	static int factorial3(int n) { // n! 계산
		
		if(n<=1) return 1;//재귀 호출 끊기
		
		return n * factorial3(n-1);
	}
	public static void main(String[] args) {
		
	}
}
