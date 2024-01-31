import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Array_5_5 {

    static List<Integer> array = new ArrayList<>();
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    public static void main(String[] args) throws IOException {

        tokens = new StringTokenizer(input.readLine());

        while (tokens.hasMoreTokens()) array.add(Integer.parseInt(tokens.nextToken()));

        System.out.println(removeDuplicate());
    }

    private static int removeDuplicate() {

        int index = 1; // 탐색 위치
        int fixIndex = 0; // 원소 넣는 위치
        int num = array.get(0); // 앞 숫자

        while (index<array.size()) {

            // 앞 숫자와 다르면 (중복 아닌 경우)
            if(num!=array.get(index)) {
                fixIndex++; // 원소 넣는 위치 증가
                num = array.get(index); // 현재 숫자로 갱신

                // 해당 숫자를 원소 넣는 위치에 넣어주기
                array.set(fixIndex, array.get(index));
            }

            index++;
        }

        return fixIndex+1;
    }
}
