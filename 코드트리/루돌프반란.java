package Samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 루돌프의 움직이는 조건 잘 보기!!
 * 루돌프는 가장 가까운 산타를 향해 8가지 방향으로 움직일 수 있는데, 가까워지는 방향으로 돌진한다.
 * 
 * 산타의 움직이는 조건 잘 보기!
 * 움직일 수 있는 4칸 중에서 현재 칸보다 멀어지는 칸을 제외하고 움직일 수 있으나, 산타나 벽이 있으면 움직일 수 없음 
 * 우선순위 큐에 현재보다 멀어지는 방향의 칸은 안 넣어도 될 듯!
 * **/
public class 루돌프반란 {
	
	static BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder output=new StringBuilder();
	static int N, M, P, C, D, tern, removedSantaCnt=0;
	static int map[][], deltas[][]={{-1,0},{0,1},{1,0},{0,-1},{-1,-1},{-1,1},{1,-1},{1,1}};
	static Rudolph rudolph;
	static Santa[] santas;
	
	public static class Rudolph {
		int x, y, power, lastDirect;

		public Rudolph(int x, int y, int power) {
			super();
			this.x = x;
			this.y = y;
			this.power = power;
		}
	}
	
	public static class Santa {
		int x, y, power, lastDirect, score=0, stopTern=-2, num;
		boolean isRemoved=false;
		
		public Santa(int x, int y, int power, int num) {
			super();
			this.x = x;
			this.y = y;
			this.num=num;
			this.power = power;
		}
	}
	
	public static class MoveRudolph implements Comparable<MoveRudolph>{
		Santa santa;
		int dist, direct, moveX, moveY, movedDist;
		
		public MoveRudolph(Santa santa, int direct, int moveX, int moveY) {
			super();
			this.santa = santa;
			this.dist = (rudolph.x-santa.x)*(rudolph.x-santa.x)+(rudolph.y-santa.y)*(rudolph.y-santa.y);
			this.direct = direct;
			this.moveX = moveX;
			this.moveY = moveY;
			this.movedDist=(moveX-santa.x)*(moveX-santa.x)+(moveY-santa.y)*(moveY-santa.y);
		}

		@Override
		public int compareTo(MoveRudolph o) {
			if(this.dist==o.dist) {
				if(this.santa.x==o.santa.x) {
					if(this.santa.y==o.santa.y) {
						// 8가지 방향 중에서 산타와 가장 가까운 방향으로 우선순위 큐 설정  
						return Integer.compare(this.movedDist, o.movedDist);
					}
					return Integer.compare(this.santa.y, o.santa.y)*-1;
				}
				return Integer.compare(this.santa.x, o.santa.x)*-1;
			}
			return Integer.compare(this.dist, o.dist);
		}		
	}
	
	public static class MoveSanta implements Comparable<MoveSanta> {
		
		int dist, direct, moveX, moveY;

		public MoveSanta(int dist, int direct, int moveX, int moveY) {
			super();
			this.dist = dist;
			this.direct = direct;
			this.moveX = moveX;
			this.moveY = moveY;
		}

		@Override
		public int compareTo(MoveSanta o) {
			
			if(this.dist==o.dist) {
				Integer.compare(this.direct, o.direct);
			}
			return Integer.compare(this.dist, o.dist);
		}
	}

	public static void main(String[] args) throws IOException {
		
		tokens=new StringTokenizer(input.readLine());
		N=Integer.parseInt(tokens.nextToken());
		M=Integer.parseInt(tokens.nextToken());
		P=Integer.parseInt(tokens.nextToken());
		C=Integer.parseInt(tokens.nextToken());
		D=Integer.parseInt(tokens.nextToken());
		
		map=new int[N][N];
		santas=new Santa[P+1];
		
		tokens=new StringTokenizer(input.readLine());
		int x=Integer.parseInt(tokens.nextToken())-1;
		int y= Integer.parseInt(tokens.nextToken())-1;
		rudolph=new Rudolph(x, y, C);
		map[x][y]=-1;

		for(int p=1;p<=P;p++) {
			tokens=new StringTokenizer(input.readLine());
			int num=Integer.parseInt(tokens.nextToken());
			x=Integer.parseInt(tokens.nextToken())-1;
			y=Integer.parseInt(tokens.nextToken())-1;
			santas[num]=new Santa(x,y,D,num);
			map[x][y]=num;
		}
		
		for(tern=0;tern<M;tern++) {
			// 모든 산타가 탈락되면 종료 
			if(removedSantaCnt==P) break;
			
			moveRudolph();
	
			moveSanta();
		
			// 탈락하지 않는 산타에게 점수 부여 
			for(int p=1;p<=P;p++) {
				if(santas[p].isRemoved) continue;
				santas[p].score+=1;
			}
		}
		
		for(int p=1;p<=P;p++) {
			output.append(santas[p].score+" ");
		}
		
		System.out.println(output);
	}

