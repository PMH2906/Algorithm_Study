import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 코드트리_술래잡기 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder output = new StringBuilder();
    static int N, M, H, K, ans;
    static boolean[][] tree;
    static Behind[] behinds;
    static int[][] map;
    static int[] nxtDirs;
    static int[][] deltas = {{-1,0},{0,1},{1,0},{0,-1}}; // 상 우 하 좌 
    static Catch catchP;
    
    public static class Behind{
    	int x, y, dir;
    	boolean removed;
		public Behind(int x, int y, int dir, boolean removed) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.removed = removed;
		}
    	
    }
    
    public static class Catch{
    	int x, y, nxtDirIdx;

		public Catch(int x, int y, int nxtDirIdx) {
			super();
			this.x = x;
			this.y = y;
			this.nxtDirIdx = nxtDirIdx;
		}
    	
    }
    
	public static void main(String[] args) throws IOException {
		
		tokens = new StringTokenizer(input.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		H = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		
		behinds = new Behind[M+1];
		map = new int[N][N];
		tree = new boolean[N][N];
		ans = 0;
		nxtDirs = new int[2*N*N-2];
		
		for(int m=1;m<=M;m++) {
			tokens = new StringTokenizer(input.readLine());
			
			int x = Integer.parseInt(tokens.nextToken())-1;
			int y = Integer.parseInt(tokens.nextToken())-1;
			int dir = Integer.parseInt(tokens.nextToken());
		
			behinds[m] = new Behind(x, y, dir, false);
			map[x][y] = m;
		
		}
		
		for(int h=0;h<H;h++) {
			tokens = new StringTokenizer(input.readLine());
			
			int x = Integer.parseInt(tokens.nextToken())-1;
			int y = Integer.parseInt(tokens.nextToken())-1;
			
			tree[x][y] = true;
		}
		
		init();
		catchP = new Catch(N/2, N/2, 0);
		for(int k=1;k<=K;k++) {
			int cnt = 0;
			
			// 도망자 옮기기 
			for(int i=1;i<=M;i++) {
		
				if(behinds[i].removed) continue;
				
				int dist = Math.abs(catchP.x-behinds[i].x)+Math.abs(catchP.y-behinds[i].y);
				if(dist<=3) {
				
					int nx = behinds[i].x+deltas[behinds[i].dir][0];
					int ny = behinds[i].y+deltas[behinds[i].dir][1];
					
					if(nx<0||nx>=N||ny<0||ny>=N) {
						behinds[i].dir=(behinds[i].dir+2)%4;
						nx = behinds[i].x+deltas[behinds[i].dir][0];
						ny = behinds[i].y+deltas[behinds[i].dir][1];
					}
					
					if(nx==catchP.x&&ny==catchP.y) continue;
					
					// 옮기기 
					behinds[i].x = nx;
					behinds[i].y = ny;
				}
			}

			// 술래 이동 및 다음 움직여야 할 방향 
			catchP.x = catchP.x+deltas[nxtDirs[catchP.nxtDirIdx]][0];
			catchP.y = catchP.y+deltas[nxtDirs[catchP.nxtDirIdx]][1];
			catchP.nxtDirIdx = (catchP.nxtDirIdx+1)%nxtDirs.length;
			
			//잡기
			for(int i=0;i<=2;i++) {
				int nx = catchP.x+deltas[nxtDirs[catchP.nxtDirIdx]][0]*i;
				int ny = catchP.y+deltas[nxtDirs[catchP.nxtDirIdx]][1]*i;
				
				for(int m=1;m<=M;m++) {
					if(behinds[m].removed) continue;
					
					if(behinds[m].x==nx&&behinds[m].y==ny&&!tree[nx][ny]) {
						behinds[m].removed=true;
						cnt++;
					}
				}
			}
			ans += (k*cnt);
		}
		System.out.println(ans);
	}
	
	// 현재 위치에서 술래가 움직여야 할 방향. 즉 거리 3을 측정하는 방향 초기화  
	// map 2개를 만들어서 시작방향, 돌아오는 방향의 dir 정보를 저장하는 방식도 있
	private static void init() {
		int dist = 0;
		int dir = 0;
		int idx = 1;
		while(true) {
			if(idx==N*N-1) break;
			if(dir==0||dir==2) {
				dist++;
			}
			for(int i=0;i<dist;i++) {
				if(idx==N*N-1) break;
				if(i==dist-1) {
					dir = (dir+1)%4;
					nxtDirs[idx] = dir;
				}
				nxtDirs[idx] = dir;
				idx++;
			}
		}
		
		for(int i=N*N-2;i>=0;i--) {
			nxtDirs[idx]=(nxtDirs[i]+2)%4;
			idx++;
		}
		
	}

}
