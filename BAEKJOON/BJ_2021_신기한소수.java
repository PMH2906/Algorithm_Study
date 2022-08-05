import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_2021_신기한소수 {
	static boolean check(int num) {
		double K = Math.sqrt(num);
		if(num<2) return false;
		for(int i=2;i< (int)K+1;i++) {
			if(num%i==0) return false;
		}
		return true;
	}
	
	static void makeNum(int len, int sum) {
		if(len==N) {
			output.append(sum/10+"\n");
			return;
		}
		for(int n=1;n<=9;n++) {
			if(check(sum+n)){			
				makeNum(len+1,(sum+n)*10);
			}
		}
	}
	static int[] result;
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(input.readLine());
		result = new int[N];
		makeNum(0,0);
		System.out.println(output);
	}
}
