import java.util.*;
import java.io.*;

//16가지 mbti가 있을때 k(k>16)명이 있으면 이 중 같은 mbti를 가지는 사람은 반드시 두 명 이상이다.
//k>32이면 같은 mbti를 가지는 사람이 반드시 세 명 이상 있다는 것을 알 수 있다.
//그렇다면 사람이 32명 넘게 있을 때는 무조건 답이 0이 된다.



public class BJ20529_가장가까운세사람의심리적거리
{
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder output = new StringBuilder();
    static int N, T, mbti[][];
    public static void main(String args[]) throws IOException
    {
        T = Integer.parseInt(input.readLine());

        for(int t=0;t<T;t++){
            N = Integer.parseInt(input.readLine());
            
            tokens = new StringTokenizer(input.readLine());
            
            if(N>32) {
                output.append(0+"\n");
                continue;
            }

            mbti = new int[N][4];


            for(int n=0;n<N;n++){
                String data = tokens.nextToken();
                if(data.charAt(0)=='E') mbti[n][0]=1;
                if(data.charAt(1)=='S') mbti[n][1]=1;
                if(data.charAt(2)=='T') mbti[n][2]=1;
                if(data.charAt(3)=='J') mbti[n][3]=1;
            }

            output.append(distance()+"\n");
        }
        System.out.println(output);

    }
    private static int distance(){
        int minDist = Integer.MAX_VALUE;

        for(int i=0;i<N-2;i++){
            for(int j=i+1;j<N-1;j++) {
                for(int z=j+1;z<N;z++){
                    int dist = 0;
                    for(int idx=0;idx<4;idx++){
                        dist+=Math.abs(mbti[i][idx]-mbti[j][idx]);
                        dist+=Math.abs(mbti[j][idx]-mbti[z][idx]);
                        dist+=Math.abs(mbti[z][idx]-mbti[i][idx]);
                    }
                    minDist = Math.min(minDist, dist);
                }
            }
        }
        return minDist;
    }
}