from _collections import deque
n, m = map(int,input().split())
grid = [list(map(int,input().split())) for _ in range(n)]

# 상, 하, 좌, 우
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

# count, x, y, [위치]
def bfs(x,y):
    count=1
    rainbow_cnt=0
    locate = []
    rainbow = []
    block_num = grid[x][y]
    o_x,o_y=x,y
    q=deque()
    q.append((x,y))
    locate.append((x,y)) #block_num 위치
    visited[x][y] = True
    # q.append((x,y))
    while q:
        x,y=q.popleft()
        for i in range(4):
            nx,ny=x+dx[i],y+dy[i]
            if nx<0 or nx>n-1 or ny<0 or ny>n-1:
                continue
            if (grid[nx][ny] == block_num or grid[nx][ny]==0)and visited[nx][ny] == False:
                if grid[nx][ny]==0:
                    rainbow.append((nx,ny)) #무지개 위치
                    rainbow_cnt+=1 #무지개 수
                visited[nx][ny] = True
                q.append((nx,ny))
                locate.append((nx,ny)) #block 위치
                count+=1
    for r in rainbow: # 무지개는 다른 num에 또 사용되므로 visited를 False로
        visited[r[0]][r[1]]=False
    return (count,rainbow_cnt,o_x,o_y,locate)

# 중력
def gravity(grid):
    grid_t = []
    for each in zip(*grid): #행->열,열->행 바꾸고 튜플 -> 리스트로 변환해서 삽입
        grid_t.append(list(each))
    for i in range(n): # 행 기준으로 좌(위), 좌(아래)로 생각하고 중력으로 인해 좌(아래)로 이동시킬 수 있도록
        empty = 0
        for j in range(n-1,-1,-1):
            if grid_t[i][j] == -2:
                empty+=1
            elif grid_t[i][j] == -1:
                empty=0
            elif grid_t[i][j]>=0 and empty!=0:
                grid_t[i][j+empty]= grid_t[i][j]
                grid_t[i][j] = -2
    grid=[]
    for each in zip(*grid_t):#행->열,열->행 다시 돌리기 위해, 돌린 후 튜플 -> 리스트로 변환해서 삽입
        grid.append(list(each))

    return grid

#90도 회전: 행-> 열, 열->행이 되지만 각 행과 열이 reverse로 삽입되어야함
# grid_rotate_90=list(zip(*grid))[::-1]
# 행 -> 열, 열->행으로 바꾼후 행의 위치를 역순으로! 하면 90도 회전임
# original : [1 2 3][4 5 6][7 8 9] 
# 역 : [1 4 7] [2 5 8] [3 6 9] 즉 역행렬의 행만 reverse하면 90도 회전이랑 같음
# 90도 회전 : [3 6 9][2 5 8][1 4 7]
# 튜플을 리스트로 
# grid_rotate_90 = [list(row) for row in grid_rotate_90]

def lotate_90(grid):
    n_new = []
    for g in grid:
        n_new.append(g[::-1]) #행을 reverse시켜 append
    grid_rotate_90 = []
    for each in zip(*n_new): #행->열, 열->행으로 바꾸고, 튜플->리스트로 변환
        grid_rotate_90.append(list(each))
    return grid_rotate_90
score = 0
while True:
    compare_list = []
    visited = [[False] * n for _ in range(n)]
    # 가장 큰 불록 찾고 block 사라지기
    for i in range(n):
        for j in range(n):
            if visited[i][j] == False and grid[i][j]>0:
                compare_list.append(bfs(i,j))
                # key = lambda x:(x[0],x[1],x[2])
               
                if len(compare_list) == 2: #compare_list에 2개의 데이터 쌓이면 블록수, 무지개수, 행, 열 순으로 가장 큰 순으로 정렬
                    compare_list.sort(reverse=True) # 가장 큰 수 제외하고 뒤에는 필요없으므로 빼기
                    compare_list.pop()
    if not compare_list : # compare_list가 없으면 break
        break
    elif compare_list[0][0] <2: # block의 기준은 2 이상이므로 적으면 break
        break
    #가장 큰 block에 위치하는 모든 block사라짐
    locate = compare_list[0][4]
    for l in locate:
        grid[l[0]][l[1]]=-2

    #중력
    grid = gravity(grid)

    #90도 회전
    grid = lotate_90(grid)

    #중력
    grid = gravity(grid)
    #점수
    score += compare_list[0][0] ** 2

print(score)

