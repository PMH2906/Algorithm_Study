import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_6808_규영이와인영이의카드게임 {
	static StringBuilder output = new StringBuilder();
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int [] competitor;
	static int [] me;
	static int competitorScore;
	static int meScore;
	static int win;
	static int lose;
	static int[] choosed;
	static boolean[] visited;
	
	static void choiceCard(int nth, int[] choosed, boolean[] visited, int competitorScore, int meScore) {
		if(nth==choosed.length) {
			if(competitorScore>meScore) win+=1;
			else lose+=1;
			return;
		}
		for(int i=0;i<me.length;i++) {
			if(!visited[i]) {
				visited[i]=true;
				choosed[nth]=me[i];
				if(competitor[nth]>choosed[nth]) choiceCard(nth+1, choosed, visited, competitorScore+competitor[nth]+choosed[nth], meScore);
				else if(competitor[nth]<choosed[nth]) choiceCard(nth+1, choosed, visited, competitorScore, meScore+competitor[nth]+choosed[nth]);
				visited[i]=false;
			}
		}
	}

	public static void main(String args[]) throws Exception
	{
		int T = Integer.parseInt(input.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			competitor = new int[9];
			me = new int[9];
			win =0;
			lose =0;
			choosed = new int[9];
			visited = new boolean[9];
			
			tokens=new StringTokenizer(input.readLine());
			for(int i=0;i<9;i++) competitor[i]=Integer.parseInt(tokens.nextToken());
			
			int idx=0;
			out:for(int i=1;i<=18;i++) {
				for(int j=0;j<9;j++) {
					if(i==competitor[j]) continue out;
				}
				me[idx++]=i;
			}
			choiceCard(0, choosed, visited,0,0);
			output.append(String.format("#%d %d %d\n", test_case,win,lose));
		}
		System.out.println(output);
	}

}
