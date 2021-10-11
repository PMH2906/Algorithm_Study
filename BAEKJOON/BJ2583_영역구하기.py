import sys
from collections import deque
input = sys.stdin.readline

dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]

n, m, k = map(int, input().split()) #행/ 열/ 직사각형 갯수
g = [[0]*m for _ in range(n)]

for i in range(k):
    a, b, c, d = map(int, input().split())
    for col in range(a,c):
        for row in range(b,d):
            g[row][col] = 1

def bfs(x,y,count):
    q = deque()
    q.append((x,y))
    g[x][y] = 1
    count+=1

    while q:
        x,y = q.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if 0<=nx<n and 0<=ny<m and g[nx][ny] == 0:
                count += 1
                g[nx][ny] = 1
                q.append((nx,ny))
    return count

result = 0
all = []
for i in range(n):
    for j in range(m):
        count = 0
        if g[i][j] == 0:
            all.append(bfs(i,j,count))
            result += 1
print(result)
all.sort()
for i in all:
    print(i, end = ' ')