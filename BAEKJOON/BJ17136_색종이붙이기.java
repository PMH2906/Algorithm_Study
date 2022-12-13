import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ17136_색종이붙이기 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int[][] paper;
	static int[] confettiCnt = { 0, 5, 5, 5, 5, 5 };
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		paper = new int[10][10];

		for (int r = 0; r < 10; r++) {
			tokens = new StringTokenizer(input.readLine());
			for (int c = 0; c < 10; c++) {
				paper[r][c] = Integer.parseInt(tokens.nextToken());
			}
		}

		dfs(0, 0, 0);
		if (ans == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		} else {
			System.out.println(ans);
		}
	}

	// dfs
	private static void dfs(int r, int c, int cnt) {

		// 마지막 행, 열오면 갯수 비교하기
		if (r >= 9 && c > 9) {
			ans = Math.min(cnt, ans);
			return;
		}

		// 계산된 색종이 갯수보다 크면 다음은 확인할 필요없으므로 return
		if (ans <= cnt) {
			return;
		}
		// 마지막 열까지 오면 다음 행으로 이동
		if (c > 9) {
			dfs(r + 1, 0, cnt);
			return;
		}

		// 종이에서 1인 곳 확인
		if (paper[r][c] == 1) {
			for (int len = 5; len > 0; len--) {
				// 해당 크기의 색종이의 갯수가 남아있으며 붙일 수 있는 공간이 있다면
				if (check(r, c, len) && confettiCnt[len] > 0) {
					attach(r, c, len); // 색종이 붙이기
					confettiCnt[len]--; // 해당 크기 색종이 사용
					dfs(r, c + 1, cnt + 1); // dfs
					detach(r, c, len); // 색종이 떼
					confettiCnt[len]++; // 해당 크기 색종이 갯수 원상복구
				}
			}
		} else {
			dfs(r, c + 1, cnt); // 종이의 위치가 1이 아니라면 다음 열로 이동해서 dfs탐
		}

	}

	// 색종이 떼기
	private static void detach(int r, int c, int len) {
		for (int row = 0; row < len; row++) {
			for (int col = 0; col < len; col++) {
				paper[r + row][c + col] = 1;
			}
		}

	}

	// 색종이 붙이기
	private static void attach(int r, int c, int len) {
		for (int row = 0; row < len; row++) {
			for (int col = 0; col < len; col++) {
				paper[r + row][c + col] = 0;
			}
		}
	}

	// 색종이를 붙일 수 있는가 여부
	private static boolean check(int r, int c, int len) {
		if (r + len - 1 < 10 && c + len - 1 < 10) {
			for (int row = 0; row < len; row++) {
				for (int col = 0; col < len; col++) {
					if (paper[r + row][c + col] != 1)
						return false;
				}
			}
		} else {
			return false;
		}
		return true;
	}

}
