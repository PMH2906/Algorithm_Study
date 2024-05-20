import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ1522_문자열교환 {

    static List<Integer> array = new ArrayList<>();
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static char[] arr;
    static int end;
    static char leftFindChar, rightFindChar;
    static int ans=Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        arr = input.readLine().toCharArray();

        int cntA=0;
        for(char i : arr) {
            if(i=='a') cntA+=1;
        }

        end=cntA; // a의 갯수 범위만큼의 문자열에서 b의 갯수가 가장 작은값을 찾기
        for(int i=0;i<arr.length;i++) {
            int cntB=0;

            for(int j=i;j<end;j++) {
                if(arr[j%arr.length]=='b') cntB++;
            }
            end+=1;

            ans=Math.min(ans,cntB);
        }

        System.out.print(ans);
    }
}
