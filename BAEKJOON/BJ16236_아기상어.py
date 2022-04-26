# # 내 코드 => 문제점 : 거리가 가장 짧은 위치가 여러개이면 가장 윗줄, 가장 왼쪽에 위치하는 곳으로 가야하는데 불가능
# # line 48 추가해서 문제점 고침
from collections import deque
# n = int(input())
# # sea = [list(map(int,input().split())) for _ in range(n)]
# dx = [-1, 0, 0, 1]
# dy = [0, -1, 1, 0]
# sea=[]
# input_data=[]
# shark = deque()
# shark_size = 2
# x, y = 0, 0
# # check = False
# answer=0

# for i in range(n):
#     input_data = list(map(int,input().split()))
#     sea.append(input_data)
#     for j in range(n):
#         if input_data[j] == 9:
#             x, y = i, j

# def dfs(x, y, shark_size, visited):
#     global answer
#     shark.append((x,y))
#     visited[x][y]= True
#     sea[x][y] = 0
#     check = False
#     short_list = []
#     while shark:
#         x,y=shark.popleft()
#         for i in range(4):
#             nx = x+dx[i]
#             ny = y+dy[i]
#             if nx<0 or nx>n-1 or ny<0 or ny>n-1:
#                 continue
#             if (sea[nx][ny] == 0  or sea[nx][ny] == shark_size )and visited[nx][ny]==False:
#                 shark.append((nx,ny))
#                 visited[nx][ny] = True
#                 time[nx][ny]=time[x][y]+1
#             elif sea[nx][ny] <shark_size and sea[nx][ny] != 0 and visited[nx][ny]==False:
#                 # sea[nx][ny] = 0
#                 visited[nx][ny] = True
#                 time[nx][ny] = time[x][y] + 1
#                 short_list.append((time[nx][ny],nx,ny))
#                 # answer += time[nx][ny]
#                 check = True
#                 # break
#     if check == True:
#         short_list.sort()
#         answer += time[short_list[0][1]][short_list[0][2]]
#         sea[short_list[0][1]][short_list[0][2]]=0
#         nx=short_list[0][1]
#         ny=short_list[0][2]
#         shark.clear()
#         short_list.clear()

#     return (nx, ny, check)

# while True:

#     for _ in range(shark_size):
#         visited = [[False] * n for _ in range(n)]
#         time = [[0] * n for _ in range(n)]
#         x, y, check = dfs(x, y, shark_size, visited)
#         if check == False:
#             break
#     shark_size+=1

#     if check == False:

#         print(answer)
#         break


# 다른 분 답
# 매 탐색마다 "모든" 물고기까지 거리를 구할 필요 없다. 시간 초과난다.
# 물고기를 한 번이라도 만나면 무조건 그 물고기 까지가 최단거리이다.
# 만약 최단거리가 동일한 물고기가 2마리 이상이면, X작은순, Y작은순으로 정렬하고, 맨 앞 물고기 먹는다.

from unittest import result


x,y = 0,0
shark_size = 2
eat_cnt = 0
fish_cnt = 0
fish_pos = []
input_data=[]
sea = []
time = 0

dx = (0,0,1,-1)
dy = (1,-1,0,0)

n = int(input())


for i in range(n):
    input_data = list(map(int,input().split()))
    sea.append(input_data)
    for j in range(n):
        if input_data[j] == 9:
            x, y = i, j
        elif 0< input_data[j] <= 6:
            fish_cnt+=1
            fish_pos.append((i,j))
sea[x][y] = 0 # 시작 위치
        
def bfs(x,y):
    q=deque([(x,y,0)]) #q선언과 동시에 초기화
    dist_list=[]
    visited = [[False]*n for _ in range(n)]
    visited[x][y] = True
    min_dist=int(1e9)
    while q:
        x,y,dist = q.popleft()
        for i in range(4):
            nx=x+dx[i]
            ny=y+dy[i]

            if nx<0 or nx>n-1 or ny<0 or ny>n-1:
                continue
            if sea[nx][ny] <= shark_size and not visited[nx][ny]:
                visited[nx][ny] = True
                if 0<sea[nx][ny]<shark_size:
                    min_dist=dist
                    dist_list.append((dist+1,nx,ny))

                # sea[nx][ny] <= shark_size 인 부분 모두 거리구하고 q 삽입됨
                if dist+1<=min_dist:
                    q.append((nx,ny,dist+1))
    if dist_list:
        dist_list.sort()
        return dist_list[0]
    else:
        return False


result = False

while fish_cnt:
    result = bfs(x,y)
    if not result:
        break
    x,y=result[1],result[2] # 최단 거리와 조건에 맞는 x,y
    time+= result[0]
    eat_cnt+=1
    fish_cnt-=1
    if shark_size == eat_cnt: # 상어 크기와 먹은 물고기의 갯수가 같다면
        shark_size+=1 # 사이즈 증가
        eat_cnt = 0
    sea[x][y]=0

print(time)
