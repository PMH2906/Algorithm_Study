from _collections import deque
n, m = map(int, input().split())

maps = [list(map(int,input().split())) for _ in range(n)]
move = deque([list(map(int,input().split())) for _ in range(m)])

rain_location = deque([(n-2,0),(n-2,1),(n-1,0),(n-1,1)])
dir = [(0,-1),(-1,-1),(-1,0),(-1,1),(0,1),(1,1),(1,0),(1,-1)]

# 대각선 구름 카운트
def count_rain(x,y):
    count=0
    for i in range(1,5):
        nx,ny=x+dir[i*2-1][0],y+dir[i*2-1][1]
        if nx<0 or nx>n-1 or ny<0 or ny>n-1:
            continue
        if maps[nx][ny]>0:
            count+=1
    return count

# 다음 구름 자리
def next_rain_location(rain_location,dir,s):
    rain_len = len(rain_location)
    for _ in range(rain_len):
        x,y=rain_location.popleft()
        nx, ny= x+(dir[0]*(s%n)),y+(dir[1]*(s%n))
        if nx<0:
            nx += n
        elif nx>=n:
            nx -= n
        if ny<0:
            ny += n
        elif ny>=n:
            ny -= n
        rain_location.append((nx,ny))
        visited[nx][ny] = True
        maps[nx][ny]+=1
    return rain_location

# rain_location은 다음 구름자리가 추가되고, 지나온 구름자리는 deque를 이용하여 뺌
while move:
    visited = [[False] * n for _ in range(n)]
    d, s = move.popleft()
    rain_location=next_rain_location(rain_location,dir[d-1],s)
    while rain_location:
        x,y=rain_location.popleft()
        count = count_rain(x,y)
        maps[x][y] += count
    for i in range(n):
        for j in range(n):
            if visited[i][j]==False and maps[i][j]>=2:
                rain_location.append((i,j))
                maps[i][j] -= 2
print(sum(map(sum,maps)))
