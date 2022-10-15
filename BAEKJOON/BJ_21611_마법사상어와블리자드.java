package Algorithm;

import java.io.BufferedReader;

import java.io.InputStreamReader;

import java.util.StringTokenizer;

public class BJ_21611_마법사상어와블리자드 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder output;
	static int N, M, map[][],D[],S[], ans,size;
	static int twoDim[][];
	static int[][] deltas= {{0,-1},{1,0},{0,1},{-1,0}}, deltasDestroy= {{-1,0},{1,0},{0,-1},{0,1}};
	static Info[] oneDim;
	public static class Info{
		int x, y;

		public Info(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
	
		// 초기화
		map = new int[N][N];
		twoDim = new int[N][N];
		oneDim = new Info[N*N];
		ans=0;
		size=N*N;
		D = new int[M];
		S = new int[M];
		for(int r=0;r<N;r++) {
			tokens = new StringTokenizer(input.readLine());
			for(int c=0;c<N;c++) map[r][c] = Integer.parseInt(tokens.nextToken());
		}
		for(int m=0;m<M;m++) {
			tokens = new StringTokenizer(input.readLine());
			D[m] = Integer.parseInt(tokens.nextToken())-1;
			S[m] = Integer.parseInt(tokens.nextToken());
		}
		
		init();
		for(int m=0;m<M;m++) {
			
			destroy(S[m],D[m]);
			move();
			while(true) {
				int cnt = bomb();
				if(cnt==0) break;
				move();
			}
			
			change();
			
		}
		System.out.println(ans);
		
	}
	private static void change() {
		int cnt=1;
		int ball=map[oneDim[1].x][oneDim[1].y];
		int idx=1;
		int[][] newMap=new int[N][N];
		for(int i=2;i<N*N;i++) {
			Info now = oneDim[i];
			if(map[now.x][now.y]==0) break;
			if(map[now.x][now.y]==ball) cnt+=1;
			else {
				if(idx>=N*N) {
					map=newMap;
					return;
				}
				newMap[oneDim[idx].x][oneDim[idx].y]=cnt;
				idx+=1;
				if(idx>=N*N) {
					map=newMap;
					return;
				}
				newMap[oneDim[idx].x][oneDim[idx].y]=ball;
				idx+=1;
			
				ball=map[now.x][now.y];
				cnt=1;
			}	
		}
		if(idx>=N*N) {
			map=newMap;
			return;
		}
		newMap[oneDim[idx].x][oneDim[idx].y]=cnt;
		idx+=1;
		if(idx>=N*N) {
			map=newMap;
			return;
		}
		newMap[oneDim[idx].x][oneDim[idx].y]=ball;
	
		map=newMap;
	}
	private static int bomb() {
		int cnt=1;
		int ball=-1;
		int bombcnt=0;
		int idx = N*N;
		for(int i=1;i<N*N;i++) {
			Info info = oneDim[i];
			if(map[info.x][info.y]==0) {
				idx=i;
				break;
			}
			if(ball==map[info.x][info.y]) cnt+=1;
			else {
				if(cnt>=4) {
					for(int j=1;j<=cnt;j++) {
						Info bombLoc = oneDim[i-j];
						map[bombLoc.x][bombLoc.y]=0;
					}	
					bombcnt+=1;
					ans+=ball*cnt;
				}
				cnt=1;//초기화
				ball = map[info.x][info.y]; // 현재볼로갱신
			}
		}
		if(cnt>=4) {
			for(int j=1;j<=cnt;j++) {
				Info bombLoc = oneDim[idx-j];
				map[bombLoc.x][bombLoc.y]=0;
			}			
			bombcnt+=1;
			ans+=ball*cnt;
		}
	
		return bombcnt;
	}
	private static void move() {
		int empty=0;
		for(int i=1;i<N*N;i++) {
			Info info = oneDim[i];
			if(map[info.x][info.y]==0)empty+=1;
			else if(map[info.x][info.y]!=0&&empty>0) {
				Info moveLoc=oneDim[i-empty];
				map[moveLoc.x][moveLoc.y]=map[info.x][info.y];
				//옮겨준 공은 빈칸으로
				map[info.x][info.y]=0;
			}
		}
		
	}
	private static void destroy(int s, int d) {
		for(int i=1;i<=s;i++) {
			int x = N/2+(deltasDestroy[d][0])*i;
			int y = N/2+(deltasDestroy[d][1])*i;
	
			map[x][y]=0;
		}
		
	}
	private static void init() {
		int x = N/2;
		int y = N/2;
		int dist =0;
		int dir =-1;
		int oneDimIdx=1;
		oneDim[0]=new Info(N/2, N/2);//상어위치
		twoDim[N/2][N/2]=0;//일차원 위치
		while(true) {
			dir=(dir+1)%4;
			if(dir==0||dir==2) dist++;
			for(int i=0;i<dist;i++) {
				if(x==0&&y==0) return;
				x+=deltas[dir][0];
				y+=deltas[dir][1];
				twoDim[x][y]=oneDimIdx;
				oneDim[oneDimIdx]=new Info(x,y);
				oneDimIdx+=1;
			}
		}
	}

}
