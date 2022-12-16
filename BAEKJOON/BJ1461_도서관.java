import java.util.*;
import java.io.*;

public class BJ1461_도서관 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, M, dist;
    static List<Integer> minus = new ArrayList<>();
    static List<Integer> plus = new ArrayList<>();
    public static void main(String args[]) throws Exception
    {
        tokens = new StringTokenizer(input.readLine());

        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        tokens = new StringTokenizer(input.readLine());
        plus.add(0);
        minus.add(0);
        for(int i=0;i<N;i++){
            int num = Integer.parseInt(tokens.nextToken());
            if(num>0) {
                plus.add(num);
            }else{
                minus.add(-1*num);
            }
        }
        
        Collections.sort(plus,Comparator.reverseOrder());
        Collections.sort(minus, Comparator.reverseOrder());

        if(plus.get(0)>minus.get(0)){
            dist += plus.get(0);
            int index=M;
            while(index<=plus.size()-1){
                dist+=plus.get(index)*2;
                index+=M;
            }
            index=0;
            while(index<=minus.size()-1){
                dist+=minus.get(index)*2;
                index+=M;
            }
        } else {
            dist += minus.get(0);
            int index=M;
            while(index<=minus.size()-1){
                dist+=minus.get(index)*2;
                index+=M;
            }
            index=0;
            while(index<=plus.size()-1){
                dist+=plus.get(index)*2;
                index+=M;
            }
        }
        System.out.println(dist);
    }

}
