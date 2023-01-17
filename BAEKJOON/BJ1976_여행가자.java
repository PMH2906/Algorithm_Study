import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// union find 사용
// 첫 여행지와 부모가 같으면 연결된 걸로 간주! => 여행 가능 
// 부모가 같지 않으면 => 여행 불가능 
public class BJ1976_여행가자 {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int N ,M, parents[];
	public static void main(String[] args) throws Exception, IOException {
		N = Integer.parseInt(input.readLine());
		M = Integer.parseInt(input.readLine());
		
		parents = new int[N+1];
		for(int i=1;i<=N;i++) {
			parents[i] = i; // 자기자신을 부모로 초기화 
		}
		
		for(int i=1;i<=N;i++) {
			tokens = new StringTokenizer(input.readLine());
			for(int j=1;j<=N;j++) {
				int num = Integer.parseInt(tokens.nextToken());
				if(num==1) {
					union(i,j); // 연결된 여행지 합집합 연산 
				}
			}
		}
		
		tokens = new StringTokenizer(input.readLine()); 
		int firstParents = parents[Integer.parseInt(tokens.nextToken())]; // 첫 여행지의 부모 
		for(int m=1;m<M;m++) {
			int next = Integer.parseInt(tokens.nextToken());
			// 첫 여행지의 부모와 다르면, 연결되어 있지 않은 도시이므로 여행 불가! 
			if(firstParents!=parents[next]) {
				System.out.print("NO");
				return;
			}
		}
		System.out.println("YES");
		
	}
	private static void union(int i, int j) {
		int a = find(i);
		int b = find(j);
		
		if(a!=b) {
			if(a>b) parents[a]=b;
			else parents[b]=a;
		}
	}
	private static int find(int i) {
		
		if(parents[i]==i) return i;
		
		return parents[i] = find(parents[i]);
	}

}
