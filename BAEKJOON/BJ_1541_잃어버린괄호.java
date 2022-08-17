import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

/**

@author 박미희
@since 2022. 8. 17.
@see
@git
@youtube
@performance
@category #
@note */
public class BJ_1541_잃어버린괄호{
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static String[] data;
	static int result=0,nth=0,sum=0,num=0;
	
	public static void main(String[] args) throws IOException {
	
		data=input.readLine().split("");
		
		for(int i=data.length-1;i>=-1;i--) {
			if(i==-1) {
				sum+=num;
				result+=sum;
				break;
			}
			if(data[i].equals("+")) {
				nth=0;
				sum+=num;
				num=0;
				continue;
			}
			if(data[i].equals("-")) {
				nth=0;
				result-=sum+num;
				num=0;sum=0;
				continue;
			}
			num+=Integer.parseInt(data[i])*Math.pow(10, nth);
			nth++;
		}
		System.out.println(result);
	}
	
}