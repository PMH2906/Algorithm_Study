from collections import deque

def solution(priorities, location):
    count=0
    q=deque(priorities)
    rank=deque([i for i in range(len(priorities))])
    while q:
        now = q.popleft()
        now_i = rank.popleft()
  
        if q and now < max(list(q)):
            q.append(now)
            rank.append(now_i)
        else:
            count+=1
            if now_i == location:
                answer = count
    return answer

print(solution([2, 1, 3, 2],2))