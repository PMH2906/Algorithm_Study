import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1707_이분그래프 {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder output=new StringBuilder();
    static int T, nodeColors[];
    static List<Integer>[] node;

    public static void main(String[] args) throws IOException {

        T=Integer.parseInt(input.readLine());

        loop : for(int t=0;t<T;t++) {
            int V, E;

            tokens=new StringTokenizer(input.readLine());
            V=Integer.parseInt(tokens.nextToken());
            E=Integer.parseInt(tokens.nextToken());

            node=new ArrayList[V+1];
            nodeColors=new int[V+1];
            for(int v=0;v<=V;v++) node[v]=new ArrayList<>();

            // 간선 입력
            for(int e=0;e<E;e++) {
                tokens=new StringTokenizer(input.readLine());
                int a=Integer.parseInt(tokens.nextToken());
                int b=Integer.parseInt(tokens.nextToken());

                // 양방향 저장
                node[a].add(b);
                node[b].add(a);
            }

            boolean check=false;
            for(int v=1;v<=V;v++) {
                if(nodeColors[v]==0) {

                    // 이분 그래프인지 확인
                    if(!isBipartiteGraph(v)) {
                        output.append("NO\n");
                        continue loop;
                    }
                }
            }
            output.append("YES\n");
        }

        System.out.print(output);
    }

    private static boolean isBipartiteGraph(int v) {

        Queue<Integer> q=new LinkedList<>();

        // 임의로 집합 순번 선언
        nodeColors[v]=1;
        q.add(v);

        while(q.size()>0) {
            int nowNode=q.poll();

            for(Integer nextNode : node[nowNode]) {

                // 현재 탐색 노드와 연결된 다음 탐색 노드의 집한 순번이 같을 경우 false 반환
                if(nodeColors[nowNode]==nodeColors[nextNode]) return false;

                // 현재 탐색 노드와 연결된 다음 탐색 노드가 순번이 없을 경우
                // 1. 현재 탐색 노드와 다른 순번 저장
                // 2. 다음 노드 탐색할 수 있도록 큐 삽입
                if(nodeColors[nextNode]==0) {
                    nodeColors[nextNode]=nodeColors[nowNode]*(-1);
                    q.add(nextNode);
                }
            }
        }
        return true;
    }
}
