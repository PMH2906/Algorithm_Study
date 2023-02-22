import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1963_소수경로 {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder output = new StringBuilder();
	static int T, before, after, cnt[];
	static boolean divisor[] = new boolean[10000], visited[];

	public static void main(String[] args) throws Exception, IOException {
		T = Integer.parseInt(input.readLine());
		findDivisor();
		
		for(int t=0;t<T;t++) {
			tokens = new StringTokenizer(input.readLine());
			before = Integer.parseInt(tokens.nextToken());
			after = Integer.parseInt(tokens.nextToken());
			
			visited = new boolean[10000];
			cnt= new int[10000];
			bfs();
			
		}
		System.out.println(output);

	}
	
	private static void bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(before);
		visited[before] = true;
		
		while(q.size()>0) {
			int now = q.poll();
			
			
			for(int i=0;i<4;i++) {
				for(int j=0;j<10;j++) {
					if(i==0&&j==0) continue; // 0번째  고려
					
					StringBuilder strNum = new StringBuilder(String.valueOf(now));
					strNum.setCharAt(i, (char)(j+'0'));
					
					int changed = Integer.parseInt(strNum.toString());
					
					if(divisor[changed]&&!visited[changed]) { //소수이면서 방문하지 않았다면 
						q.add(changed);
						visited[changed] = true;
						cnt[changed] = cnt[now]+1;
					}
					
				}
			}
		}
		
		output.append(cnt[after]+"\n");
		
	}

	public static void findDivisor() {
		Arrays.fill(divisor, true);
		
		for(int i=2; i<=Math.sqrt(9999);i++) {
			if(divisor[i]==true) {
				int j = 2; // 배수 구하기
				while(i*j<=9999) {
					divisor[i*j] = false;
					j+=1;
				}
			}
		}
		
		
	}

}
