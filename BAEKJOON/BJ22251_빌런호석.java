import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ22251_빌런호석 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, K, P, X, LEDCnt[][],ans=0;
    static int LED[][] = {{1,1,1,1,1,1,0},{0,1,1,0,0,0,0},{1,1,0,1,1,0,1},{1,1,1,1,0,0,1},{0,1,1,0,0,1,1},{1,0,1,1,0,1,1},{1,0,1,1,1,1,1},{1,1,1,0,0,0,0},{1,1,1,1,1,1,1},{1,1,1,1,0,1,1}};
    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(input.readLine());

        N = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());
        P = Integer.parseInt(tokens.nextToken());
        X = Integer.parseInt(tokens.nextToken());

        LEDCnt = new int[10][K]; // 행:숫자 열:자릿수

        for (int k = 0; k < K; k++) { // 맨 앞 숫자부터 탐색(자릿수)
            int now = X / (int) Math.pow(10, K - k - 1); // 현재 자릿수 숫자를 기준으로
            X %= (int) Math.pow(10, K - k - 1); // x 갱신
            for (int i = 0; i < 10; i++) { // 0~9까지 숫자 탐색
                int cnt = 0;
                for (int j = 0; j < 7; j++) { // 8개의 LED 다스플레이 바 탐색
                    if (LED[i][j] != LED[now][j]) cnt++; // 반전되어야할 LED 디스플레이 바 갯수
                }
                LEDCnt[i][k] = cnt;
            }
        }

        dupPermutation(0,new int[K]);
        
        System.out.println(ans);
    }

    // 자릿수에 올 수 있는 수 중복 조합(0-9)
    private static void dupPermutation(int nth, int[] choosed) {
        if(nth==choosed.length){
            if(possible(choosed)) ans++; // 모든 조건 맞으면 증가 
            return;
        }
        for(int i=0;i<10;i++){
            choosed[nth]=i;
            dupPermutation(nth+1,choosed);
        }
    }

	private static boolean possible(int[] choosed) {
		int num=0; // 순열로 뽑은 수를 정수로 변환 => 0,0,3 => 3으로 계산 
		int cnt=0; // 현재 층수에서 순열로 뽑은 수로 반전시킬 LED의 총 갯수 
		for(int i=0;i<choosed.length;i++) {
			num += choosed[i]*(int) Math.pow(10, K - i - 1); 
		}
		// 변환한 정수가 1-N 사이 값이라면 
		if(num<=N&&num>=1) {
			// 현재 층수에서 순열로 뽑은 수로 반전시킬 LED의 총 갯수 
			for(int i=0;i<choosed.length;i++) {
				cnt+=LEDCnt[choosed[i]][i];
			}
			// 반전 시킬 수가 1-P라면 모든 조건 성립 
			if(cnt<=P&&cnt>=1) {
				return true;
			}
		}
		return false;
	}
}