	private static void moveSanta() {
		
		loop : for(int p=1;p<=P;p++) {
			
			PriorityQueue<MoveSanta> pq=new PriorityQueue<>();
			int originDist=(rudolph.x-santas[p].x)*(rudolph.x-santas[p].x)+(rudolph.y-santas[p].y)*(rudolph.y-santas[p].y);
			
			// 기절하거나 제거된 산타는 제외 
			if(santas[p].isRemoved||santas[p].stopTern+2>tern) continue;
		
			for(int d=0;d<4;d++) {
				int nx=santas[p].x+deltas[d][0];
				int ny=santas[p].y+deltas[d][1];
				
				if(nx<0||ny>=N||ny<0||ny>=N) continue;
				
				int dist=(rudolph.x-nx)*(rudolph.x-nx)+(rudolph.y-ny)*(rudolph.y-ny);
				if(originDist>dist) pq.add(new MoveSanta(dist, d, nx, ny));
			}
			
			MoveSanta moveSanta=pq.poll();
			while(true) {
				// 산타가 이동하는 위치에 다른 산타가 있으면 다음 이동 위치 탐색 
				if(map[moveSanta.moveX][moveSanta.moveY]>0) {
					// 산타가 더 이상 움직일 수 있는 위치가 없으면 다음 산타 탐색  
					if(pq.size()==0) continue loop;
					moveSanta=pq.poll();
				} 
				// 산타가 이동하는 위치에 다른 산타가 없으면 다음 행동 진행 
				else break;
			}
			
			// 이동 위치에 루돌프 있으면 
			if(map[moveSanta.moveX][moveSanta.moveY]==-1) {
				// 점수
				santas[p].score+=santas[p].power;
				santas[p].stopTern=tern;
				// 산타 반대 편으로 이동 
				interact(santas[p], (moveSanta.direct+2)%4, santas[p].power-1);
				
			} else {
				// 단순 이동
				map[santas[p].x][santas[p].y]=0;
				santas[p].x=moveSanta.moveX;
				santas[p].y=moveSanta.moveY;
				// map에 산타 이동 표시
				map[santas[p].x][santas[p].y]=santas[p].num;
			}	
		}
	}

	private static void moveRudolph() {
		PriorityQueue<MoveRudolph> pq=new PriorityQueue<>();
		
		// 가장 가까운 산타와 해당 산타와 가장 가까운 8가지 방향 중 하나 탐색 
		for(int d=0;d<deltas.length;d++) {
			for(int p=1;p<=P;p++) {
				if(santas[p].isRemoved) continue; // 탈락한 산타는 제외				
				
				int nx=rudolph.x+deltas[d][0];
				int ny=rudolph.y+deltas[d][1];
				if(nx<0||ny>=N||ny<0||ny>=N) continue;
				
				pq.add(new MoveRudolph(santas[p], d, nx, ny));
			}
		}
		
		MoveRudolph moveRudolph=pq.poll();
		
		// 루돌프 이동
		map[rudolph.x][rudolph.y]=0;
		rudolph.x=moveRudolph.moveX;
		rudolph.y=moveRudolph.moveY;
		
		// 이동한 위치에 산타있을 경우
		if(map[rudolph.x][rudolph.y]>0) {
			// 점수얻기
			santas[map[rudolph.x][rudolph.y]].score+=rudolph.power;
			// 산타 기절 턴 입력 
			santas[map[rudolph.x][rudolph.y]].stopTern=tern;
			// 산타 이동 
			interact(santas[map[rudolph.x][rudolph.y]], moveRudolph.direct, rudolph.power);
		}
		// map에 루돌프 이동 표시 
		map[rudolph.x][rudolph.y]=-1;
	}

	private static void interact(Santa santa, int direct, int power) {
		
		int nx=santa.x+deltas[direct][0]*power;
		int ny=santa.y+deltas[direct][1]*power;
		
		// 밖으로 나가면 탈락 
		if(nx<0||nx>=N||ny<0||ny>=N) {
			santa.isRemoved=true;
			map[santa.x][santa.y]=0;
			removedSantaCnt+=1;
			return;
		}
		
		// 이동
		map[santa.x][santa.y]=0;
		santa.x=nx;
		santa.y=ny;
		
		// 이동한 곳에 산타가 있으면 상호작용 
		if(map[santa.x][santa.y]>0) {
			interact(santas[map[santa.x][santa.y]], direct, 1);
		}
		
		// 모든 산타와 상호작용 후 map에 자신의 산타 정보 입력 
 		map[santa.x][santa.y]=santa.num;
	}

}
