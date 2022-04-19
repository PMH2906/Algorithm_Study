from collections import deque

n, m = map(int, input().split())

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def bfs(x_R, y_R, x_B, y_B):
    q=deque()
    q.append((x_R, y_R, x_B, y_B))
    visited =[]
    visited.append((x_R, y_R, x_B, y_B))
    depth =0
    while q:
        for _ in range(len(q)): # 상, 하, 좌, 우로 벽에 부딪힐 때의 모든 경우를 다시 확인
            x_R, y_R, x_B, y_B = q.popleft()
            # print(x_R, y_R, x_B, y_B)
            if depth > 10 : #움직인 횟수가 10 이상이면 -1 출력
                print(-1)
                return
            if data[x_R][y_R] == 'O': #현재 빨간 구슬의 위치가 구멍이라면 depth출력 
                print(depth)
                return 
            for i in range(4): 
                nx_R, ny_R = x_R, y_R
                while True: # 빨간 구슬이 #일 때까지 혹은 구멍일 때 까지 움직인 경우, 상, 하, 좌, 우 따로 계산에서 큐에 삽입됨
                    nx_R += dx[i]
                    ny_R += dy[i]
                    if data[nx_R][ny_R] == 'O':
                        break
                    if data[nx_R][ny_R] == '#':
                        nx_R -=dx[i]
                        ny_R -=dy[i]
                        break
                nx_B, ny_B = x_B, y_B
                while True: # 파란 구슬이 #일 때까지 혹은 구멍일 때 까지 움직인 경우, 상, 하, 좌, 우 따로 계산에서 큐에 삽입됨
                    nx_B += dx[i]
                    ny_B += dy[i]
                    if data[nx_B][ny_B] == 'O':
                        break
                    if data[nx_B][ny_B] == '#':
                        nx_B -=dx[i]
                        ny_B -=dy[i]
                        break

                if data[nx_B][ny_B] == 'O': #파란 구슬이 먼저 구멍에 들어가거나 동시에 들어가면 안됨. 따라서 이 경우 무시
                    continue
                if nx_R == nx_B and ny_R == ny_B: # 두 구슬의 위치가 같다면
                    if abs(x_R-nx_R)+abs(y_R-ny_R) > abs(x_B-nx_B)+abs(y_B-ny_B): # 더 많이 이동한 구슬이 나중에 도착하므로 먼저 도착하는 구슬 보다 뒤에 위치
                        nx_R, ny_R = nx_R-dx[i], ny_R-dy[i]
                    else:
                        nx_B, ny_B = nx_B-dx[i], ny_B-dy[i]

                if (nx_R, ny_R,nx_B, ny_B) not in visited: # 방문해본적이 없는 위치라면 새로 큐에 삽입
                    q.append((nx_R, ny_R,nx_B, ny_B))
                    visited.append((nx_R, ny_R,nx_B, ny_B))
        depth+=1
    print(-1) # 10최가 초과하지 않았지만 10회 내에도 구멍에 못 들어갈 경우

data = []

for i in range(n):
    data.append(list(input()))
    for j in range(m):
        if data[i][j]=='R':
            x_R, y_R = i,j
            data[i][j] = '.'
        elif data[i][j]=='B':
            x_B, y_B = i,j
            data[i][j] = '.'
bfs(x_R, y_R, x_B, y_B)

