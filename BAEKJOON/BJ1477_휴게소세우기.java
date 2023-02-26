import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ1477_휴게소세우기 {
    static StringTokenizer tokens;
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, L, ans;
    static List<Integer> point;
    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(input.readLine());

        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        L = Integer.parseInt(tokens.nextToken());

        point = new ArrayList<>();
        
        tokens = new StringTokenizer(input.readLine());

        // 시작점, 끝점 추가 & 휴게소 위치 추가    
        point.add(0); 
        point.add(L);
        for(int i=0;i<N;i++){
            point.add(Integer.parseInt(tokens.nextToken()));
        }
        
        Collections.sort(point);
        
        int left=0;
        int right=L;
    
        while(right>=left) {
        	int mid = (left+right)/2;
        	int cnt =0;
        	
        	for(int i=1;i<point.size();i++) {
        		
        		// 세울 휴게소 갯수 세기 
        		int dist = point.get(i) - point.get(i-1);
        		
        		cnt+=(dist/mid);
        		if(dist%mid==0) { // 나누어 떨어진다면, 해당 지점에는 이미 휴게소가 위치하므로 하나 빼주기 
        			cnt--;
        		}
        		
        	}
        	
        	if(cnt>M) left = mid+1; 
        	else {
        		right = mid-1; // 값이 같을 때, mid가 더 작아질 수 있으므로 확인 
        		ans = mid; 
        	}
        }
        System.out.println(ans);
    }
}