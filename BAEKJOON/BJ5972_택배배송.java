import java.util.*;
import java.io.*;

public class BJ5972_택배배송
{
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N, M, D[];
	static List<Node>[] node;

	public static class Node implements Comparable<Node>{
	    int to, cnt;

	    public Node(int to, int cnt){
	        this.to = to;
	        this.cnt = cnt;
	    }

	    @Override
	    public int compareTo(Node o){
	        return Integer.compare(this.cnt, o.cnt);
	    }
	}
	
    public static void main (String args[]) throws IOException
    {
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        D = new int[N+1];
        node = new ArrayList[N+1];

        for(int i=0;i<=N;i++){
            node[i] = new ArrayList<>();
        }

        for(int m=0;m<M;m++){
            tokens = new StringTokenizer(input.readLine());

            int to = Integer.parseInt(tokens.nextToken());
            int from = Integer.parseInt(tokens.nextToken());
            int cnt = Integer.parseInt(tokens.nextToken());

            node[to].add(new Node(from,cnt));
            node[from].add(new Node(to,cnt));
        }


        dijkstra();
        System.out.print(D[N]);
    }

    public static void dijkstra(){

        Arrays.fill(D, Integer.MAX_VALUE);
        D[1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(1,0));
        while(pq.size()>0){
            Node now = pq.poll();

            if(now.to==N) break;
            for(Node next : node[now.to]){
                if(D[next.to]>D[now.to]+next.cnt){
                    D[next.to] = D[now.to]+next.cnt;
                    pq.add(new Node(next.to,D[now.to]+next.cnt));
                }
            }

        }
    }
}