# 달팽이 풀이1 2D -> 1D
N, M = map(int, input().split())

grid = [[0 for _ in range(N)] for _ in range(N)]
line = [0]*(N**2)
ball = []

def init_grid():
    global grid,line

    # 우 하 좌 상
    dx=(0,1,0,-1)
    dy=(1,0,-1,0)

    cnt=N**2-1
    dist = N
    loc = (0,-1)
    dir = 0
    while True:
        for i in range(dist):
            nx=loc[0]+dx[dir]
            ny=loc[1]+dy[dir]

            loc = (nx,ny)
            line[cnt] = (nx,ny)
            grid[nx][ny]= cnt
            cnt-=1
        if dir==0 or dir==2:
            dist-=1
        dir = (dir + 1) % 4
        if dist == 0:
            break


init_grid()
print(grid)
print(line)

#달팽이 풀이2
dist = 0
x,y=N//2, N//2

# 좌 하 우 상
dx = [0, 1, 0, -1]
dy = [-1, 0, 1, 0]
result=[]
for i in range(2*N-1):
    d=i%4
    if d == 0 or d==2:
        dist+=1

    for _ in range(dist):
        nx= x+dx[d]
        ny=y+dy[d]
        x=nx
        y=ny
        #계산할 거!!!
        result.append((x,y))
print(result)
