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
