package com.ssafy.online2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 좋은아침_연습 {
	static char[] src = { 'a', 'b', 'c', 'd' };

	public static void main(String[] args) {
		// 1. src에서 3개를 뽑아서 만들 수 있는 순열을 모두 출력하시오.
		System.out.println("순열");
		makePermutation(0, new char[3], new boolean[src.length]);

		System.out.println("중복 순열");
		makePermutationDup(0, new char[3]);
		// 2. src에서 3개를 뽑아서 만들 수 있는 조합을 모두 출력하시오.
		System.out.println("조합");
		makeCombination(0, new char[3], 0);

		// 3. src로 구성할 수 있는 모든 부분집합을 출력하시오.
		System.out.println("부분집합");
		powerSetDupPer(0, new boolean[4]);

	}

	/**
	 * 기본 순열 - 모든 요소가 중복되지 않고 순서를 고려해서 나온다
	 * 
	 * @param nth     - 순열 몇 개 뽑을 지
	 * @param choosed - 뽑은 결과
	 * @param visited - 어떤 요소를 사용한 적이 있는가
	 * 
	 *                요소를 돌면서 choosed(결과)에 선택 안 되었으면 넣어주고 다음 요소 뽑을 수 있도록 재귀함수 부름 그 후
	 *                choosed가 모두 뽑혔으면 재귀함수 탈출하면서 choosed에 뽑힌 요소가 아님을 확인해주기 위해
	 *                visited[i]=false로 설정해주기
	 */
	static void makePermutation(int nth, char[] choosed, boolean[] visited) {
		// 기저조건 : 몇 번째꺼를 고르는데 choosed를 다 채웠다면
		if (nth == choosed.length) {
			System.out.println(Arrays.toString(choosed));
			return;
		}
		// inductive part
		for (int i = 0; i < src.length; i++) {
			if (!visited[i]) {
				// 해당 하는 원소 사용
				visited[i] = true; // 순열이면 한 번 뽑으면 반복안되므로
				choosed[nth] = src[i];
				makePermutation(nth + 1, choosed, visited);
				// 해당하는 원소 사용 안 한척!!!
				visited[i] = false;
			}
		}
	}

	/**
	 * 중복 순열
	 * 
	 * @param nth
	 * @param choosed
	 * @param visited
	 */
	static void makePermutationDup(int nth, char[] choosed) {
		// 기저조건 : 몇 번째꺼를 고르는데 choosed를 다 채웠다면
		if (nth == choosed.length) {
			System.out.println(Arrays.toString(choosed));
			return;
		}
		// inductive part
		for (int i = 0; i < src.length; i++) {

			// 중복 순열은 반복해서 뽑아도 되므로 visited 배열 안 씀
			choosed[nth] = src[i];
			makePermutationDup(nth + 1, choosed);
		}
	}

	/**
	 * 조합 : 중복되지 않게 뽑는데 순서가 없다
	 * 
	 * @param nth
	 * @param choosed
	 * @param startIdx
	 */
	static void makeCombination(int nth, char[] choosed, int startIdx) {
		if (nth == choosed.length) {
			System.out.println(Arrays.toString(choosed));
			return;
		}
		for (int i = startIdx; i < src.length; i++) {
			choosed[nth] = src[i];
			makeCombination(nth + 1, choosed, i + 1); // 사용한 요소 다음 것 부터 고려. 중복 안되므로
		}
	}

	/**
	 * 중복 조합
	 * 
	 * @param nth
	 * @param choosed
	 * @param startIdx
	 */
	static void makeCombinationDup(int nth, char[] choosed, int startIdx) {
		if (nth == choosed.length) {
			System.out.println(Arrays.toString(choosed));
			return;
		}
		for (int i = startIdx; i < src.length; i++) {
			choosed[nth] = src[i];
			makeCombination(nth + 1, choosed, i); // 현재 뽑은 요소도 같이 고려해준다. 중복 가능
		}
	}

	/**
	 * 
	 * @param toCheck - 어디까지 포함 여부를 체크했는지 확인
	 * @param checked - 어떤 상태로 체크되어 있는지
	 */
	static void powerSetDupPer(int toCheck, boolean[] checked) {
		if(toCheck==checked.length) {
			print(checked);
			return;
		}
		// toCheck에 해당하는 요소를 선택할꺼냐? 말거냐?
		checked[toCheck] = true;
		powerSetDupPer(toCheck+1, checked);
		checked[toCheck] = false;
		powerSetDupPer(toCheck+1, checked);
	}

	static void print(boolean[] temp) {
//		System.out.println(Arrays.toString(temp));
		List<Character> selected = new ArrayList<>();
		List<Character> unselected = new ArrayList<>();
		
		for(int i=0; i<temp.length;i++) {
			if(temp[i]) {
				selected.add(src[i]);
			}else {
				unselected.add(src[i]);
			}
		}
		System.out.println(selected+ " : "+unselected);
	}

}
