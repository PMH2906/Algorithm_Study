import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**

@author 박미희
@since 2022. 8. 12.
@see
@git
@youtube
@performance
@category #
@note */
public class BJ_15686_치킨배달{
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static List<int []> chicken = new ArrayList<int[]>();
	static List<int []> home = new ArrayList<int[]>();
	static int[][] map;
	static int N,M,distance,min_distance,sum,minSum=Integer.MAX_VALUE;
	
	public static void openSelect(int nth, int[] choosed, int startIdx) { // 조합 이용
		if(nth==choosed.length) {
			dist(choosed); // 조합 경우의 수마다 도시의 치킨 거리 구하기
			return;
		}
		for(int i=startIdx;i<chicken.size();i++) {
			choosed[nth]=i;
			openSelect(nth + 1, choosed, i + 1);
		}
	}
	public static void dist(int[] choosed) {
		sum=0;
		for(int h=0;h<home.size();h++) {
			min_distance=Integer.MAX_VALUE;
			for(int c : choosed) {
				distance=Math.abs(home.get(h)[0]-chicken.get(c)[0])+Math.abs(home.get(h)[1]-chicken.get(c)[1]); // 치킨집과 집의 거리
				min_distance=Math.min(distance, min_distance); // 치킨집과 집의 거리 중 가장 짧은 거리
			}
			sum+=min_distance;
		}
		minSum = Math.min(sum, minSum); // 도시의 치킨 거리 중 가장 작은 값
	}
	public static void main(String[] args) throws IOException {
		
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		map = new int[N][N];
	
		for(int r=0;r<N;r++) {
			tokens = new StringTokenizer(input.readLine());
			for(int c=0;c<N;c++) {
				map[r][c] = Integer.parseInt(tokens.nextToken());
				if (map[r][c]==1) home.add(new int[] {r,c});
				else if(map[r][c]==2) chicken.add(new int[] {r,c});
			}
		}
	
		openSelect(0, new int[M], 0);
		System.out.println(minSum);
		
	}
}

