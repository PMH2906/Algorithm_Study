import sys
sys.setrecursionlimit(10000) #재귀함수 한도 재설정
input = sys.stdin.readline
data = []
result = 0

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

n = int(input())
for _ in range(n):
    data.append(list(map(int, input().split()))) 

def dfs(x,y,h):
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]

        if(0 <= nx < n) and (0 <= ny < n):
            if data[nx][ny]>h and visited[nx][ny]==0:
                visited[nx][ny] = 1
                dfs(nx,ny,h)    

max_v = max(map(max, data))

for i in range(max_v):
    visited = [[0] * (n) for _ in range(n)]
    count=0
    for j in range(n):
        for z in range(n):
            if data[j][z]>i and visited[j][z]== 0:
                visited[j][z] = 1
                count+=1
                dfs(j,z,i)           
    result =max(result, count)

print(result)
