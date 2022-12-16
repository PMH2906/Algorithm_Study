import java.util.*;
import java.io.*;
/**
주어진 사과나무 높이의 합은 한 번에 총 3만큼 성장시키기 때문에 3의 배수가 됩니다.
주어진 사과나무 높이의 합을 3으로 나눈다면 총 걸리는 일수가 나오게 됩니다. 이 수는 또한 1만큼 자라게 하는 물뿌리개와 2만큼 자라게 하는 물뿌리개가 사용되는 횟수를 의미하기도 합니다.
홀수 높이의 사과나무는 2만큼 성장시키는 물뿌리개로는 성장시킬 수 없기 때문에 홀수 높이의 사과나무 개수를 셉니다.
만약 이 개수가 물뿌리개를 사용하는 총 일수를 초과한다면 해당 높이들을 맞출 수 없습니다.
그렇기 때문에 만약 사과나무 높이의 합이 3의 배수이고 홀수 높이의 사과나무의 개수가 물뿌리개를 사용하는 총 일수보다 작거나 같다면 YES를 출력하고 그렇지 않다면 NO를 출력합니다.
 */
public class BJ19539_사과나무 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, len[],sum=0,odd=0;
    public static void main(String args[]) throws Exception
    {
        N = Integer.parseInt(input.readLine());
        len = new int[N];
        tokens = new StringTokenizer(input.readLine());
        for(int i=0;i<N;i++){
            len[i] = Integer.parseInt(tokens.nextToken());
        }
        cal();
        if(sum%3==0&&odd<=sum/3){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }
    }
    private static void cal(){
        for(int i=0;i<len.length;i++){
        	sum+=len[i];
            if(len[i]%2==1) odd++;
        }
    }


}
