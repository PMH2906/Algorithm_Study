import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Array_5_4 {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static List<Integer> array = new ArrayList<>();
    static StringTokenizer token;

    public static void main(String[] args) throws IOException {

        token = new StringTokenizer(input.readLine());

        while (token.hasMoreTokens()) array.add(Integer.parseInt(token.nextToken()));

        System.out.println(canReach(0));
    }

    // 움직일 수 있는 경우의 수 완전 탐색하여 마지막 인텍스에 도달하는지 확인하는 로직
    private static boolean canReach(int index) {

        // 마지막 위치에 도달하면 true 반환
        if (index==array.size()-1) return true;

        // 마지막 위치를 넘거나 현재 위치의 값이 0일 경우 false 반환
        if (index>array.size()-1 || array.get(index)==0) return false;

        // 최대로 움직일 수 있는 횟수
        int canMoved = array.get(index);

        // 움직일 수 있는 만큼 재귀함수 호출
        while (canMoved>0) {

            // 만약 true가 반환되면 더 이상 재귀함수 호출하지 않고 멈추기
            if(canReach(index+canMoved)) return true;

            // 움직일 수 있는 횟수 --
            canMoved--;
        }

        return false;
    }
}
