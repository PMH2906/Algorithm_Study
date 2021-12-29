from collections import deque
def solution(progresses, speeds):
    answer = []
    q = deque()
    for i in range(len(progresses)):
        if (100-progresses[i])%speeds[i] == 0:
            q.append((100-progresses[i])//speeds[i])
        else :
            q.append(((100-progresses[i])//speeds[i])+1) 
    
    result = 0

    while q:
        day = q.popleft()
        if q:
            while day >= q[0]:
                result+=1
                q.popleft()
                if not q:
                    break
        answer.append(result+1)
        result = 0
        
    return answer

print(solution([95, 90, 99, 99, 80, 99], [1, 1, 1, 1, 1, 1]))
