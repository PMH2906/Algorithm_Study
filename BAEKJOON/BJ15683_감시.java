import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ15683_감시 {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int R,C, ans=Integer.MAX_VALUE, empty=0;
    static int[][] map;
    static int[][] deltas={{-1,0},{1,0},{0,-1},{0,1}};
    static int[][][] cctv={{{0},{1},{2},{3}},{{0,1},{2,3}},{{0,3},{3,1},{1,2},{2,0}},{{0,2,3},{3,0,1},{1,2,3},{2,0,1}},{{0,1,2,3}}};
    static List<Point> cctvPoint = new ArrayList<>(), cctvDirection=new ArrayList<>();

    public static class Point {
        int x, y, num, direction[];

        public Point(int x, int y, int num) {
            this.x=x;
            this.y=y;
            this.num =num;
        }

        public Point(int x, int y, int num, int[] direction) {
            this.x=x;
            this.y=y;
            this.num =num;
            this.direction=direction;
        }
    }

    public static void main(String[] args) throws IOException {

        tokens=new StringTokenizer(input.readLine());
        R=Integer.parseInt(tokens.nextToken());
        C=Integer.parseInt(tokens.nextToken());
        map=new int[R][C];

        for(int r=0;r<R;r++) {
            tokens=new StringTokenizer(input.readLine()," ");
            for(int c=0;c<C;c++) {
                map[r][c]=Integer.parseInt(tokens.nextToken());
                if(map[r][c]>=1&&map[r][c]<=5) cctvPoint.add(new Point(r,c,map[r][c]-1));
                if(map[r][c]==0) empty+=1;
            }
        }
        dfs(0);
        System.out.println(ans);
    }

    // cctvPoint : cctv가 있는 r,c,cctv 번호
    // cctvDirection : cctvPoint 정보와 cctv 번호에 맞는 90도 방향 리스트
    // 해당 함수는 cctv 번호에 맞는 90도 방향 리스트의 조합 계산
    private static void dfs(int depth) {

        if(depth==cctvPoint.size()) {
            calculation(cctvDirection);
            return;
        }

        Point cctvPointNow=cctvPoint.get(depth);
        for (int[] cctvDirectionArray : cctv[cctvPointNow.num]) {
            cctvDirection.add(new Point(cctvPointNow.x, cctvPointNow.y, cctvPointNow.num, cctvDirectionArray));
            dfs(depth+1);
            cctvDirection.remove(cctvDirection.size()-1);
        }
    }

    // cctv가 있는 위치에서 각 cctv 방향에 맞게 감시할 수 있는 칸을 모두 visited 처리 후 사각지대 카운트 세기
    private static void calculation(List<Point> cctvDirection) {
        boolean[][] visited=new boolean[R][C];
        for(Point point : cctvDirection) {

            for(int cctvList:point.direction) {
                int nx=point.x+deltas[cctvList][0];
                int ny=point.y+deltas[cctvList][1];
                while(true) {
                    if(nx<0||nx>=R||ny<0||ny>=C) break;
                    if(map[nx][ny]==6) break;

                    visited[nx][ny]=true;

                    nx+=deltas[cctvList][0];
                    ny+=deltas[cctvList][1];
                }
            }
        }

        int cnt=0;
        for(int r=0;r<R;r++) {
            for(int c=0;c<C;c++) {
                if(visited[r][c]==true&&map[r][c]==0) cnt+=1;
            }
        }
        ans=Math.min(ans, empty-cnt);
    }
}
