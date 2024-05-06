import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2146_다리만들기 {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, answer=Integer.MAX_VALUE;
    static int[][] map, deltas={{1,0},{-1,0},{0,1},{0,-1}};
    static boolean[][] visited;
    static List<PriorityQueue<Info>> sea;

    public static class Info implements Comparable<Info> {
        int x, y, nth, cnt;

        public Info(int x, int y, int nth, int cnt) {
            this.x=x;
            this.y=y;
            this.nth=nth;
            this.cnt=cnt;
        }

        @Override
        public int compareTo(Info o) {
            return Integer.compare(this.cnt, o.cnt);
        }
    }

    public static void main(String[] args) throws IOException {

        N=Integer.parseInt(input.readLine());
        map=new int[N][N];
        visited=new boolean[N][N];
        sea= new LinkedList<>();

        for(int r=0;r<N;r++) {
            tokens=new StringTokenizer(input.readLine());
            for(int c=0;c<N;c++) {
                map[r][c]=Integer.parseInt(tokens.nextToken());
            }
        }

        find();
        build();

//        for(int r=0;r<N;r++) {
//            System.out.println(Arrays.toString(map[r]));
//        }

        System.out.println(answer);
    }

    /**
     * 가장 짧은 다리 찾기
     * **/
    private static void build() {

        loop : for(PriorityQueue<Info> pq : sea) {
            visited=new boolean[N][N];

            while (!pq.isEmpty()) {
                Info now=pq.poll();
                // System.out.println(now.x+" "+now.y+" "+now.nth+" "+now.cnt);
                visited[now.x][now.y]=true; // 미리 들어간 칸은 visited 처리해야 함

                for(int d=0;d<deltas.length;d++) {
                    int nr=now.x+deltas[d][0];
                    int nc=now.y+deltas[d][1];

                    if(nr<0||nr>=N||nc<0||nc>=N) continue;

                    if(map[nr][nc]!=now.nth&&map[nr][nc]!=0) { // 다음 위치가 바다가 아니면서 다른 섬일 경우
                        answer=Math.min(answer, now.cnt);
                        continue loop;
                    } else if (map[nr][nc]==0&&!visited[nr][nc]) { // 다음 칸이 바다면서 visited 안 된 경우 추가
                        visited[nr][nc]=true;
                        pq.add(new Info(nr,nc, now.nth, now.cnt+1));
                    }
                }

            }
        }
    }

    /**
     * 섬을 nth번째로 채우기
     * 섬을 찾다가 바다칸을 찾으면 sea에 ADD
     * **/
    public static void find() {
        int nth=1;

        for(int r=0;r<N;r++) {
            for(int c=0;c<N;c++) {
                if(map[r][c]==1&&!visited[r][c]) {
                    sea.add(new PriorityQueue<>());
                    bfs(r,c, nth);
                    nth+=1;
                }
            }
        }
    }

    /**
     * 섬을 nth번째로 채우기
     * 섬을 찾다가 바다칸을 찾으면 sea에 ADD
     * **/
    public static void bfs(int r, int c, int nth) {
        Queue<Info> q=new LinkedList<>();
        q.add(new Info(r,c,0,0));
        visited[r][c]=true;
        map[r][c]=nth;

        while (q.size()>0) {
            Info now = q.poll();

            for(int d=0;d<deltas.length;d++) {
                int nr=now.x+deltas[d][0];
                int nc=now.y+deltas[d][1];

                if(nr<0||nr>=N||nc<0||nc>=N) continue;

                if(map[nr][nc]==1&&!visited[nr][nc]) {
                    map[nr][nc]=nth;
                    visited[nr][nc]=true;
                    q.add(new Info(nr,nc,0,0));
                } else if(map[nr][nc]==0&&!visited[nr][nc]) {
                    //System.out.println(nr+" "+nc);
                    visited[nr][nc]=true;
                    sea.get(nth-1).add(new Info(nr,nc,nth,1));
                }
            }
        }
    }
}
