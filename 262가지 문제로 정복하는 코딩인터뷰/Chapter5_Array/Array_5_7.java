import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Array_5_7 {

    static List<Integer> array = new ArrayList<>();
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    public static void main(String[] args) throws IOException {

        tokens = new StringTokenizer(input.readLine());

        while (tokens.hasMoreTokens()) array.add(Integer.parseInt(tokens.nextToken()));

        System.out.println(findMaxProfit(array));
    }

    private static int findMaxProfit(List<Integer> array) {

        int maxProfit = Integer.MIN_VALUE;

        // 주식을 두 번 사고 팔기
        // 1. 0 ~ i에서 주식을 한 번 살 때의 최대 이윤 구하기
        // 2. i+1 ~ size에서 주식을 한 번 살 때의 최대 이윤 구하기
        // 1번 + 2번 합계의 최대 이윤 구하기

        for(int i=1;i<array.size()-1;i++) {

            int minValueFirst = Integer.MAX_VALUE;
            int maxProfitFirst = Integer.MIN_VALUE;

            int minValueSecond = Integer.MAX_VALUE;
            int maxProfitSecond = Integer.MIN_VALUE;

            for(int j=0;j<=i;j++) {

                // 현재까지 나온 가장 최저가로 갱신
                minValueFirst = Math.min(array.get(j), minValueFirst);

                // 가장 최저가와 현재 가격을 빼고
                // 지금까지 나온 값과 해당 값을 비교하여 최대 이윤으로 갱신
                maxProfitFirst = Math.max(array.get(j)-minValueFirst, maxProfitFirst);
            }
            for(int j=i+1;j<array.size();j++) {

                // 현재까지 나온 가장 최저가로 갱신
                minValueSecond = Math.min(array.get(j), minValueSecond);

                // 가장 최저가와 현재 가격을 빼고
                // 지금까지 나온 값과 해당 값을 비교하여 최대 이윤으로 갱신
                maxProfitSecond = Math.max(array.get(j)-minValueSecond, maxProfitSecond);
            }

            maxProfit = Math.max(maxProfitFirst+maxProfitSecond, maxProfit);

        }

        return maxProfit;
    }
}
