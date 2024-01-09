import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Array_5_6 {

    static List<Integer> array = new ArrayList<>();
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    public static void main(String[] args) throws IOException {

        tokens = new StringTokenizer(input.readLine());

        while (tokens.hasMoreTokens()) array.add(Integer.parseInt(tokens.nextToken()));

        System.out.println(findMaxProfit(array));
    }

    private static int findMaxProfit(List<Integer> array) {
        int minValue = Integer.MAX_VALUE;
        int maxProfit = Integer.MIN_VALUE;

        for(int i=0;i<array.size();i++) {

            // 현재까지 나온 가장 최저가로 갱신
            minValue = Math.min(array.get(i), minValue);

            // 가장 최저가와 현재 가격을 빼고
            // 지금까지 나온 값과 해당 값을 비교하여 최대 이윤으로 갱신
            maxProfit = Math.max(array.get(i)-minValue, maxProfit);
        }

        return maxProfit;
    }
}
