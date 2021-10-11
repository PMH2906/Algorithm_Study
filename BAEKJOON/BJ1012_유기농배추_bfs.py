import sys
from collections import deque
input = sys.stdin.readline

t = int(input())

dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]

def bfs(x,y):
    q = deque()
    q.append((x,y))
    g[x][y] = 0
    while q:
        x,y = q.popleft()

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if (0<=nx<m) and (0<=ny<n) and g[nx][ny] == 1:
                g[nx][ny] = 0
                q.append((nx, ny))

for _ in range(t):   
    count = 0
    m, n, k = map(int, input().split())
    g=[[0]*n for _ in range(m)]

    for _ in range(k):
        a, b = map(int, input().split())
        g[a][b] = 1

    for i in range(m):
        for j in range(n):
            if g[i][j] == 1:
                bfs(i,j)
                count += 1
    print(count)