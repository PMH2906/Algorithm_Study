import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_3040_백설공주와일곱난쟁이 {
	static StringBuilder output = new StringBuilder();
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int[] caps=new int[9];
	
	public static void findNanjang(int nth, int[] choosed, int startIdx) {
		if(nth==choosed.length) {
			int sum=0;
			for(int i=0;i<choosed.length;i++) sum+=choosed[i];
			if(sum==100) {
				for(int i=0;i<choosed.length;i++) System.out.println(choosed[i]);
				System.exit(0);
			}
			return;
		}
		for(int i=startIdx;i<caps.length;i++) {
			choosed[nth]=caps[i];
			findNanjang(nth+1,choosed,i+1);
		}
	}
	
	public static void main(String[] args) throws Exception {

		for(int i=0;i<9;i++) caps[i] = Integer.parseInt(input.readLine());
		
		findNanjang(0,new int[7], 0);
		
	}
}
