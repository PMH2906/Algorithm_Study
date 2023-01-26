import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1. x1, y2 를 중심으로 반지름 r1에 위치한 원이 류재명이 있을 수 있음
// 2. x2, y2 를 중심으로 반지름 r2에 위치한 원이 류재명이 있을 수 있음
// 1,2의 원의 접점을 구하면 됨!!!!!!
public class BJ1002_터렛 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder output = new StringBuilder();
    static int T, x1, y1, x2, y2, r1, r2;
	public static void main(String[] args) throws Exception, IOException {
		T = Integer.parseInt(input.readLine());
		
		for(int t=0;t<T;t++) {
			tokens = new StringTokenizer(input.readLine());
			
			x1= Integer.parseInt(tokens.nextToken());
			x2= Integer.parseInt(tokens.nextToken());
			r1= Integer.parseInt(tokens.nextToken());
			x2= Integer.parseInt(tokens.nextToken());
			y2= Integer.parseInt(tokens.nextToken());
			r2= Integer.parseInt(tokens.nextToken());
			
			output.append(find()+"\n");
		}
		System.out.println(output);
	}
	private static int find() {
		
		// 주어진 점 사이의 거리 제곱 
		long distTwoPoint = (long)Math.pow(x1-x2,2) + (long)Math.pow(y1-y2, 2);
		
		if(x1==x2 && y1==y2 && r1==r2) return -1; // 중간점과 반지름 같은 경우 -> 접점 무한대 
		
		else if(distTwoPoint > Math.pow(r1+r2, 2)) return 0; // 점 사이의 거리가 두 반지름을 합보다 클 때 -> 접점 없음 
		
		else if(distTwoPoint < Math.pow(r1-r2, 2)) return 0; // 점 사이의 거리가 두 반지름의 차이보다 작을 때 -> 하나의 원안에 하나의 원이 존재 -> 접점 없음 
		
		else if(distTwoPoint < Math.pow(r1-r2, 2)) return 1; // 점 사이의 거리가 두 반지름의 차이와 같을 때 -> 내접 -> 접점 한개 
			 
		else if(distTwoPoint == Math.pow(r1+r2, 2)) return 1; // 점 사이와 두 반지름의 합이 같을 때 -> 외접 -> 접점 한개 
		
		return 2; // 나머지 접점 두 개  
	}

}
