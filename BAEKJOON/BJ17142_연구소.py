from collections import deque
from itertools import combinations

n, m = map(int, input().split())

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def solution(map, virus, empty, visited):
    time = 0
    count = 0

    for v in virus:
        map[v[0]][v[1]] = 0
        visited[v[0]][v[1]] = True
    while virus:
        # virus는 m개의 위치에서 부터 차례대로 상하좌우 확인 후 q 삽입.
        # 따라서 모든 활성화바이러스의 위치부터 상하좌우 보고 계산 후, 삽입한 q를 동일하게 확인
        now = virus.popleft()
        x, y = now[0], now[1]

        for i in range(4):
            nx, ny = x+dx[i],y+dy[i]
            if nx<0 or nx>n-1 or ny<0 or ny>n-1:
                continue
            if visited[nx][ny] == False:
                if graph[nx][ny] != 1:
                    map[nx][ny]=map[x][y]+1 # 벽 제외 빈 공간은 0부터 증가
                    virus.append((nx,ny))
                    if graph[nx][ny] == 0:
                        count+=1 # 빈칸 갯수
                        time = map[x][y]+1 #시간 측정

                    visited[nx][ny] =True

                if empty == count :
                    break
    if count < empty: # 모든 큐를 다 봤지만 빈칸이 있다는 것은 바이러스를 모든 자리에 버트리지 못 함
        return -1
    return time


graph = []

empty = 0

virus_all = []
for i in range(n):
    temp = list(map(int, input().split()))
    for j in range(n):
        if temp[j] == 2:
            virus_all.append((i,j))
        elif temp[j] == 0:
            empty += 1
    graph.append(temp)

answer = 2501

# 활성화 바이러스 위치 조합을 사용해서 선택
# m개의 바이러스 선택한 경우 하나씩 확인
for case in combinations(virus_all, m):
    virus = deque()
    visited = [[False] * n for _ in range(n)]
    map=[[-1] *n for _ in range(n)]

    #활성화바이러스 위치 추가
    for c in case:
        virus.append(c)

    s = solution(map, virus, empty, visited)
    
    #모든 활성화 바이러스 위치에서 감염되지 않은 공간이 있으면 continue로 인해 
    #answer에 삽입되는 수가 없으므로 앞에 선언된 수 그대로의 값을 가짐
    if s == -1:
        continue
    answer = min(answer,s)
 

if answer == 2501:
    print(-1)
else:
    print(answer)





