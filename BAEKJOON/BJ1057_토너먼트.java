import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1057_토너먼트 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int N, kim, im, round=0;
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		kim =  Integer.parseInt(tokens.nextToken());
		im =  Integer.parseInt(tokens.nextToken());
		
		while(N!=1) {
			round++;
			N = (int) Math.ceil((double)N/(double)2);
			
			kim=(int) Math.ceil((double)kim/(double)2);
			im=(int) Math.ceil((double)im/(double)2);
			
			if(kim==im) {
				System.out.println(round);
				return;
			}
		}
		System.out.println(-1);
	}

}
