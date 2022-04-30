# 청소년 상어 - BOJ 19236
# DFS+구현
import copy

board = [[] for _ in range(4)]

#  ↑, ↖, ←, ↙, ↓, ↘, →, ↗ 
dx = [-1, -1, 0, 1, 1, 1, 0, -1]
dy = [0, -1, -1, -1, 0, 1, 1, 1]

for i in range(4):
    data = list(map(int, input().split()))
    fish = []
    for j in range(4):
        # 물고기 번호, 방향
        fish.append([data[2*j], data[2*j+1]-1]) # 0,2,4,6은 물고기 번호 입력되고, 1,3,5,7은 방향이 입력되므로
    board[i] = fish


max_score = 0


def dfs(sx, sy, score, board):
    global max_score
    score += board[sx][sy][0] # 상어의 현재 위치의 물고기 번호를 점수에 저장
    max_score = max(max_score, score) # 상어가 먹은 점수의 최대 점수를 구하기 위해
    board[sx][sy][0] = 0 # 현재 위치의 물고기는 먹었으므로 0

    # 물고기 움직임
    for f in range(1, 17): # 물고기 번호가 1-16까지 있으므로
        f_x, f_y = -1, -1
        for x in range(4): #모든 grid 위치에서 #1-16(변수i)까지 순서대로 해당 위치의 물고기를 찾음
            for y in range(4):
                if board[x][y][0] == f: #1-16까지 순서대로 해당 위치의 물고기를 찾음
                    f_x, f_y = x, y # 해당 위치 찾으면 위치를 따로 저장해주고 break
                    break
        if f_x == -1 and f_y == -1:
            continue # 해당 칸에 물고기가 없으면(번호 : 0), 다시 첫번째 for문으로
        f_d = board[f_x][f_y][1] # 해당 물고기의 방향

        for i in range(8): # 0-7까지. 0이면 방향 안 바뀌도록 설정해서 처음에는 해당 물고기 방향을 탐색
            nd = (f_d+i) % 8 
            nx = f_x + dx[nd] #물고기가 해당 방향으로 움직인 후의 x,y위치
            ny = f_y + dy[nd]
            if not (0 <= nx < 4 and 0 <= ny < 4) or (nx == sx and ny == sy): # 격자 밖으로 넘어가거나 상어 위치면 방향 바꿀 수 있도록 for문으로 감
                continue
            board[f_x][f_y][1] = nd # 물고기가 해당 방향으로 움직일 수 있다면, 움직이기 전 위치에 현재 방향(만약 움직이지 못 할 경우 45도씩 움직여 움직일수있는 방향을 찾고, 그 방향)을 저장
            board[f_x][f_y], board[nx][ny] = board[nx][ny], board[f_x][f_y] # 현재 위치의 물고기 번호와 다음 위치의 물고기 change
            break

    # 상어 먹음
    s_d = board[sx][sy][1] # 현재 상어 방향. 4일 경우 격자에서 벗어나므로 dfs 실행X
    for i in range(1, 5):
        nx = sx + dx[s_d]*i # 현재 상어 위치 + 상어가 움직일 방행*i(i는 상어가 움직일 수 있는 이동 수)
        ny = sy + dy[s_d]*i
        if (0<= nx < 4 and 0<= ny < 4) and board[nx][ny][0] > 0: #격자 안에서 있으면서 물고기가 0일 때만(물고기가 없으면 지나가지 못 함)
            dfs(nx, ny, score, copy.deepcopy(board)) #(현재 상어 위치x, 현재 상어 위치 y, 점수, board copy)

dfs(0, 0, 0, board)
print(max_score)