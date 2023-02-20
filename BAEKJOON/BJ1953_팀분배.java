import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1953_팀분배 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder output1=new StringBuilder(),output2= new StringBuilder();
	static int N, visited[], map[][]; 

	
	public static void main(String[] args) throws Exception, IOException {
		N = Integer.parseInt(input.readLine());

		visited = new int[N+1];
		map = new int[N+1][N+1];
		
		for(int n=1;n<=N;n++) {
			
			tokens = new StringTokenizer(input.readLine());
			
			int nth = Integer.parseInt(tokens.nextToken());
			
			for(int i=0;i<nth;i++) {
				int num =  Integer.parseInt(tokens.nextToken());
				map[n][num] = 1; // 싫은 사람 
				map[num][n] = 1;
			}
		}
		
		bfs();
		
		int size1 = 0;
		int size2 = 0;
		for(int n=1;n<=N;n++) {
			if(visited[n]==1) {
				size1++;
				output1.append(n+" ");
			}
			else {
				size2++;
				output2.append(n+" ");
			}
		}
	
		System.out.println(size1+"\n"+output1+"\n"+size2+"\n"+output2);

	}

	private static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		
		for(int n=1;n<=N;n++) {
		
			if(visited[n]!=0) continue;
			
			visited[n]=1;
			q.add(n);
			
			while(q.size()>0) {
				int now = q.poll();
				for(int i=1;i<=N;i++) {
					if(visited[i]!=0) continue;
					
					if(map[now][i]==1||map[i][now]==1) {
						visited[i] = visited[now]*(-1);
						q.add(i);
					}
				}
			}
			
		}
		
	}

}
