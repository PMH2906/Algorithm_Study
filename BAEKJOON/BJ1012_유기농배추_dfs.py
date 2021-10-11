import sys
sys.setrecursionlimit(10000)
t = int(input())

dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]

def dfs(x,y):
    visited[x][y] = 1

    for i in range(4):
        nx = x+dx[i]
        ny = y+dy[i]

        if (0<=nx<m) and (0<=ny<n) and (visited[nx][ny] == 0) and (g[nx][ny] == 1):
            dfs(nx,ny)

for _ in range(t):   
    count = 0
    m, n, k = map(int, input().split())
    g=[[0]*n for _ in range(m)]
    visited=[[0]*n for _ in range(m)]

    for _ in range(k):
        a, b = map(int, input().split())
        g[a][b] = 1

    for i in range(m):
        for j in range(n):
            if visited[i][j] == 0 and g[i][j] == 1:
                dfs(i,j) #dfs로 뭉쳐져 있는 덩어리를 모두 다 방문하고 와서 visited를 1로 만든다.
                count+=1
    print(count)
