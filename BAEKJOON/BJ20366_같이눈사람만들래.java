import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class BJ20366_같이눈사람만들래 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int N, num[], min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception, IOException {
		N = Integer.parseInt(input.readLine());
		num = new int[N];
		tokens = new StringTokenizer(input.readLine());

		for (int i = 0; i < N; i++)
			num[i] = Integer.parseInt(tokens.nextToken());

		// 정렬 
		Arrays.sort(num);

		// 조합으로 n개 중 4개를 고르는 것은 최악의 경우 N=600이므로 시간이 너무 큼 6C4
		// 따라서 먼저 하나의 눈사람을 만든 뒤 
		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) { 
				int height = num[i] + num[j];
				
				// 만든 눈사람의 길이를 기준으로 투포인터 진행 
				int first = 0, second = N - 1;
				while (first<second) {
					
					// 이미 사용한 눈사람은 사용 불가능 
					if (first == i ||first == j) { 
						first++;
						continue;
					} else if (second == j||second == i) {
						second--;
						continue;
					}
					// 차이가 가장 작은 값 저장 
					min = Math.min(min, Math.abs(height-(num[first] + num[second])));
					
					// 두번째 눈사람이 첫번째 눈사람보다 크다면, 정렬되어있으므로 second를 이용해 작은 높이로 이동 
					if (height < num[first] + num[second])
						second--;
					
					// 반대면 first를 이용해 큰 높이로 이동 
					else if (height > num[first] + num[second])
						first++;
					// 같다면 차이는 0이므로 0 반환 
					else {
						System.out.println(0);
						return;
					}
				}
			}
		}
		System.out.println(min);

	}

}
