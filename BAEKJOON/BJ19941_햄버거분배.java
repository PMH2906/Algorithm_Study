import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ19941_햄버거분배 {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder out = new StringBuilder();
    static int K, N, ans=0;
    static char[] arr;
    static boolean[] burger;

    public static void main(String[] args) throws IOException {
        tokens=new StringTokenizer(input.readLine());

        N=Integer.parseInt(tokens.nextToken());
        K=Integer.parseInt(tokens.nextToken());
        burger=new boolean[N];

        arr=input.readLine().toCharArray();

        for(int i=0;i<arr.length;i++) if(arr[i]=='H') burger[i]=true;

        for(int i=0;i<arr.length;i++) {
            if(arr[i]=='P') {
                int startIdx=Math.max(0, i-K);
                int endIdx=Math.min(arr.length-1, i+K);

                for(int j=startIdx;j<=endIdx;j++) {
                    if(burger[j]) {
                        // 햄버거 먹음
                        burger[j]=false;
                        ans+=1;
                        break;
                    }
                }
            }
        }

        System.out.print(ans);
    }

}
