from collections import deque
import sys
input = sys.stdin.readline
dx = [1, -1, 0, 0]
dy = [0, 0, -1, 1]
n = int(input())
data = [list(map(int,input().split())) for _ in range(n)]

def bfs(x,y,h):
    q = deque()
    q.append((x,y))
    visited[x][y] = 1

    while q:
        x, y = q.popleft()

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if 0<=nx<n and 0<=ny<n:
                if data[nx][ny] > h and visited[nx][ny] == 0:
                    visited[nx][ny] = 1
                    q.append((nx,ny))

result = 0

for i in range(max(map(max,data))):
    count = 0
    visited = [[0]*n for _ in range(n)]
    for j in range(n):
        for z in range(n):
            if visited[j][z] == 0 and data[j][z]>i:
                bfs(j,z,i)
                count += 1
    result = max(result,count)
print(result)