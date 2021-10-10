#pypy3로 설정하여야 됨
import sys
input = sys.stdin.readline
r, c = map(int, input().split())
g=[list(input().strip()) for _ in range(r)]
result = 1
dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]
ch = [0] * 26

def dfs(x,y,count):
    global result
    result = max(result,count)

    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]

        if (0<=nx<r) and (0<=ny<c) and ch[ord(g[nx][ny])-ord('A')]==0:
            ch[ord(g[nx][ny])-ord('A')] = 1
            dfs(nx,ny,count+1)
            ch[ord(g[nx][ny])-ord('A')] = 0
ch[ord(g[0][0])-ord('A')]=1
dfs(0,0,result)
print(result)