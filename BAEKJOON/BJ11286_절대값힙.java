import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ11286_절대값힙 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder output = new StringBuilder();
    static int N;
    static PriorityQueue<Num> pq = new PriorityQueue<>();
    
    static class Num implements Comparable<Num>{
    	long n;
    	
    	public Num(long n) {
    		this.n = n;
    	}
    	
		@Override
		public int compareTo(Num o) {
			if(Math.abs(this.n)==Math.abs(o.n)) {
				return Long.compare(this.n, o.n);
			}
			
			return Long.compare(Math.abs(this.n),Math.abs(o.n));
		}
    	
    }
    
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(input.readLine());
		
		for(int n=0;n<N;n++) {
			long x = Integer.parseInt(input.readLine());
			
			if(x!=0) {
				pq.add(new Num(x));
			}else {
				if(pq.size()==0) output.append(0+"\n");
				else output.append(pq.poll().n+"\n");
			}
		}
		System.out.println(output);
	}

}
