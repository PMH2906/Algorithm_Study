import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ20061_모노미노도미노2 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int N, blue[][], green[][], score=0,remainCnt=0;
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(input.readLine());
		blue = new int[4][10];
		green = new int[10][4];
		
		for(int n=0;n<N;n++) {
			tokens = new StringTokenizer(input.readLine());
			int t=Integer.parseInt(tokens.nextToken());
			int x=Integer.parseInt(tokens.nextToken());
			int y=Integer.parseInt(tokens.nextToken());
			moveBloack(t,x,y);
			remove();
			special();
		}
		count();
		System.out.println(score+"\n"+remainCnt);
	
	}
	
	// 남은 블록 갯수
	private static void count() {
		for(int i=6;i<10;i++) {
			for(int j=0;j<4;j++) {
				if(blue[j][i]==1) remainCnt++;
				if(green[i][j]==1) remainCnt++;
			}
		}
	}
	
	// 특별한 칸 블록 
	private static void special() {
		int cnt=0;
		// blue의 특별한 칸 블록 갯수 확인 
		for(int i=4;i<6;i++) {
			for(int j=0;j<4;j++) {
				if(blue[j][i]==1) {
					cnt++;
					break;
				}
			}
		}
		// 갯수 만큼 열 삭제 
		if(cnt>0) {
			for(int i=9-cnt;i>3;i--) {
				for(int j=0;j<4;j++) {
					blue[j][i+cnt]=blue[j][i];
					blue[j][i]=0;
				}
			}
		}
		// green의 특별한 칸 블록 갯수 확인 
		cnt=0;
		for(int i=4;i<6;i++) {
			for(int j=0;j<4;j++) {
				if(green[i][j]==1) {
					cnt++;
					break;
				}
			}
		}
		// 갯수 만큼 행 삭제 
		if(cnt>0) {
			for(int i=9-cnt;i>3;i--) {
				for(int j=0;j<4;j++) {
					green[i+cnt][j]=green[i][j];
					green[i][j]=0;
				}
			}
		}
		
	}
	private static void remove() {
		
		// blue 
		// 모든 칸이 블록으로 가득찬 행 혹은 열 구하기 
		int index = 0; // 사라진 행 혹은 열 중 가장 마지막 행 혹은 열 구하기 
		int cnt = 0; // 사라진 행 혹은 열 갯수 
		for(int i=6;i<10;i++) {
			for(int j=0;j<4;j++) {
				if(blue[j][i]!=1) break;
				if(j==3) {
					index=i;
					cnt+=1;
				}
			}
		}
		if(index>0) {
			// 사라진 가장 마지막 열 기준으로 사라진 열 갯수만큼을 뺀 열부터 blue영역까지 앞 열을 탐색 
			for(int i=index-cnt;i>3;i--) { 
				for(int j=0;j<4;j++) {
					blue[j][i+cnt]=blue[j][i]; // 탐색한 앞 열을 사라진 열로 가져오기 
					blue[j][i]=0;
				}
			}
		}
		score+=cnt; // 점수 증가 
		
		// green : blue와 동일한 논리 
		index = 0;
		cnt = 0;
		for(int i=6;i<10;i++) {
			for(int j=0;j<4;j++) {
				if(green[i][j]!=1) break;
				if(j==3) {
					index=i;
					cnt+=1;
				}
			}
		}
		if(index>0) {
			for(int i=index-cnt;i>3;i--) {
				for(int j=0;j<4;j++) {
					green[i+cnt][j]=green[i][j];
					green[i][j]=0;
				}
			}
		}
		score+=cnt;
	}
	// 들어온 블론 옮기기 
	private static void moveBloack(int t, int x, int y) {
		// 블록이 있는지 확인하면서 놓일 곳 찾기  
		if(t==1) {
			int blueCol=y;
			while(blueCol<9&&blue[x][blueCol+1]!=1) {
				blueCol++;
			}
			int greenRow=x;
			while(greenRow<9&&green[greenRow+1][y]!=1) {
				greenRow++;
			}
			blue[x][blueCol]=1;
			green[greenRow][y]=1;
		}
		else if(t==2) {
			int blueCol=y+1;
			while(blueCol<9&&blue[x][blueCol+1]!=1) {
				blueCol++;
			} 
			int greenRow=x;
			while(greenRow<9&&green[greenRow+1][y]!=1&&green[greenRow+1][y+1]!=1) {
				greenRow++;
			}
			
			blue[x][blueCol]=1;
			blue[x][blueCol-1]=1;
			green[greenRow][y]=1;
			green[greenRow][y+1]=1;
		}
		else if(t==3) {
			int blueCol=y;
			while(blueCol<9&&blue[x][blueCol+1]!=1&&blue[x+1][blueCol+1]!=1) {
				blueCol++;
			} 
			int greenRow=x+1;
			while(greenRow<9&&green[greenRow+1][y]!=1) {
				greenRow++;
			}
			blue[x][blueCol]=1;
			blue[x+1][blueCol]=1;
			green[greenRow][y]=1;
			green[greenRow-1][y]=1;
		}
	}

}
