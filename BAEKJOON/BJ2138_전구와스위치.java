import java.util.*;
import java.io.*;

public class BJ2138_전구와스위치 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, M,cnt[], ans=Integer.MAX_VALUE;
    static char now[][],want[];
    
    public static void main(String args[]) throws Exception
    {
        N = Integer.parseInt(input.readLine());

        now = new char[2][N];
        want = new char[N];
        cnt=new int[2];
        cnt[1]+=1;
        now[0] = input.readLine().toCharArray();

        want = input.readLine().toCharArray();

        for(int i=0;i<N;i++){   
            if(i==0||i==1){
                now[1][i] = now[0][i]=='1'?'0':'1';
            }else{
                now[1][i]=now[0][i];
            }
        }

        change();
        if(ans==Integer.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(ans);
        }

        
    }

    private static void turnOnOff(int index, char[] arr, int nth){
        for(int i=index-1;i<=index+1;i++){
            if(0<=i&&i<N){
                arr[i]=arr[i]=='1'?'0':'1';
            }
        }
        cnt[nth]+=1;
        
    }
    private static void change(){
        for(int i=0;i<2;i++){
            for(int j=1;j<N;j++){
                if(now[i][j-1]!=want[j-1]){
                    turnOnOff(j,now[i],i);
                }
            }
            if(now[i][N-1]==want[N-1]){
                ans=Math.min(ans,cnt[i]);
            }
        }
    }

}
