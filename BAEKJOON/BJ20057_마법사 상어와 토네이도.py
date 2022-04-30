import math
n = int(input())
grid = [list(map(int, input().split())) for _ in range(n)]

#좌 하 우 상
percents = [[(-1,0,1),(1,0,1),(-1,-1,7),(1,-1,7),(-2,-1,2),(2,-1,2),(-1,-2,10),(1,-2,10),(0,-3,5),(0,-2,0)],
            [(0,-1,1),(0,1,1),(1,-1,7),(1,1,7),(1,-2,2),(1,2,2),(2,-1,10),(2,1,10),(3,0,5),(2,0,0)],
            [(-1,0,1),(1,0,1),(-1,1,7),(1,1,7),(-2,1,2),(2,1,2),(-1,2,10),(1,2,10),(0,3,5),(0,2,0)],
            [(0,-1,1),(0,1,1),(-1,-1,7),(-1,1,7),(-1,-2,2),(-1,2,2),(-2,-1,10),(-2,1,10),(-3,0,5),(-2,0,0)]]
#좌, 하, 우, 상
direction = [(0,-1),(1,0),(0,1),(-1,0)]

# 토네이도 첫 위치
rain_x = n//2
rain_y = n//2
dist = 1 # 방향안바뀌고 움직이는 거리
dir = 0 # 방향
dir_change = 0
answer = 0 # 버려진 모래
n_rain_x=0
n_rain_y=0
info=[]
new_x=0
new_y=0
n_mout=0

while True:
    # 1,1 토네이도 위치 오면 종료
    if rain_x == 0 and rain_y == 0:
        print(answer)
        break

    # 방향 안 바뀌는 동안 퍼센트에 맞는 모래 구하기
    # dist는 방향 두 번 바뀌면 dist가 하나 늘어남
    # dist 는 방향이 0과 2 일때 dist가 늘어남
    # if dir == 0 or dir == 2:  # 다음 회차(d==0) 이거나 right(d==2) 이면 한번 더
    # dist += 1
    for _ in range(dist):
        # 1,1 토네이도 위치 오면 종료
        if rain_x == 0 and rain_y == 0:
            break

        n_rain_x = rain_x+ direction[dir][0]  # 다음 토네이도(y) 위치
        n_rain_y =  rain_y+ direction[dir][1] # 다음 토네이도(y) 위치

        n_mout = grid[n_rain_x][n_rain_y] # 다음 토네이도 위치의 모래 양

        for info in percents[dir]: # 모든 비율의 모래 구하기
            new_x = rain_x+info[0]
            new_y = rain_y + info[1] 

            if new_x<0 or new_x>n-1 or new_y<0 or new_y>n-1: # 격자밖으로 => answer 증가
                if info[2] == 0: # 마지막 알파는 다음 토네이도(y) 위치에 남은 모든 모래를 가지고 있음
                    answer+=grid[n_rain_x][n_rain_y]
                    grid[n_rain_x][n_rain_y] -= grid[n_rain_x][n_rain_y] # 다음 토네이도 모두 비워주기
                else:
                    answer += math.floor(n_mout*(info[2]/float(100))) # 해당위치에 비율에 맞는 모래를 answer에 추가.
                    grid[n_rain_x][n_rain_y] -= math.floor(n_mout*(info[2]/float(100)))# 다음 토네이도 위치의 모래를 이동한만큼 빼주기. why? 알파는 그 나머지 값 모두를 가질 거기때문에
            else: # 격자 안에 => gird 모래 갯수 변경
                if info[2] == 0: # 마지막 알파는 다음 토네이도(y) 위치에 남은 모든 모래를 가지고 있음. 알파 위치에 추가.
                    grid[new_x][new_y]+=grid[n_rain_x][n_rain_y]
                    grid[n_rain_x][n_rain_y] -= grid[n_rain_x][n_rain_y] # 다음 토네이도 모두 비워주기
                else:
                    grid[new_x][new_y] += math.floor(n_mout*(info[2]/float(100))) # 해당위치에 비율에 맞는 모래를 추가
                    grid[n_rain_x][n_rain_y] -= math.floor(n_mout * (info[2] / float(100))) # 다음 토네이도 위치의 모래를 이동한만큼 빼주기. why? 알파는 그 나머지 값 모두를 가질 거기때문에.

        #토네이도 위치 바꾸기
        rain_x = n_rain_x
        rain_y = n_rain_y

    # 방향 바꾸기
    dir = (dir+1)%4
    dir_change += 1
    # 방향 두번 바뀌면 dist 증가(달팽이 특징)
    if dir_change==2:
        dist += 1
        dir_change = 0







# 답지 



# 모래 계산하는 함수
def recount(s_x, s_y, direction):
    global ans

    if s_y < 0:
        return
        
    # 3. a, out_sand
    total = 0  # a 구하기 위한 변수
    for dx, dy, z in direction:
        nx = s_x + dx
        ny = s_y + dy
        if z == 0:  # a(나머지)
            new_sand = sand[s_x][s_y] - total
        else:  # 비율
            new_sand = int(sand[s_x][s_y] * z)
            total += new_sand

        if 0 <= nx < N and 0 <= ny < N:  # 인덱스 범위이면 값 갱신
            sand[nx][ny] += new_sand
        else:  # 범위 밖이면 ans 카운트
            ans += new_sand

N = int(input())
sand = [list(map(int, input().split())) for _ in range(N)]

# 2. 방향별 모래 비율 위치
left = [(1, 1, 0.01), (-1, 1, 0.01), (1, 0, 0.07), (-1, 0, 0.07), (1, -1, 0.1),
         (-1, -1, 0.1), (2, 0, 0.02), (-2, 0, 0.02), (0, -2, 0.05), (0, -1, 0)]
right = [(x, -y, z) for x,y,z in left]
down = [(-y, x, z) for x,y,z in left]
up = [(y, x, z) for x,y,z in left]

s_x, s_y = N//2, N//2
ans = 0
dx = [0, 1, 0, -1]
dy = [-1, 0, 1, 0]

# 1.토네이도 회전 방향(y위치)
dict = {0: left, 1: down, 2: right, 3: up}
time = 0
for i in range(2*N-1):
    # 몫: i//4(타임+1), 나머지:i%4(방향)
    d = i % 4
    if d == 0 or d == 2:  # 다음 회차(d==0) 이거나 right(d==2) 이면 한번 더
        time += 1
    for _ in range(time):
        n_x = s_x + dx[d]
        n_y = s_y + dy[d]
        recount(n_x, n_y, dict[d])  # y좌표, 방향
        s_x, s_y = n_x, n_y

print(ans)