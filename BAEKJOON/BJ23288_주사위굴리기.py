from _collections import deque
n, m, k = map(int, input().split())
maps=[list(map(int, input().split())) for _ in range(n)]

# 우, 하, 좌, 상(시계방향)
direction = [(0,1),(1,0),(0,-1),(-1,0)]
# 우, 하, 좌, 상로 옮길 때 주사위 위치
dice_dir = [(2,4,1,3,0,5), (3,5,2,1,4,0),(4,2,0,3,1,5),(5,3,2,0,4,1)]
dice=[6,1,3,5,4,2]

now_dice_location = (0,0)

def bfs(x,y,num):
    visited = [[False] * m for _ in range(n)]
    count=1
    q=deque()
    q.append((x,y))
    visited[x][y] = True
    while q:
        x,y=q.popleft()
        for i in range(4):
            nx,ny=x+direction[i][0], y+direction[i][1]
            if nx < 0 or nx > n - 1 or ny < 0 or ny > m - 1:
                continue
            if maps[nx][ny] == num and visited[nx][ny]==False:
                visited[nx][ny] = True
                q.append((nx,ny))
                count+=1
    return count

def next_dice_list(dir,dice_dir,dice):
    # dice_dir = [(2, 4, 1, 3, 0, 5), (3, 5, 2, 1, 4, 0), (4, 2, 0, 3, 1, 5), (5, 3, 2, 0, 4, 1)]
    # dice = [6, 1, 3, 5, 4, 2]
    new_dice = [0] * 6
    for i in range(6):
        new_dice[i] = dice[dice_dir[dir][i]]

    return new_dice

def next_dir(dir,clock_dir):
    # 한줄로
    # dir = (dir+clock_dir)%4 # 4로 나누면 나머지는 0,1,2,3이므로
    dir+=clock_dir
    if dir>3:
        dir=0
    elif dir<0:
        dir=3
    return dir

def rotate_180(dir):
    # 한줄로
    #dir = (dir+2)%4 # 서로 반대 방향은 2씩 차이남. 3을 넘어가면 %4로 해주어 0,1,2,3으로 나오도록!

    if dir == 0:
        dir=2
    elif dir == 2:
        dir = 0
    elif dir == 1:
        dir = 3
    elif dir == 3:
        dir=1
    return dir
dir = 0
score=0
x,y=0,0
for _ in range(k):
    nx,ny=x+direction[dir][0],y+direction[dir][1] #이동
    # 이동 후 주사위 위치
    if nx < 0 or nx > n - 1 or ny < 0 or ny > m - 1:
        dir = rotate_180(dir)
        nx, ny = x + direction[dir][0], y + direction[dir][1]

    score += bfs(nx,ny,maps[nx][ny])*maps[nx][ny]
    dice = next_dice_list(dir, dice_dir, dice)

    if dice[0]> maps[nx][ny]:
        dir = next_dir(dir, 1) # 방향 전환
    elif dice[0]< maps[nx][ny]:
        dir = next_dir(dir, -1)
    elif dice[0] == maps[nx][ny]:
        dir=dir
    x,y=nx,ny
print(score)