from collections import deque
def check(s, begin): #한 문자만 다른지 check하는 함수
    answer = 0
    for i in range(len(s)):
        if begin[i] != s[i]:
            answer+=1
    return True if answer == 1 else False #문법 중요

def solution(begin, target, words):
    if target not in words:
        return 0
    q = deque()
    q.append([begin,[]]) #q.append(시작단어 혹은 바뀐 단어, 바꿔진 과정을 담는 list)
    
    while q :
        n, l = q.popleft()
        for word in words:
            if word not in l and check(word, n):
                if word == target:
                    return len(l)+1 #만약 word==target이면 바꿔진 과정에 +1(why?마지막 word==target인 경우의 word도 포함 )
                temp = l[0:] # word!=target이면 바꿔진 과정인 list에
                temp.append(word) # 현재 단어도 추가해서 
                q.append([word,temp])  #q에 넣어주기(현재 바뀐 단어, 바꿔진 과정이 담긴 list)
    return 0