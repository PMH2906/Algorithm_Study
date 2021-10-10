#pypy3으로!!!

import sys
input = sys.stdin.readline
r, c = map(int, input().split())
g=[list(input().strip()) for _ in range(r)]
result = 1
dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]
visited_al= [g[0][0]]
def dfs(x,y,count):
    global result
    result = max(result,count)

    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]

        if (0<=nx<r) and (0<=ny<c) and(g[nx][ny] not in visited_al):
            visited_al.append(g[nx][ny])
            dfs(nx,ny,count+1)
            visited_al.remove(g[nx][ny])

dfs(0,0,result)
print(result)