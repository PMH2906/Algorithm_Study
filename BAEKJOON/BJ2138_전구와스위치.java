import java.util.*;
import java.io.*;


/**
 * 그리디!! 앞에서부터 탐색하여 i-1 번째 전구를 현재 상태와 원하는 상태를 비교하며 탐색해나가는 과정!!  
 * 현재 상태의 전구에서 첫번째 스위치를 안 킨 상태와 킨 상태(누른 횟수 증)로 두 개의 배열을 체크해야함
 * 두 상태의 배열을 i = 1-N-1까지 탐색하면서 i-1 상태가 원하는 전구 상태와 다르다면 i번째 스위치를 누른다 -> 횟수 증가와 i-1,i,i+1전구 바꿔주기 , 같다면 pass
 * 모두 탐색했다면, 두 상태의 배열의 N-1(마지막 칸)과 원하는 전구 상태의 N-1(마지막 칸)을 비교하여 같으면 누른 횟수 중 가장 작은 횟수로 바꿔주기  
 * **/
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
