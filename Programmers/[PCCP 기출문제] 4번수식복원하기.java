import java.util.*;
class Solution {
    static Queue<Integer> num;
    static List<String> question;
    public String[] solution(String[] expressions) {

        num=new LinkedList<>();
        question=new ArrayList<>();

        for(int n=2;n<=9;n++) num.add(n);

        for(String expression : expressions) {
            if(expression.contains("X")) {
                question.add(expression);
            }

            String[] splitExpression=expression.split(" ");
            int num1=Integer.parseInt(splitExpression[0]);
            int num2=Integer.parseInt(splitExpression[2]);

            int size=num.size();
            for(int i=0;i<size;i++) {
                int n=num.poll();

                // 각 조건에 만족하는 n은 다시 num에 넣어주기
                // 조건에 만족하지 않는 n은 제거
                if(expression.contains("X")) {
                    if(check(n, num1)&&check(n, num2)) num.add(n);
                }
                else {
                    int num3=Integer.parseInt(splitExpression[4]);
                    check(n, num1, num2, num3, splitExpression[1]);
                }
            }
        }

        String[] answer = new String[question.size()];
        Set<Integer> set=new HashSet<>();
        for(int i=0;i<question.size();i++) {
            String q=question.get(i);
            set.clear();
            int size=num.size();

            String[] splitExpression=q.split(" ");
            int num1=Integer.parseInt(splitExpression[0]);
            int num2=Integer.parseInt(splitExpression[2]);
            String oper=splitExpression[1];

            for(int j=0;j<size;j++) {
                int n=num.poll();

                int result=0;
                if(oper.equals("+"))  {
                    // 10진수 변환 후 덧셈 -> n진수 변환
                    set.add(change(change10(num1,n)+change10(num2,n), n));
                }
                else {
                    // 10진수 변환 후 뺄셈 -> n진수 변환
                    set.add(change(change10(num1,n)-change10(num2,n), n));
                }

                // 사용한 n은 다시 넣어주기
                num.add(n);
            }

            // 결과가 여러 개면 ?로 하나면 답으로 반환
            if(set.size()>1) {
                answer[i]=num1+" "+oper+" "+num2+" = ?";
            } else {
                for(Integer a: set) answer[i]=num1+" "+oper+" "+num2+" = "+a;
            }
        }

        return answer;
    }

    // num1+num2=num3 or num1-num2=num3을 만족하는지 확인하는 메서드
    public void check(int n, int num1, int num2, int num3, String oper) {

        if(!check(n, num1)||!check(n, num2)||!check(n, num3)) return;
        num3=change10(num3,n);
        num1=change10(num1,n);
        num2=change10(num2,n);

        if(oper.equals("+"))  {
            if(num3==num1+num2) num.add(n);
        }
        else {
            if(num3==num1-num2) num.add(n);
        }
    }

    // num1이 n진수가 가능한지 확인하는 메서드
    // 각 자릿수가 n진수를 넘어가면 false, 아니면 true
    public boolean check(int n, int num1) {

        int idx=10;
        while(num1>0) {
            if(num1%idx>=n) return false;
            num1/=idx;
        }

        return true;
    }

    // n진수로 변환
    public static int change(int num, int n) {

        int idx=1;
        int sum=0;

        while(num>=n) {
            sum+=((num%n)*idx);
            num/=n;
            idx*=10;
        }

        sum+=num*idx;

        return sum;
    }

    // 10 진수로 변환
    public static int change10(int num, int n) {

        int idx=10;
        int pow=0;
        int sum=0;
        while(num>0) {

            sum+=(num%idx)*Math.pow(n,pow);
            num/=idx;
            pow+=1;
        }

        return sum;
    }
}