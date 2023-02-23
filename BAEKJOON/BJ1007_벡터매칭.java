import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1007_벡터매칭 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder output = new StringBuilder();
	static int T, N;
	static Position[] positions;
	static double minSum;
	
	static class Position{
		int x, y;
		
		Position(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws Exception, IOException {
		T = Integer.parseInt(input.readLine());
		
		
		for(int t=0;t<T;t++) {
			N = Integer.parseInt(input.readLine());
			
			positions = new Position[N];
			minSum = Double.MAX_VALUE;
			
			for(int n=0;n<N;n++) {
				tokens = new StringTokenizer(input.readLine());
				
				positions[n] = new Position(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
				
			}
			
			comb(0, 0, new int[N/2]);
		
			output.append(minSum+"\n");
		}
		System.out.println(output);
	}
	private static void comb(int nth, int start, int[] choosed) {
		if(nth == choosed.length) {
			float x1=0,x2=0,y1=0,y2=0;
			boolean idx[] = new boolean[N];
			for(int i=0;i<choosed.length;i++) {
				idx[choosed[i]]=true;
			}
			
			for(int n=0;n<N;n++) {
				if(idx[n]) {
					x1+=positions[n].x;
					y1+=positions[n].y;
				}else {
					x2+=positions[n].x;
					y2+=positions[n].y;
				}
			}
			
			minSum = Math.min(Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2)), minSum);
			return;
		}
		
		for(int i=start;i<N;i++) {
			choosed[nth]=i;
			comb(nth+1, i+1, choosed);
		}
		
	}
	
}
