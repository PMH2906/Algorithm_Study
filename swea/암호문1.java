import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class 암호문1 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static List<String> list = new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		for(int test_case = 1; test_case <= 10; test_case++)
		{
			list = new LinkedList<>();
			int N = Integer.parseInt(input.readLine());
			
			tokens = new StringTokenizer(input.readLine());
			for(int i=0;i<N;i++) {
				list.add(tokens.nextToken());
			}
			
			int C = Integer.parseInt(input.readLine());
			
			tokens = new StringTokenizer(input.readLine());
			for(int i=0;i<C;i++) {
				tokens.nextToken();
				
				int startIdx = Integer.parseInt(tokens.nextToken());
				int addCnt = Integer.parseInt(tokens.nextToken());
				
				for(int cnt=0;cnt<addCnt;cnt++) {
					list.add(startIdx++,tokens.nextToken());
				}
			}
			output.append(String.format("#%d ", test_case));
			for(int i=0;i<10;i++) {
				output.append(list.get(i)+" ");
			}
			output.append("\n");
		}
		System.out.println(output);
	}
}
