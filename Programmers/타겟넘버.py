def solution(numbers, target):
    n = len(numbers)
    answer=0
    
    def dfs(i, result):
        if i == n:
            if result == target:
                #nonlocal을 사용하는 이유는 위의 answer이 전역변수가 아닌 solution의 함수 내의 변수이기 때문.
                #global을 쓴다면 전역변수로 선언된 answer이 없기 때문에 에러 발생
                nonlocal answer 
                answer += 1
            return
        else:
            dfs(i+1,result+numbers[i])
            dfs(i+1,result-numbers[i])
            
    dfs(0,0)
    
    return answer