# Algorithm Study
개인 알고리즘 공부 공간


# 공부 진행💡🌱

* 이것이 코딩 테스트다 with 파이썬
	- 개념 정리
	- 목표 : 기출 문제 스스로 풀어보기

* 백준 
	- [알고리즘 분류] 에서, 각 분류별로 하나씩!
	- 하루 2문제 이상

* 프로그래머스

# 파이썬 문법
  *  startswith는 문자열이 특정문자로 시작하는지 여부를 알려준다
     p2.startswith(p1)
  
  *  find는 문자열중에 특정문자를 찾고 위치를 반환해준다
     p2.find(p1) == 0
  
  *  zip함수
  함수는 여러 개의 순회 가능한(iterable) 객체를 인자로 받고, 각 객체가 담고 있는 원소를 터플의 형태로 차례로 접근할 수 있는 반복자(iterator)를 반환
    for p1, p2 in zip(phone_book, phone_book[1:])
    ==> (phone_book[0], phone_book[1]) (phone_book[1], phone_book[2])...
  
  
  * permutations(from itertools import permutations)
    permutations를 이용해서 모든 경우의 수를 만들고 판정.
    permutations(iterable, r=None) : iterable요소의 길이r에 해당하는 순열 생성
    ex)프로그래머스-소수찾기
    
  * set함수
    중복되는 수가 발생시 삭제
    set(리스트)
    ex)프로그래머스-소수찾기
   
  * map
    map은 리스트의 요소를 지정된 함수로 처리해주는 함수
    map(함수, iterable)
    ex)프로그래머스-소수찾기
    
   * join 
     리스트를 구분자로 합쳐줌
     '구분자'.join함수(리스트)
     ex)프로그래머스-소수찾기
     
   * sort(key = lambda x : x값 처리, reverse = True)
     ex)프로그래머스-가장 큰 수
     
   * from collections import defaultdict
   
     routes = defaultdict(list)
     
     routes[ticket[0]].append(ticket[1])
     
     routes[ticket[0]].append(ticket[2])
     
     =>{'ticket[0]' : [ticket[1], ticket[2]
     ex)프로그래머스 - 여행경로
    
   

# 시간복잡도
   * 순차탐색 : n 
   * 이분탐색 : log(n) 만약 정렬 후 이분 탐색하면 nlog(n)
   	
   
