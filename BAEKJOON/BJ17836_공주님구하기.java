import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ17836_공주님구하기 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer tokens;
    static int N, M, T;
    static int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}}, map;
    static boolean[][] visited, visitedGram;

    public static class Position{
        int x , y, time;
        boolean gram;

        public Position(int x, int y, int time, boolean gram) {
            this.x=x;
            this.y=y;
            this.time=time;
            this.gram=gram;
        }
    }
    public static void main(String[] args) throws IOException {

        tokens = new StringTokenizer(input.readLine());

        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        T = Integer.parseInt(tokens.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        visitedGram = new boolean[N][M];

        for(int r=0;r<N;r++){
            tokens = new StringTokenizer(input.readLine());
            for(int c=0;c<M;c++) {
                map[r][c] = Integer.parseInt(tokens.nextToken());
            }
        }

        System.out.print(bfs());

    }

    public static String bfs() {

        Queue<Position> q = new LinkedList<>();
        q.add(new Position(0,0,0,false));

        while(q.size()>0) {
            Position now = q.poll();
//            System.out.println(now.x + " " + now.y + " " +now.time + " "+ now.gram);
            if(now.x==N-1&&now.y==M-1&&now.time<=T) {
                return String.valueOf(now.time);
            } else if(now.x==N-1&&now.y==M-1&&now.time>T) {
                return "Fail";
            }

            for(int d=0;d<deltas.length;d++){
                int nx = now.x + deltas[d][0];
                int ny = now.y + deltas[d][1];

                if(nx<0||nx>=N||ny<0||ny>=M) continue;

                if(!now.gram && map[nx][ny] == 0 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new Position(nx,ny,now.time+1, now.gram));
                } else if(!now.gram && map[nx][ny] == 2 && !visited[nx][ny]) {
                    visitedGram[nx][ny] = true;
                    q.add(new Position(nx,ny,now.time+1, true));
                } else if(now.gram && !visitedGram[nx][ny]) {
                    visitedGram[nx][ny] = true;
                    q.add(new Position(nx,ny,now.time+1, now.gram));
                }
            }
        }

        return "Fail";
    }
}