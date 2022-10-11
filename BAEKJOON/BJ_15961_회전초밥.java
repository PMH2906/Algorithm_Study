package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_15961_회전초밥 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder output = new StringBuilder();
	static int[] check,list;
	static int N, D, K, C, max_cnt=Integer.MIN_VALUE, cnt=1;
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		N=Integer.parseInt(tokens.nextToken());
		D=Integer.parseInt(tokens.nextToken());
		K=Integer.parseInt(tokens.nextToken());
		C=Integer.parseInt(tokens.nextToken());
		
		list=new int[N];
		check = new int[D+1];
		
		for(int n=0;n<N;n++) {
			list[n]=Integer.parseInt(input.readLine());
		}
		check[C]+=1;
		for(int n=0;n<K;n++) {
			if(check[list[n]]==0) cnt++;
			check[list[n]]+=1;
		}
		max_cnt = cnt;
		int start = 0;
		
		for(int finish=K;finish<N;finish++) {
			
			if(list[finish]!=list[start]) {
				check[list[start]]-=1;
				if(check[list[start]]==0) cnt--;
				if(check[list[finish]]==0) cnt++;
				check[list[finish]]+=1;
			}
			start +=1;
			max_cnt = Math.max(max_cnt, cnt);
		}
		System.out.println(max_cnt);

	}

}
