def solution(numbers):
    #int형의 list를 map을 사용하여 string으로 치환한 뒤, list로 변환
    numbers = list(map(str, numbers))
    
    #lambda x : x * 3은 num 인자 각각의 문자열을 3번 반복한다는 뜻
    #num인자가 30이면 "303030" 뜻
    #문자열 비교는 아스키코드 값으로 치환되어 정렬
    #첫번째부터 비교. 즉 666, 303030 이면 6과 3 비교
    numbers.sort(key = lambda x : x*3, reverse = True)
    
    #int로 변환한 뒤, str로 변환하는 이유는 "000"을 "0"으로 처리하기 위해
    return str(int(''.join(numbers)))
print(solution([3, 30, 34, 5, 9]))

