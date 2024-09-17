import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RE_BJ2110_공유기설치 {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, C, home[], ans;

    public static void main(String[] args) throws IOException {

        tokens=new StringTokenizer(input.readLine());
        N=Integer.parseInt(tokens.nextToken());
        C=Integer.parseInt(tokens.nextToken());
        home=new int[N];

        for(int i=0;i<N;i++) {
            home[i]=Integer.parseInt(input.readLine());
        }

        Arrays.sort(home);
        int min=1;
        int max=home[home.length-1]-home[0];
        int mid;

        while(min<=max) {
            mid=(min+max)/2;

            if(getMinDistance(mid)>=C) {
                ans=mid;
                min=mid+1;
            } else {
                max=mid-1;
            }
        }

        System.out.println(ans);
    }

    public static int getMinDistance(int maxDistance) {

        int beforeHome=home[0];
        int cnt=1;

        for(int i=1;i<home.length;i++) {
            if(home[i]-beforeHome>=maxDistance) {
                cnt+=1;
                beforeHome=home[i];
            }
        }

        return cnt;
    }

}
