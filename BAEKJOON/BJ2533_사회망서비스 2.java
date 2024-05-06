import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ2533_사회망서비스 {

    static int N;
    static List<Integer>[] node;
    static int[][] dp;
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(input.readLine());
        node = new ArrayList[N+1];
        visited=new boolean[N+1];
        dp=new int[N+1][2]; // 0: 자신이 얼리어댑터인 경우, 1: 자신이 얼리어댑터가 아닌 경우

        for(int i=0;i<=N;i++) {
            node[i]=new ArrayList<>();
        }

        for(int i=1;i<N;i++) {
            tokens = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());

            node[a].add(b);
            node[b].add(a);
        }

        dfs(1); // N이 2부터 시작이므로 1을 임의로 잡음. 어느 노드를 시작해도 답은 같음. 이 문제에서는 Root가 존재하지 않음

//        for(int i=0;i<=N;i++) {
//            System.out.println(Arrays.toString(dp[i]));
//        }

        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    public static void dfs(int now) {
        visited[now]=true;
        dp[now][0]=1;
        dp[now][1]=0;

        for(int next : node[now]) {
            if(!visited[next]) {
                dfs(next);
                dp[now][0]+=Math.min(dp[next][0], dp[next][1]); // 자신이 얼리어댑터인 경우, 자식은 얼리어댑터가 될 수도 혹은 아닐 수도 있으므로 자식의 dp[0]과 dp[1] 중 작은 값 더하기
                dp[now][1]+=dp[next][0]; // 자신이 얼리어댑터가 아닌 경우, 자식은 무조건 얼리어댑터가 되어야 함
            }
        }
    }
}

