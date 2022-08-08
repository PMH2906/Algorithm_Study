from collections import deque

# 풀이2
N, M = map(int,input().split())

grid = [[0 for _ in range(N)] for _ in range(N)] # 1차원배열 위치 담을 2차원 배열

# map = [list(map(int,input().split())) for _ in range(N)] # 한 번에 입력 받기

line = [0]*(N**2) #좌표 담김 1차원 배열
ball = []

def init_grid() :
    global grid, line
    dx=(0,1,0,-1) # 우 하 좌 상
    dy=(1,0,-1,0)

    cnt = N**2-1
    dist = N
    loc = (0,-1)
    dir = 0
    while True: # dist가 0 일때까지 반복
        for i in range(dist): # dist만큼 같은 방향으로 가면서 1차원배열, 2차월배열의 위치를 서로에게 담기
            nx=loc[0]+dx[dir]
            ny=loc[1]+dy[dir]

            loc =(nx,ny)
            line[cnt] = loc
            grid[nx][ny] = cnt
            cnt-=1

        if(dir==0 or dir ==2): # dist 만큼 계산되면 방향바뀌는데 특정 방향은 dist-1이 됨
            dist-=1
        dir=(dir+1)%4 # 방향 바꾸기
        if dist == 0: #dist가 0이 되었다는 것은 달팽이 배열 다 채웠다는 뜻. 종료
            break

init_grid()
print(grid)
print(line)

#풀이2
dist = 0
x,y=N//2, N//2
# 좌 하 우 상
dx=[0, 1, 0, -1]
dy=[-1, 0, 1, 0]

result=[]

for i in range(2*N-1): #8번 방향 바뀜 # range(8) 이면 1-7까지만 됨
    d=i%4 # 방향 바꾸기

    if d==0 or d==2: # dist 늘리기
        dist+=1
    
    for _ in range(dist): # dist 만큼 이동하면서 달팽이 요소 접근
        nx = x + dx[d]
        ny = y + dy[d]
        # 게산 할 꺼! 현재 x,y는 현재 위치임.
        x = nx
        y = ny

        result.append((x,y))

print(result)