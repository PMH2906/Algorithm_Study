package Samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 주의! 행과 열 크기 다르면 잘 확인하기
 * !=, == 조건 잘 확인하기 
 * 
 * **/
public class 포탑부수기 {
	
	static BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output=new StringBuilder();
	static StringTokenizer tokens;
	static int N, M, K, removedPotabCnt=0;
	static int[][] mapPotabNum, deltas= {{0,1},{1,0},{0,-1},{-1,0},{-1,-1},{-1,1},{1,-1},{1,1}};
	static List<Potab> potabList;
	
	public static class Potab{
		int x, y, attackTern, power;
		boolean isAttack, isRemoved;
		
		public Potab(int x, int y, int power, int attackTern, boolean isAttack, boolean isRemoved) {
			super();
			this.x = x;
			this.y = y;
			this.power=power;
			this.attackTern = attackTern;
			this.isAttack = isAttack;
			this.isRemoved = isRemoved;
		}

		@Override
		public String toString() {
			return "Potab [x=" + x + ", y=" + y + ", attackTern=" + attackTern + ", power=" + power + ", isAttack="
					+ isAttack + ", isRemoved=" + isRemoved + "]";
		}	
	}
	public static class LowPowerPotab implements Comparable<LowPowerPotab>{
		Potab potab;
		int potabNum;

		public LowPowerPotab(Potab potab, int potabNum) {
			super();
			this.potab = potab;
			this.potabNum=potabNum;
		}

		@Override
		public int compareTo(LowPowerPotab o) {
			if(this.potab.power==o.potab.power) {
				if(this.potab.attackTern==o.potab.attackTern) {
					if(this.potab.x+this.potab.y==o.potab.x+o.potab.y) {
						return Integer.compare(this.potab.y,o.potab.y)*-1;
					}
					return Integer.compare(this.potab.x+this.potab.y,o.potab.x+o.potab.y)*-1;
				}
				return Integer.compare(this.potab.attackTern, o.potab.attackTern)*-1;
			}
			return Integer.compare(this.potab.power,o.potab.power);
		}
	}
	
	public static class HigherPowerPotab implements Comparable<HigherPowerPotab>{
		Potab potab;
		int potabNum;

		public HigherPowerPotab(Potab potab, int potabNum) {
			super();
			this.potab = potab;
			this.potabNum=potabNum;
		}

		@Override
		public int compareTo(HigherPowerPotab o) {
			if(this.potab.power==o.potab.power) {
				if(this.potab.attackTern==o.potab.attackTern) {
					if(this.potab.x+this.potab.y==o.potab.x+o.potab.y) {
						return Integer.compare(this.potab.y,o.potab.y);
					}
					return Integer.compare(this.potab.x+this.potab.y,o.potab.x+o.potab.y);
				}
				return Integer.compare(this.potab.attackTern, o.potab.attackTern);
			}
			return Integer.compare(this.potab.power,o.potab.power)*-1;
		}
	}
	
	public static class RootInfo {
		int x, y;
		
		List<Integer> root=new ArrayList<>();
		
		public RootInfo(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
		public RootInfo(int x, int y, List<Integer> root, int potab) {
			super();
			this.x = x;
			this.y = y;
			this.root = new ArrayList<>(root);
			this.root.add(potab);
		}
	}
	
	public static void main(String[] args) throws IOException {
		Runtime runtime = Runtime.getRuntime();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

		int T=Integer.parseInt(input.readLine());
		for(int i=0;i<T;i++) {	
	
			tokens=new StringTokenizer(input.readLine());
			N=Integer.parseInt(tokens.nextToken());
			M=Integer.parseInt(tokens.nextToken());
			K=Integer.parseInt(tokens.nextToken());
			
			mapPotabNum=new int[N][M];
			potabList=new ArrayList<>();
			potabList.add(new Potab(0,0,0,0, false,false));
			removedPotabCnt=0;
			
			// 포탑 & map 채우기 
			for(int r=0;r<N;r++) {
				tokens=new StringTokenizer(input.readLine());
				for(int c=0;c<M;c++) {
					int power=Integer.parseInt(tokens.nextToken());
					if(power>0) {
						removedPotabCnt+=1;
						potabList.add(new Potab(r,c,power,0,false,false));
						mapPotabNum[r][c]=removedPotabCnt;
					}
				}
			}
			
			for(int k=1;k<=K;k++) {
				if(removedPotabCnt<=1) break;
				
				Potab lowPowerPotab=potabList.get(selectLowPowerPotab());
				Potab higherPowerPotab=potabList.get(selectHigherPowerPotab());
				
				lowPowerPotab.power+=N+M;
				lowPowerPotab.attackTern=k;
				lowPowerPotab.isAttack=true;
	
				attack(lowPowerPotab, higherPowerPotab);
	
				finish();
			}
			
			Potab higherPowerPotab=potabList.get(selectHigherPowerPotab());
			System.out.println(higherPowerPotab.power);
		}
		
		long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("사용한 메모리: " + (memoryAfter - memoryBefore) + " bytes");
		
	}

