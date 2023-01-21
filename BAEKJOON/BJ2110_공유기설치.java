import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2110_공유기설치 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, C, ans = Integer.MIN_VALUE, arr[];
    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(input.readLine());

        N = Integer.parseInt(tokens.nextToken());
        C = Integer.parseInt(tokens.nextToken());

        arr = new int[N];

        for(int i=0;i<N;i++) arr[i]=Integer.parseInt(input.readLine());

        Arrays.sort(arr); // 정렬

        int min = 1; // 가장 짧은 거리 차이
        int max = arr[arr.length-1]-arr[0]; // 가장 큰 가리 차이
        int mid = (min+max)/2; // 가장 인접한 두 공유기 사이 거리
        while(min<=max){
            mid = (min+max)/2; // 가장 인접합 두 공유기 사이 거리

            int pre_position = arr[0];
            int cnt = 1; // 이미 작은 집을 포함해서 1로 시작
            // 가장 작은 집의 좌표와 비교
            // => mid가 가장 인접한 공유기 사이의 거리이므로, 이 거리보다 작으면 cnt에서 제외!
            for(int i=1;i<N;i++){
                if(arr[i]-pre_position>=mid){
                    cnt++;
                    pre_position = arr[i];
                }
            }

            if(cnt>=C){ // 설치해야할 공유기의 갯수보다 많으면

                // cnt가 설치해야할 갯수 이상이면 계속 탐색해야 최대 거리를 구할 수 있음
                ans = Math.max(ans,mid);
                min = mid + 1; // 가장 짧은 거리를 mid + 1로 갱신해서 계속해서 확인해보기.

            }else{ // 설치해야할 공유기의 갯수보다 작으면
                max=mid-1;
            }

        }
        System.out.println(ans);
    }
}
