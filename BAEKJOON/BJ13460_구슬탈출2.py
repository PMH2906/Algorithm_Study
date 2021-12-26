from collections import deque
import sys
input = sys.stdin.readline

n, m = map(int,input().split())

data = [list(input()) for _ in range(n)]

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

# R, B 좌표 찾기
for i in range(n):
    for j in range(m):
        if data[i][j]=='R':
            data[i][j]=='.'
            x_R, y_R = i, j
        elif data[i][j]=='B':
            data[i][j]=='.'
            x_B , y_B  = i, j

def bfs(x_R, y_R, x_B, y_B):
    q = deque()
    q.append((x_R, y_R, x_B, y_B))
    visited = []
    visited.append((x_R, y_R, x_B, y_B))
    count = 0
    while q:
        for _ in range(len(q)):
            x_R, y_R, x_B, y_B = q.popleft()
            if count>10: #움직인 횟수 10회 초과시 -1 출력
                print(-1)
                return
            if data[x_R][y_R] == 'O':
                print(count)
                return 
            for i in range(4):
                nx_R, ny_R, nx_B, ny_B = x_R, y_R, x_B, y_B
                while True:
                    nx_R += dx[i]
                    ny_R += dy[i]
                    if data[nx_R][ny_R]=='#':
                        nx_R -= dx[i]
                        ny_R -= dy[i]
                        break
                    if data[nx_R][ny_R]=='O':
                        break
                while True:
                    nx_B += dx[i]
                    ny_B += dy[i]
                    if data[nx_B][ny_B]=='#':
                        nx_B -= dx[i]
                        ny_B -= dy[i]
                        break
                    if data[nx_B][ny_B]=='O':
                        break
                if data[nx_B][ny_B] == 'O':
                    continue
                if nx_R == nx_B and ny_R == ny_B:
                    if abs(nx_R-x_R) + abs(ny_R - y_R) > abs(nx_B - x_B) + abs(ny_B - y_B):
                        nx_R -= dx[i]
                        ny_R -= dy[i]
                    else :
                        nx_B -= dx[i]
                        ny_B -= dy[i]

                if(nx_R, ny_R, nx_B, ny_B) not in visited:
                    q.append((nx_R, ny_R, nx_B, ny_B))
                    visited.append((nx_R, ny_R, nx_B, ny_B))
        count+=1
    print(-1)

bfs(x_R, y_R, x_B, y_B)
    

