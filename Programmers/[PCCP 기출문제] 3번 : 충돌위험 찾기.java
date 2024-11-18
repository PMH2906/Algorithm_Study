import java.util.*;
class Solution {
    static int finishedRobot, ans=0;
    static Robot[] robots;
    static int[][] deltas={{1,0},{-1,0},{0,1},{0,-1}};
    static int[][] map=new int[101][101]; // Map으로 키를 좌표, 값을 로봇 갯수로 해서 비교하면 시간 복잡도 더 적을 것 같음
    static public class Robot {
        int x, y, nextRoute=1;
        int[] routes;
        boolean finished;
        public Robot(int x, int y, int[] routes) {
            this.x=x;
            this.y=y;
            this.routes=routes;
        }
        public void moveNextRoute(int[][] points, int routeLength) {
            if(this.finished) return;
            if(this.x==points[routes[nextRoute]-1][0]&&this.y==points[routes[nextRoute]-1][1])  {
                this.nextRoute+=1;
            }
            if(nextRoute>=routeLength) {
                this.finished=true;
            }
            //System.out.println(x+" "+ y+" "+nextRoute);
        }
    }
    public int solution(int[][] points, int[][] routes) {
        robots=new Robot[routes.length];

        for(int i=0;i<routes.length;i++) {
            int x=points[routes[i][0]-1][0];
            int y=points[routes[i][0]-1][1];
            robots[i]=new Robot(x,y,routes[i]);
            map[x][y]+=1;
        }

        for(int i=1;i<=100;i++) {
            for(int j=1;j<=100;j++) {
                if(map[i][j]>=2) ans+=1;
            }
        }

        while(finishedRobot<routes.length) {
            map=new int[101][101];
            for(int i=0;i<routes.length;i++) {
                if(robots[i].finished) continue;

                int minDist=Integer.MAX_VALUE;
                int minNx=0;
                int minNy=0;
                for(int d=0;d<4;d++) {
                    int nx=robots[i].x+deltas[d][0];
                    int ny=robots[i].y+deltas[d][1];

                    if(nx<1||nx>100||ny<1||ny>100) continue;

                    int dist=Math.abs(nx-points[robots[i].routes[robots[i].nextRoute]-1][0])+Math.abs(ny-points[robots[i].routes[robots[i].nextRoute]-1][1]);
                    if(minDist>dist) {
                        minNx=nx;
                        minNy=ny;
                        minDist=dist;
                    }
                }

                robots[i].x=minNx;
                robots[i].y=minNy;
                map[robots[i].x][robots[i].y]+=1;
                robots[i].moveNextRoute(points,routes[0].length);

                if(robots[i].finished) finishedRobot+=1;
            }
            for(int i=1;i<=100;i++) {
                for(int j=1;j<=100;j++) {
                    if(map[i][j]>=2) ans+=1;
                }
            }
        }
        return ans;
    }
}