import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ2665_미로만들기 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N;
    static char[][] map;
    static int[][] deltas = {{0,-1},{0,1},{-1,0},{1,0}};
    static boolean[][][] visited;
    public static class Info implements Comparable<Info>{
        int x, y, cnt;

        public Info(int x, int y, int cnt){
            this.x=x;
            this.y=y;
            this.cnt=cnt;
        }

        @Override
        public int compareTo(Info o) {
            return Integer.compare(this.cnt,o.cnt);
        }
    }
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(input.readLine());

        map = new char[N][N];
        visited = new boolean[N*N][N][N];

        for(int r=0;r<N;r++){
            map[r]=input.readLine().toCharArray();
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.add(new Info(0,0,0));
        visited[0][0][0]=true;

        while (true){
            Info n = pq.poll();
//            System.out.println(n.x+" "+n.y);
            if(n.x==N-1&&n.y==N-1){
                return n.cnt;
            }
            for(int d=0;d<deltas.length;d++){
                int nx = n.x+deltas[d][0];
                int ny = n.y+deltas[d][1];

                if(nx<0||nx>=N||ny<0||ny>=N) continue;

                if(map[nx][ny]=='0'&&!visited[n.cnt+1][nx][ny]) {
                    pq.add(new Info(nx,ny,n.cnt+1));
                    visited[n.cnt+1][nx][ny]=true;
                }
                else if(map[nx][ny]=='1'&&!visited[n.cnt][nx][ny]){
                    pq.add(new Info(nx,ny,n.cnt));
                    visited[n.cnt][nx][ny]=true;
                }
            }

        }
    }
}
