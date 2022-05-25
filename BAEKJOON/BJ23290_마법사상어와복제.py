from collections import deque
m ,s = int(input().split())
grid = [[0,deque()] *4 for _ in range(4)] #물고기수 :0 방향:-1
fish_info = [list(map(int,input().split())) for _ in range(m)] # 물고기 위치, dir

# for _ in range(m):
#     # 정보를 리스트로 저장
#     
#     fish_x,fish_y,dir=map(int,input().split())
#     #grid([물고기수, 방향])
#     grid[fish_x-1][fish_y-1][0]=1 # 물고기수
#     # grid[fish_x - 1][fish_y - 1][1].popleft()
#     grid[fish_x-1][fish_y-1][1].append(dir-1) #방향추가
#     fish_positions.append((fish_x-1,fish_y-1)) # 물고기 위치 저장

shark_x,shark_y=map(int,input().split())

grid[shark_x-1][shark_y-1]=-1 # 상어

#8가지 방향
direction = [(0,1),(-1,-1),(-1,0),(-1,1),(0,1),(1,1),(1,0),(1,-1)]
dir=0

def clock_reverse(dir):
    dir -= 1
    if dir<0:
        dir = 7
    return dir

# 상어의 움직임
# 상, 좌, 하, 우
move = [(-1,0),(0,-1),(0,1),(1,0)]
def shark_move(x,y):
    fish_count = 0
    fish_count_max = 0
    for one in range(3):
        nx,ny=x+move[one][0],x+move[one][1]
        if nx<0 or nx>3 or ny<0 or ny>3:
            continue
        x,y=nx,ny
        fish_count += grid[x][y][0] # 물고기수 더하기
        for two in range(3):
            nx, ny = x + move[two][0], x + move[two][1]
            if nx < 0 or nx > 3 or ny < 0 or ny > 3:
                continue
            x, y = nx, ny
            fish_count += grid[x][y][0]
            for three in range(3):
                nx, ny = x + move[three][0], x + move[three][1]
                if nx < 0 or nx > 3 or ny < 0 or ny > 3:
                    continue
                x, y = nx, ny
                fish_count += grid[x][y][0]
                if fish_count_max<fish_count:
                    shark_move_dir = [one, two, three]
                    fish_count_max = fish_count

    return (fish_count_max,shark_move_dir ) # 가장 많은 물고기 수, 움직임

    return

smell = deque()

#처음 물고기 수
for info in fish_info:
    fish_x, fish_y, dir = info
    grid[fish_x - 1][fish_y - 1][0] += 1  # 물고기수 증가
    grid[fish_x - 1][fish_y - 1][1].append(dir - 1)  # 방향추가
    
for _ in range(s):
        
    # 문제 fish포지션만 움직이면 안됨. 쌓인 물고기도 움직여야하므로 매번 grid를 모두 탐색해야함
    # grid에는 물고기 수와 deque(방향키,...) 로 이루어짐 grid[[물고기수, deque([방향키,...])]]
    # 물고기 수가 있을 때만 그 수 만큼 방향키 빼서(popleft) 이동
    # 이동 후 그 위치에서 물고기수 증가와 방향키 새로 append해줌

    for fish_position in fish_positions:
        x,y = fish_position[0],fish_position[1]
        dir = grid[x][y][1]
        nx, ny = x + direction[dir][0], y + direction[dir][1]  # 물고기 이동한 위치
        #해당자리조건
        # if grid[nx][ny] == -1 or grid[nx][ny] == -2 or nx < 0 or nx > 3 or ny < 0 or ny > 3:
        for i in range(7):
            if grid[nx][ny] == -1 or grid[nx][ny] == -2 or nx < 0 or nx > 3 or ny < 0 or ny > 3:
                dir = clock_reverse(dir)
                nx, ny = x+direction[dir][0],y + direction[dir][1]
            else:
                break
        grid[nx][ny][0] +=1
        grid[nx][ny][1].append(dir)
        grid[x][y][0] -= 1
        grid[nx][ny][1].popleft(dir)

        # 문제 : 상어랑 물고기랑 같이 존재하는 곳도 있음!! 처음이랑 복제될때!!
        # 상어 이동
        fish_count, shark_move_dir = shark_move(shark_x,shark_y)
        grid[shark_x][shark_y][0] =0 # 상어위치를 물고기가 0인 것으로 바꿔줌
        for shark_dir in shark_move_dir:
            shark_x, shark_y = shark_x+ move[shark_dir][0],shark_x+ move[shark_dir][1]
            if grid[shark_x][shark_y][0]>0:
                smell = deque((shark_x,shark_y))
                grid[shark_x][shark_y][0] = -2
                grid[shark_x][shark_y][1].clear() # 이동방향 clear
        #최종 상어 위치
        grid[shark_x][shark_y][0] =-1

        # 냄새 사라짐
        if len(smell) == 3:
            smell_x,smell_y = smell.popleft()
            grid[smell_x][smell_y][0] = 0

        # 복제
        for info in fish_info:
            fish_x, fish_y, dir = info
            grid[fish_x - 1][fish_y - 1][0] += 1  # 물고기수 증가
            grid[fish_x - 1][fish_y - 1][1].append(dir - 1)  # 방향추가
        