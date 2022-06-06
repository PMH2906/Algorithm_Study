import sys
input=sys.stdin.readline # input 속도 향상

# 팩토리얼 함수(재귀함수)
def fact(n):
    if n<=1:
        return 1
    return n*fact(n-1)

while True:
    answer = 0 # 결과
    count=1 # 자릿수
    num = int(input()) # 숫자 입력

    if num == 0 : # 0 입력 시 종료
        break
    while True:
        if num <= 0:
            break
        # 나머지 값을 사용하여 일의 자리 수 부터 계산
        answer += fact(count)*(num%10) 
        # 자릿 수 올려주기
        count+=1
        # 몫 구하고 위의 과정 반복
        num=num//10
            
    print(answer)