import sys
sys.setrecursionlimit(100000) #재귀함수 한도 재설정
input = sys.stdin.readline
data = []
result = 0

n = int(input())
for _ in range(n):
    data.append(list(map(int, input().split()))) 

def dfs(x,y,h):
    if x<0 or x>=n or y<0 or y>=n:
        return False

    if data[x][y]>h and visited[x][y]==0:
        visited[x][y] = 1
        dfs(x-1,y,h)
        dfs(x+1,y,h)
        dfs(x,y-1,h)
        dfs(x,y+1,h)
        return True    
    return False



max_v = max(map(max, data))

for i in range(max_v):
    visited = [[0] * (n) for i in range(n)]
    count=0
    for j in range(n):
        for z in range(n):
            if dfs(j,z,i) == True:
                count+=1
    result =max(result, count)

print(result)
