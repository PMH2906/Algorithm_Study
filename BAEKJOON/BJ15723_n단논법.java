import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ15723_n단논법 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder output = new StringBuilder();
    static int N,M;
    static int[] data = new int[26]; // 전제 조건 중 첫번째 알파벳에 두번째 알파벳 아스키코드 저장할 배열
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(input.readLine());

        for(int n=0;n<N;n++){
            char[] in = input.readLine().toCharArray();
            data[in[0]-'a']=in[in.length-1]; // 전제 조건 중 첫번째 알파벳에 두번째 알파벳 아스키코드 저장
        }
        M = Integer.parseInt(input.readLine());

        for(int m=0;m<M;m++){
            char[] in = input.readLine().toCharArray(); // 결론 중 첫번째 알파벳부터 전제 조건에서 채운 배열을 이용하여 탐색
            int next = in[0]-'a';
            while(true){
                int out = data[next];
                if(out==0) { // 탐색하면서 0이면 더 이상 탐색할 곳(a->b c->d/ a->c일 경우, a->b 탐색 후 더 이상 탐색할 곳 없으므로 )이 없으면, 해당 결론은 거짓
                    output.append("F\n");
                    break;
                }else if(out==in[in.length-1]){ // 탐색하다가 결론에서 두번째 알파벳 만나면(a->b b->c/ a->c일 경우, a->b->c로 탐색 가능)결론은 진실
                    output.append(("T\n"));
                    break;
                }
                next=out-'a'; // 두 조건 다 만족안할경우는 계속해서 탐색할 수 있도록 출력된 값 - 'a' 처리해주어 배열에 접근가능하도록 함!
            }
        }
        System.out.print(output);
    }
}
