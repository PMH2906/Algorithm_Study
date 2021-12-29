import math
from itertools import permutations

def check(n):
    k = math.sqrt(n)
    if n<2:
        return False
    for i in range(2, int(k)+1):
        if n%i == 0 :
            return False
    return True
# permutations를 이용해서 모든 경우의 수를 만들고 판정.
# permutations(iterable, r=None) : iterable요소의 길이r에 해당하는 순열 생성
# 단, 여기서 중복되는 수가 발생해서 set으로 미리 줄이고 하면 속도가 많이 빨라짐
# map은 리스트의 요소를 지정된 함수로 처리해주는 함수
# '구분자'.join함수(리스트)
def solution(numbers):
    answer=[]
    for i in range(1,len(numbers)+1):
        perlist = list(map(''.join, permutations(list(numbers),i)))
        for j in list(set(perlist)):
            if check(int(j)):
                answer.append(int(j))
    answer = len(set(answer))
    return answer