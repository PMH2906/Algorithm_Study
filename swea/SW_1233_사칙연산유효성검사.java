import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_1233_사칙연산유효성검사 {
	static StringBuilder output = new StringBuilder();
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	public static void main(String[] args) throws Exception{
		for(int t=0;t<10;t++){
			int N = Integer.parseInt(input.readLine());
			
			int result=1;
			if(N%2==0) result=0;
			for(int i=0;i<N;i++) {
				tokens=new StringTokenizer(input.readLine());
				String Node;
				if(tokens.countTokens()==4) {
					tokens.nextToken();
					if(!(Node=tokens.nextToken()).equals("+")&&!Node.equals("*")&&!Node.equals("/")&&!Node.equals("-")) {
						result=0;
					}
				}
				if(tokens.countTokens()==2) {
					tokens.nextToken();
					if((Node=tokens.nextToken()).equals("+")||Node.equals("*")||Node.equals("/")||Node.equals("-")) {
						result=0;
					}
				}
			}
			output.append(String.format("#%d %d\n", t+1,result));
			
		}
		System.out.println(output);
			
	}

}
