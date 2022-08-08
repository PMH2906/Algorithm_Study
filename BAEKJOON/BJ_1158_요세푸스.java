package Algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1158_요세푸스 {
	static StringBuilder output = new StringBuilder();
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N;
	static int K;
	static Queue<Integer> q = new LinkedList<Integer>();
	public static void main(String[] args) throws Exception {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		
		for(int i=1;i<=N;i++) q.add(i);
		
		output.append("<");
		while(q.size()>1) {
			for(int i=1;i<=K;i++) {
				if(i==K) output.append(q.poll()+", ");
				else q.add(q.poll());
			}
		}
		output.append(q.poll()+">");
		System.out.println(output);
	}
}
