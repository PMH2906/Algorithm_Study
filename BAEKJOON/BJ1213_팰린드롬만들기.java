import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ1213_팰린드롬만들기 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static char[] name, newName;
	static int alpha[] = new int[26], odd=0,even=0;
	public static void main(String[] args) throws IOException {
		name = input.readLine().toCharArray();
		newName = new char[name.length];
		
		for(int i=0;i<name.length;i++) { // 알파벳 갯수 
			alpha[name[i]-'A'] ++;
		}
		
		for(int i=0;i<26;i++) {
			if(alpha[i]%2==0) even ++; // 짝수 갯수 
			else odd ++; // 홀수 갯수 
		}
		
		if(name.length%2==0) { // 짝수 길이면 
			if(odd==0) { // 홀수의 갯수가 무조건 0이어야 팰린드롬 완성 가능 
				Arrays.sort(name);
				int nth=-1;
				for(int i=0;i<name.length/2;i++) { // newName 인덱스 
					newName[i]=name[++nth]; // 팰린드롬의 앞글자 
					newName[name.length-i-1] = name[++nth]; // 팰린드롬의 대칭되는 뒷 글자 
				}
				System.out.println(newName); 
				
			}else {
				System.out.println("I'm Sorry Hansoo");
			}
		}else { // 홀수 길이면 
			if(odd==1) { // 홀수의 갯수가 모조건 1이어야 팰린드롬 완성 가능 
				char oddAlpha='0'; 
				Arrays.sort(name);
				int nth=-1;
				for(int i=0;i<name.length/2;i++) { 
					if(alpha[name[nth+1]-'A']%2==1) { // 홀수의 갯수에 해당하는 알파벳은 
						alpha[name[nth+1]-'A']--; // 짝수의 갯수로 바꿔주고 
						oddAlpha = name[++nth]; // oddAlpha에 저장 
					}
					newName[i]=name[++nth]; // 팰린드롬의 앞글자 
					newName[name.length-i-1] = name[++nth];// 팰린드롬의 대칭되는 뒷 글자 
					
				}
				if(oddAlpha=='0') oddAlpha=name[name.length-1]; // oddAlpha가 여전히 0이면 맨 뒤의 알파벳을 탐색 못한것이므로, 맨 뒤 알파벳 넣어주기 
				newName[name.length/2]=oddAlpha; // 센터에 넣어주기 
				System.out.println(newName);
				
			}else {
				System.out.println("I'm Sorry Hansoo");
			}
		}
	
	}

}
