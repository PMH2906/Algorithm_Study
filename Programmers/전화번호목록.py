
def solution(phone_book):
    answer = True
    phone_book.sort()
    for i in range(len(phone_book)-1):
        count=0
        for x in range(min(len(phone_book[i]),len(phone_book[i+1]))):
            if phone_book[i][x]==phone_book[i+1][x]:
                count+=1
        if count== len(phone_book[i]) or count == len(phone_book[i+1]):
            answer = False
            return answer
    return answer

"""
def solution(phoneBook):
    phoneBook = sorted(phoneBook)

    for p1, p2 in zip(phoneBook, phoneBook[1:]):
        if p2.startswith(p1):
            return False
    return True

startswith는 문자열이 특정문자로 시작하는지 여부를 알려준다 p2.startswith(p1)

find는 문자열중에 특정문자를 찾고 위치를 반환해준다 p2.find(p1) == 0

zip함수 함수는 여러 개의 순회 가능한(iterable) 객체를 인자로 받고, 
각 객체가 담고 있는 원소를 터플의 형태로 차례로 접근할 수 있는 반복자(iterator)를 반환 
for p1, p2 in zip(phone_book, phone_book[1:]) 
==> (phone_book[0], phone_book[1]) (phone_book[1], phone_book[2])...
"""