	private static void finish() {
		
		for(int n=1;n<potabList.size();n++) {
			if(potabList.get(n).isRemoved) continue;
			if(potabList.get(n).isAttack) {
				potabList.get(n).isAttack=false;
			} else {
				potabList.get(n).power+=1;
			}
		}
	}

	private static void attack(Potab lowPowerPotab, Potab higherPowerPotab) {
		
		if(!attackOne(lowPowerPotab, higherPowerPotab)) {
			attackTwo(lowPowerPotab, higherPowerPotab);
		}	
		
		// 강한 포탑 공격력 감소 
		higherPowerPotab.power-=lowPowerPotab.power;
		higherPowerPotab.isAttack=true;
		if(higherPowerPotab.power<=0) {
			mapPotabNum[higherPowerPotab.x][higherPowerPotab.y]=0;
			higherPowerPotab.isRemoved=true;
			removedPotabCnt-=1;
		} 
	}

	private static void attackTwo(Potab lowPowerPotab, Potab higherPowerPotab) {
		
		for(int d=0;d<deltas.length;d++) {
			int nx=higherPowerPotab.x+deltas[d][0]<0?N-1:(higherPowerPotab.x+deltas[d][0])%N;
			int ny=higherPowerPotab.y+deltas[d][1]<0?M-1:(higherPowerPotab.y+deltas[d][1])%M;
			
			// 주변 포탑 중 공격 포탑은 제외 
			if(nx==lowPowerPotab.x&&ny==lowPowerPotab.y) continue;
			
			if(mapPotabNum[nx][ny]>0) {
				// 주변 포탑 공격력 감소 
				Potab potab=potabList.get(mapPotabNum[nx][ny]);
				potab.power-=(lowPowerPotab.power/2);
				potab.isAttack=true;
				if(potab.power<=0) {
					mapPotabNum[potab.x][potab.y]=0;
					potab.isRemoved=true;
					removedPotabCnt-=1;
				} 
			}
		}
	}

	private static boolean attackOne(Potab lowPowerPotab, Potab higherPowerPotab) {
		
		Queue<RootInfo> q=new LinkedList<>();
		boolean[][] visited=new boolean[N][M];
		q.add(new RootInfo(lowPowerPotab.x, lowPowerPotab.y));
		visited[lowPowerPotab.x][lowPowerPotab.y]=true;
		
		while(q.size()>0) {
			
			RootInfo now=q.poll();
			
			for(int d=0;d<4;d++) {
				int nx=now.x+deltas[d][0]<0?N-1:(now.x+deltas[d][0])%N;
				int ny=now.y+deltas[d][1]<0?M-1:(now.y+deltas[d][1])%M;
				
				if(nx==higherPowerPotab.x&&ny==higherPowerPotab.y) {
					
					// 경로 포탑 공격력 감소 
					for(Integer potabNum : now.root) {
						Potab potab=potabList.get(potabNum);
						potab.power-=(lowPowerPotab.power/2);
						potab.isAttack=true;
						if(potab.power<=0) {
							mapPotabNum[potab.x][potab.y]=0;
							potab.isRemoved=true;
							removedPotabCnt-=1;
						} 
					}
					
					return true;
				}
				else if(mapPotabNum[nx][ny]>0&&!visited[nx][ny]) {
					visited[nx][ny]=true;
					q.add(new RootInfo(nx, ny, now.root, mapPotabNum[nx][ny]));
				}
			}
		}
		
		return false;
	}

	private static int selectHigherPowerPotab() {
		PriorityQueue<HigherPowerPotab> pq=new PriorityQueue<>();
		
		for(int n=1;n<potabList.size();n++) {
			if(potabList.get(n).isRemoved) continue;
			
			pq.add(new HigherPowerPotab(potabList.get(n),n));
		}
		return pq.poll().potabNum;
	}

	private static int selectLowPowerPotab() {
		PriorityQueue<LowPowerPotab> pq=new PriorityQueue<>();
		
		for(int n=1;n<potabList.size();n++) {
			if(potabList.get(n).isRemoved) continue;
			
			pq.add(new LowPowerPotab(potabList.get(n),n));
		}
		return pq.poll().potabNum;
	}
}
