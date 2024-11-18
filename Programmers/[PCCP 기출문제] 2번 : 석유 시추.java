import java.util.*;
class Solution {
    static int[][] landNum;
    static boolean[][] visited;
    static List<Integer> landCnt;

    public int solution(int[][] land) {

        landNum=new int[land.length][land[0].length];
        visited=new boolean[land.length][land[0].length];
        landCnt=new ArrayList<>();
        int num=0;
        for(int r=0;r<land.length;r++) {
            for(int c=0;c<land[0].length;c++) {
                if(!visited[r][c]&&land[r][c]==1) {
                    num+=1;
                    landNum[r][c]=num;
                    visited[r][c]=true;
                    // landCnt.add(dfs(r,c,land, num));
                    landCnt.add(bfs(r,c,land, num));
                }
            }
        }
        int answer = Integer.MIN_VALUE;

        for(int c=0;c<land[0].length;c++) {
            Map<Integer, Integer> cnt=new HashMap<>();
            int sum=0;
            for(int r=0;r<land.length;r++) {
                if(landNum[r][c]==0) continue;
                cnt.put(landNum[r][c], landCnt.get(landNum[r][c]-1));
            }

            for (Integer key : cnt.keySet()) {
                sum+=cnt.get(key);
            }

            answer=Math.max(answer,sum);
        }
        return answer==Integer.MIN_VALUE?0:answer;

    }
//     static public int dfs(int r, int c, int[][] land, int num) {
//         int sum=1;
//         if(c+1<land[0].length&&land[r][c+1]==1&&!visited[r][c+1]) {
//             landNum[r][c+1]=num;
//             visited[r][c+1]=true;
//             sum+=dfs(r,c+1,land, num);
//         }

//         if(c-1>=0&&land[r][c-1]==1&&!visited[r][c-1]) {
//             landNum[r][c-1]=num;
//             visited[r][c-1]=true;
//             sum+=dfs(r,c-1,land, num);
//         }
//        if(r-1>=0&&land[r-1][c]==1&&!visited[r-1][c]) {
//             landNum[r-1][c]=num;
//             visited[r-1][c]=true;
//             sum+=dfs(r-1,c,land, num);
//         }
//         if(r+1<land.length&&land[r+1][c]==1&&!visited[r+1][c]) {
//             landNum[r+1][c]=num;
//             visited[r+1][c]=true;
//             sum+=dfs(r+1,c,land, num);
//         }
//         return sum;
//     }

    public int bfs(int r, int c, int[][] land, int num) {
        Queue<int[]> q=new LinkedList<>();
        int[][] deltas={{0,1},{0,-1},{1,0},{-1,0}};
        int sum=1;
        q.add(new int[]{r,c});

        while(q.size()>0) {
            int[] now=q.poll();
            for(int d=0;d<4;d++) {
                int nx=now[0]+deltas[d][0];
                int ny=now[1]+deltas[d][1];

                if(nx<0||nx>=land.length||ny<0||ny>=land[0].length) continue;

                if(!visited[nx][ny]&&land[nx][ny]==1) {
                    landNum[nx][ny]=num;
                    visited[nx][ny]=true;
                    q.add(new int[]{nx,ny});
                    sum+=1;
                }
            }
        }

        return sum;
    }
}