import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 암호생성기 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static Queue<Integer> q = new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		String test_case;
		while((test_case=input.readLine())!=null&&test_case.length()>0)
		{
			tokens = new StringTokenizer(input.readLine());
			for(int i=0;i<8;i++) {
				q.add(Integer.parseInt(tokens.nextToken()));
			}
			
			out:while(true) {
				for(Integer i=1;i<=5;i++) {
					Integer num = q.poll()-i;
					if(num<=0) {
						q.add(0);
						break out;
					}
					q.add(num);
				}
			}
			output.append(String.format("#%s ", test_case));
			for(int i=0;i<8;i++) {
				output.append(String.format("%d ", q.poll()));
			}
			output.append("\n");
		}
		System.out.println(output);
		
	}
}
