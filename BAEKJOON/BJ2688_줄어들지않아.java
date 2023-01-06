import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ2688_줄어들지않아 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static int T, N;
    static long dp[][]; // int의 범위로 인해서 long으로 해야함 주의!!!!!!!!!!!!!!

    public static void main(String[] args) throws IOException {

        T = Integer.parseInt(input.readLine());

        dp = new long[65][10]; // 첫번째 : 자릿수, 두번째 : 자릿수에 들어갈 숫자 => 해당 자릿수에 들어갈 수 있는 숫자의 갯수 저장

        for(int j=0;j<10;j++) dp[1][j]=1;

        for(int i=2;i<65;i++){
            for(int j=0;j<10;j++){
                for(int z=j;z<10;z++){
                    dp[i][j]+=dp[i-1][z];
                }
            }
        }

        for(int t=0;t<T;t++){
            N = Integer.parseInt(input.readLine());
            long ans=0;
            for(int i=0;i<10;i++) ans+=dp[N][i];
            output.append(ans+"\n");
        }
        System.out.print(output); // int의 범위로 인해서 long으로 해야함 주의!!!!!!!!!!!!!!
    }
}