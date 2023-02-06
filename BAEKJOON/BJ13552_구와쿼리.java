import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ13552_구와쿼리 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder output = new StringBuilder();
    static int N, M ;
//    static Point points[];
    static int points[][];
    
//    public static class Point{
//    	int x, y, z;
//    	
//    	public Point(int x, int y, int z) {
//    		this.x = x;
//    		this.y = y;
//    		this.z = z;
//    	}
//    }
    
    public static void main(String[] args) throws IOException {
       N = Integer.parseInt(input.readLine());
      // points = new Point[N];
       points = new int[N][3];
       
       for(int n=0;n<N;n++) {
    	   tokens = new StringTokenizer(input.readLine());
    	   
    	   int x = Integer.parseInt(tokens.nextToken());
    	   int y = Integer.parseInt(tokens.nextToken());
    	   int z = Integer.parseInt(tokens.nextToken());
    	   
    	   points[n][0]=x;
    	   points[n][1]=y;
    	   points[n][2]=z;
       }
       
       
       M = Integer.parseInt(input.readLine());
       
       for(int m=0;m<M;m++) {
    	   tokens = new StringTokenizer(input.readLine());
    	   
    	   int x = Integer.parseInt(tokens.nextToken());
    	   int y = Integer.parseInt(tokens.nextToken());
    	   int z = Integer.parseInt(tokens.nextToken());
    	   int r = Integer.parseInt(tokens.nextToken());
    	   
    	   output.append(find(x,y,z,r)+"\n");
    	   
       }
       
       System.out.println(output);
       
    }

	private static int find(int x, int y, int z, int r) {
		int cnt =0;
		
		for (int[] point : points) {
			int a = point[0]-x;
			int b = point[1]-y;
			int c = point[2]-z;
			
			// 시간 초과 방지하기위해 pow 사용 안 하
			long pointDist = (long)a*a+(long)b*b+(long)c*c; // 오버플로우 방지하기 위해 long으로 바꾸고 계산해주기!! 주의!!!
			long powR = (long)r*r; 
			if(pointDist<=powR) cnt++;
		}
		
		return cnt;
	}
}