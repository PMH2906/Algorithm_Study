import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ14267_회사문화 {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder output = new StringBuilder();
    static int N, M, score[];
    static List<Integer>[] node;

    public static void main(String[] args) throws IOException {
        tokens=new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        node=new ArrayList[N+1];
        score=new int[N+1];
        for(int i=1;i<=N;i++) node[i]=new ArrayList<>();

        tokens=new StringTokenizer(input.readLine());
        tokens.nextToken();
        for(int i=2;i<=N;i++) node[Integer.parseInt(tokens.nextToken())].add(i);

        for(int i=0;i<M;i++) {
            tokens=new StringTokenizer(input.readLine());
            score[Integer.parseInt(tokens.nextToken())]+=Integer.parseInt(tokens.nextToken());
        }

        dfs(1);

        for(int i=1;i<=N;i++) {
            output.append(score[i] + " ");
        }
        System.out.println(output);
    }

    private static void dfs(int n) {

        for(int next : node[n]) {
            score[next]+=score[n];
            dfs(next);
        }
    }

}
