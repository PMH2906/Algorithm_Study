import java.util.*;
import java.io.*;

public class BJ15686_치킨배달_second {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int N, M, map[][], ans=Integer.MAX_VALUE;
    static List<Position> house = new ArrayList<>();
    static List<Position> chicken = new ArrayList<>();

    public static class Position{
        int x, y;

        public Position(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String args[]) throws Exception
    {
        
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        map = new int[N][N];

        for(int r=0;r<N;r++){
            tokens = new StringTokenizer(input.readLine());
            for(int c=0;c<N;c++){
                map[r][c]= Integer.parseInt(tokens.nextToken());
                if(map[r][c]==1) house.add(new Position(r,c));
                else if(map[r][c]==2) chicken.add(new Position(r,c));
            }
        }

        combination(0,new int[M], 0);
        
        System.out.println(ans);
    }
    private static void combination(int nth, int[] choosed, int startIdx){
        if(nth==choosed.length){
            int dist = cal(choosed);
            ans = Math.min(ans,dist);
            return;
        }

        for(int i=startIdx;i<chicken.size();i++){
            choosed[nth]=i;
            combination(nth+1, choosed, i+1);
        }
    }
    private static int cal(int[] choosed){
        int allDist=0;
        for(int i=0;i<house.size();i++){
            int minDist = Integer.MAX_VALUE;
            for(int j=0;j<choosed.length;j++){
                int dist = Math.abs(house.get(i).x-chicken.get(choosed[j]).x) + Math.abs(house.get(i).y-chicken.get(choosed[j]).y);
                minDist = Math.min(minDist,dist);
            }
            allDist+=minDist;
        }
        return allDist;
    }

}
