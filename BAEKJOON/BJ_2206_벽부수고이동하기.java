package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2206_벽부수고이동하기 {
	static StringBuilder output = new StringBuilder();
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N;
	static int M;
	static int[][] map;
	static boolean[][][] visited;
	static Queue<Integer> destroy=new LinkedList<Integer>(); // 파괴 여부
	static int[][] deltas = {{1,0},{0,1},{-1,0},{0,-1}}; // 하, 우, 상, 좌
	static Queue<int[]> q = new LinkedList<>(); 
	static int result=0;
	
	static void bfs(int r, int c) {
		int moveCnt=0;
		q.add(new int[] {r,c,moveCnt});
		destroy.add(0);
		while(q.size()>0) { 
			int[] location=q.poll();
			int destroyCheck=destroy.poll();
	
			if(location[0]==N-1&&location[1]==M-1) { // 도착
				result=location[2]+1;
				return;
			}
			for(int i=0;i<deltas.length;i++){
				int nr=location[0]+deltas[i][0]; // 다음 위치
				int nc=location[1]+deltas[i][1];
				
				if(nr<0||nr>=N||nc<0||nc>=M) continue;
				if(map[nr][nc]==1 && destroyCheck==1) continue; // 다음 위치 벽 존재 & 벽을 부순 이력 있으면 pass
				if(map[nr][nc]==1 && destroyCheck==0&&!visited[nr][nc][1]) { // 다음 위치에 벽 존재하지만 부순 이력없고,해당 벽을 부순적이없다면
					visited[nr][nc][0]=true; 
					q.add(new int[] {nr,nc,location[2]+1});
					destroy.add(1);
				}
				else if(map[nr][nc]==0) { // 다음 위치가 벽이 아닐 때
					if(destroyCheck==1&&!visited[nr][nc][1]) { // 벽을 파괴한 여부에 맞게 visited처리. visited처리되어있으면 이미 벽을 파괴한 거리가 더 짧기 때문에 탐색X
						visited[nr][nc][1] = true; 
						destroy.add(destroyCheck);
						q.add(new int[] {nr,nc,location[2]+1});
					}
					else if(destroyCheck==0&&!visited[nr][nc][0]){
						visited[nr][nc][0]=true;
						destroy.add(destroyCheck);
						q.add(new int[] {nr,nc,location[2]+1});
					}
					
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		tokens = new StringTokenizer(input.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		map=new int[N][M];
		visited=new boolean[N][M][2]; // 0: 벽 파괴한 적 X, 1: 벽 파괴한  O. 벽을 파괴하고 지나간 자리와 파괴하지 않고 지니가는 자리가 겹칠 수 있기 때문에 따로 방문처리
		
		for(int r=0;r<N;r++) {
			String[] data = input.readLine().split("");
			for(int c=0;c<M;c++) map[r][c]=Integer.parseInt(data[c]);
		}
		bfs(0,0);
		if(result==0) System.out.println(-1);
		else System.out.println(result);
	}

}