package Algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;



public class SWEA_1247_최적경로 {
	static class Position {
        int x,y;
 
        public Position(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }
    }
     
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer tokens;
    static Position firm, home;
    static Position[] customers;
    static int N,result;
    static boolean[] visited;
    static int[] choosed;
     
    public static void order(int nth, int[] choosed, boolean[] visited){
        if(choosed.length==nth) {
            int dist=0;
            dist+=Math.abs(firm.x-customers[choosed[0]].x)+Math.abs(firm.y-customers[choosed[0]].y);
            for(int i=0;i<choosed.length-1;i++) {
                dist+=Math.abs(customers[choosed[i]].x-customers[choosed[i+1]].x)+Math.abs(customers[choosed[i]].y-customers[choosed[i+1]].y);
            }
            dist+=Math.abs(home.x-customers[choosed[choosed.length-1]].x)+Math.abs(home.y-customers[choosed[choosed.length-1]].y);
            result = Math.min(result, dist);
            return;
        }
         
        for(int i=0;i<N;i++) {
            if(!visited[i]) {
                choosed[nth]=i;
                visited[i]=true;
                order(nth+1, choosed, visited);
                visited[i]=false;
            }
        }
    }
 
    public static void main(String[] args) throws Exception {
 
        int T = Integer.parseInt(input.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            result=Integer.MAX_VALUE;
            N = Integer.parseInt(input.readLine());
            customers = new Position[N];
            visited = new boolean[N];
            choosed=new int[N];
             
            tokens=new StringTokenizer(input.readLine());
            firm=new Position(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
            home=new Position(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
            for(int n=0; n<N; n++) {
                customers[n]=new Position(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
            }
             
            order(0,choosed,visited);
            output.append(String.format("#%d %d\n", test_case,result));
        }
        System.out.println(output);
    }
}
