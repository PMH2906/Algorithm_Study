import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ1043_거짓말 {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, M, answer=0;
    static int[] knowPeople, parents;
    static List<Integer> [] party;
    static boolean[] know;

    public static void main(String[] args) throws IOException {
        tokens=new StringTokenizer(input.readLine());
        N=Integer.parseInt(tokens.nextToken());
        M=Integer.parseInt(tokens.nextToken());

        parents=new int[N+1];
        know=new boolean[N+1];
        for(int i=1;i<=N;i++) parents[i]=i;

        party=new ArrayList[M];
        for(int i=0;i<M;i++) party[i]=new ArrayList<>();

        tokens=new StringTokenizer(input.readLine());
        int knowPeopleSize=Integer.parseInt(tokens.nextToken());
        // 진실을 아는 사람 셋팅
        knowPeople=new int[knowPeopleSize];
        for(int i=0;i<knowPeopleSize;i++) {
            knowPeople[i]=Integer.parseInt(tokens.nextToken());
        }

        for(int i=0;i<M;i++) {
            tokens=new StringTokenizer(input.readLine());
            int visitSize=Integer.parseInt(tokens.nextToken());
            int now=Integer.parseInt(tokens.nextToken());
            party[i].add(now);
            while (tokens.hasMoreElements()) {
                int next = Integer.parseInt(tokens.nextToken());
                party[i].add(next);
                union(now, next);
                now = next;
            }
        }

        if(knowPeopleSize==0) {
            System.out.println(M);
            return;
        }

        // 진실을 아는 사람의 Root 부모를 찾고, 해당 부모와 같은 부모를 가지는 참석자는 know를 true로 만든다.
        for(int i=0;i<knowPeople.length;i++) {
            int rootKnowPeople = find(knowPeople[i]);
            for(int j=1;j<parents.length;j++) {
                if(rootKnowPeople==find(j)) know[j]=true;
            }
        }

        loop : for(int i=0;i<M;i++) {
            for (int n : party[i]) {
                if(know[n]) continue loop;
            }
            answer+=1;
        }

        System.out.println(answer);
    }

    public static void union(int a, int b) {
        a=find(a);
        b=find(b);

        if(a==b) return;

        if(a<b) parents[b]=a;
        else parents[a]=b;
    }

    public static int find(int x) {
        if(parents[x]==x) return x;
        return find(parents[x]);
    }

}
