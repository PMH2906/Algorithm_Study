import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author mihee
 * @see https://www.acmicpc.net/problem/1244
 * @performance 
 * @category #단순구현
 * @memo 메모
**/

public class Main {
	static int cnt;
	static int[] status;
	static int[][] info;

	public static void main(String[] args) throws Exception {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens;

		int S = Integer.parseInt(input.readLine());
		status = new int[S];

		tokens = new StringTokenizer(input.readLine());
		for (int i = 0; i < S; i++) {
			status[i] = Integer.parseInt(tokens.nextToken());
		}

		int N = Integer.parseInt(input.readLine());

		info = new int[N][2];
		for (int r = 0; r < N; r++) {
			tokens = new StringTokenizer(input.readLine());
			for (int c = 0; c < 2; c++) {
				info[r][c] = Integer.parseInt(tokens.nextToken());
			}
		}

		for (int r = 0; r < N; r++) {
			if (info[r][0] == 1) { // 남
				for (int i = info[r][1] - 1; i < status.length; i++) {
					if ((i + 1) % info[r][1] == 0) {
						if (status[i] == 1)
							status[i] = 0;
						else
							status[i] = 1;
					}
				}
			}
			else { // 여

				if (status[info[r][1]-1] == 1)
					status[info[r][1]-1] = 0;
				else
					status[info[r][1]-1] = 1;
				
				for (int i = 1; i < status.length / 2; i++) {
					if (((info[r][1] - 1) - i) < 0 || ((info[r][1] - 1) + i) >= S)
						break;
					if(status[(info[r][1] - 1) - i]== status[(info[r][1] - 1) + i]) {
						if (status[(info[r][1] - 1) - i] == 1)
							status[(info[r][1] - 1) - i] = 0;
						else
							status[(info[r][1] - 1) - i] = 1;
	
						if (status[(info[r][1] - 1) + i] == 1)
							status[(info[r][1] - 1) + i] = 0;
						else
							status[(info[r][1] - 1) + i] = 1;
					}else break;
				}

			}
			

		}
		for(int i=0; i<status.length; i++) {
			System.out.print(status[i] + " ");
			if((i+1) % 20 == 0)
				System.out.println();
		}
	}
}
