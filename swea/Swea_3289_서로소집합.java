package s;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**

@author 박미희
@since 2022. 8. 23.
@see
@git
@youtube
@performance
@category #
@note */
public class Swea_3289_서로소집합{
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int N,M;
	static int[] parent;
	public static int find(int curr) {
		if(parent[curr] == curr) {
			return curr;
		}
		return parent[curr] = find(parent[curr]);
	}
	
	public static void union(int a, int b) {
		int root1 = find(a);
		int root2 = find(b);
		
		if(root1!=root2) parent[root1] = root2;
	}
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(input.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			tokens = new StringTokenizer(input.readLine());
			
			 N = Integer.parseInt(tokens.nextToken());
			 M = Integer.parseInt(tokens.nextToken());
			 parent = new int[N];
			 for(int i=0;i<N;i++) parent[i]=i;
			 
			 output.append("#"+test_case+" ");
			 for(int m=0;m<M;m++) {
				 tokens = new StringTokenizer(input.readLine());
				 int cal = Integer.parseInt(tokens.nextToken());
				 int a = Integer.parseInt(tokens.nextToken())-1;
				 int b = Integer.parseInt(tokens.nextToken())-1;
				 
				 if(cal==0) union(a,b);
				 if(cal==1) {
					 if(find(a)==find(b)) output.append("1");
					 else output.append("0");
				 }
				
			 }
			 output.append("\n");
		}
		System.out.println(output);
	}
	
}