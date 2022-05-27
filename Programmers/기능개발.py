from collections import deque
def solution(progresses, speeds):
    answer = []
    day = deque()
    
    # 배포 가능 날짜 계산
    for i in range(len(progresses)):
        if (100-progresses[i])%speeds[i] == 0:
            day.append((100-progresses[i])//speeds[i])
        else:
            day.append(((100-progresses[i])//speeds[i])+1)
    while day:
        count=1
        now_day = day.popleft()

         # 1. 현재 기능과 현재 기능보다 빨리 완성된 기능의 총 갯수(count)를 세어주고 answer에 추가. 
         # why? 현재 기능보다 빨리 완성되었으나 현재 기능이 가장 먼저 배포되어야하므로 해당 날짜에 위의 모든 기능 배포 가능
         # 2. 현재 기능보다 배포하는데 시간이 더 필요한 기능을 만난다면 해당 기능 기준으로 1 과정 반복.
        if day:
            while now_day >= day[0]: # 현재 기능보다 나중에 배포되어야 할 기능이 빨리 완성될 경우 count해주고, 데이터 제거
                count+=1 
                day.popleft() 
                if not day: # 만약 day 리스트에 데이터 없으면 종료
                    break
        answer.append(count) #  현재 기능과 현재 기능보다 빨리 완성된 기능의 총 갯수를 answer에 추가.
        
    return answer