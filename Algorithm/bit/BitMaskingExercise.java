package s;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class BitMaskingExercise {
	public static void main(String[] args) {
		setup();
		sumFirstVisitByArray();
		sumFirstVisitByBit();

		nums = Arrays.copyOf(nums, 4);
		cnt = 0;
		System.out.println("permutationByArray");
		permutationByArray(0, new int[3], new boolean[nums.length]);

		cnt = 0;
		System.out.println("permutationByBit");
		permutationByBit(0, new int[3], 0);

		System.out.println("makePowerSet 배열");
		makePowerSet(0, new boolean[nums.length]);
		
		System.out.println("makePowerSet 비트");
		makePowerSet();
	}

	private static int[] nums;

	// 0~31까지의 정수 100개를 무작위로 만들어본다.
	private static void setup() {
		nums = new int[100];
		Random rand = new Random();
		for (int i = 0; i < nums.length; i++) {
			nums[i] = rand.nextInt(32);
		}
		System.out.println("배열: " + Arrays.toString(nums));
	}

	private static void sumFirstVisitByArray() {
		// TODO:nums에서 처음 나온 배열 요소들만 더해서 그 값을 출력하시오.
		boolean[] status = new boolean[32];
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			if (!status[nums[i]]) {
				status[nums[i]] = true;
				sum += nums[i];
			}
		}
		System.out.println(sum);
		// END:
	}

	private static void sumFirstVisitByBit() {
		// TODO: 위 메서드를 bit 연산자로 처리하시오.
		int status = 0;
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			if ((status & 1 << nums[i]) == 0) {
				status |= 1 << nums[i];
				sum += nums[i];
			}
		}
		System.out.println(sum);
		// END:
	}

	static int cnt = 0;

	// TODO: 배열 기반으로 순열을 작성해보고 visited를 왜 원상복구시키고 있는지 고민해보자.
	private static void permutationByArray(int nth, int[] choosed, boolean[] visited) {
		if (choosed.length == nth) {
			System.out.println(Arrays.toString(choosed));
			return;
		}
		for (int i = 0; i < nums.length; i++) {
			if (!visited[i]) {
				choosed[nth] = nums[i];
				visited[i] = true;
				permutationByArray(nth + 1, choosed, visited);
				visited[i] = false;
			}
		}
	}
	// END:

	// TODO: bit 연산을 이용해서 순열을 작성해보자.
	private static void permutationByBit(int nth, int[] choosed, int visited) {
		if (choosed.length == nth) {
			System.out.println(Arrays.toString(choosed));
			return;
		}
		for (int i = 0; i < nums.length; i++) {
			if ((visited & 1 << i) == 0) {
				choosed[nth] = nums[i];
				permutationByBit(nth + 1, choosed, visited |= 1 << i);
//    			visited&=~(1<<i); //생략!!!!!!!!!!! int 형이니까 값 자체가 전달되므로 false를 안해줘도 해당 재귀에서는 다른 재귀함수에서 바뀌는 int 숫자가 바뀌지 않음!!!!!!!!!
				// 하지만 객체형태면 주소를 넘겨주니까 다른 재귀에서 바뀌면 다른 재귀함수의 visited에도 영향을 줌
			}
		}
	}

	// END:

	// TODO: 중복순열을 이용해서 nums의 부분집합을 만들어보자.
	private static void makePowerSet(int nth, boolean[] status) {
		if (nth == status.length) {
			List<Integer> list = new LinkedList<Integer>();
    		for(int i=0;i<status.length;i++) {
    			if(status[i]) list.add(nums[i]);
    		}
    		System.out.println(list);

			System.out.println(Arrays.toString(status));
			return;

		}

		status[nth] = true;
		makePowerSet(nth + 1, status);
		status[nth] = false;
		makePowerSet(nth + 1, status);

	}
	// END:

	// TODO: bitmasking을 이용해서 부분집합을 구해보자.
	private static void makePowerSet() {
		for(int i=0;i<(1<<nums.length);i++) {
			List<Integer> include = new ArrayList<>();
			List<Integer> exclude = new ArrayList<>();
//			System.out.println(i ); // 인덱스자체가 요소들에 대한 포함 여부를 비트로 가지고 있다
			for(int j=0;j<nums.length;j++) {
				if((i & 1<<j)>0){ // 포함하고있니?
					include.add(j);
				}else {
					exclude.add(j);
				}
			}
			System.out.printf("include:%s, exclude:%s\n",include,exclude);
		}
	}
	// END:

}
