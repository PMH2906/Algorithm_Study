# Reference : https://velog.io/@heyksw/Python-%EB%B0%B1%EC%A4%80-platinum-23291-%EC%96%B4%ED%95%AD-%EC%A0%95%EB%A6%AC

from _collections import deque

n, k = map(int,input().split())

direction = [(-1,0),(1, 0),(0,-1),(0,1)] # 좌, 우, 하, 상
board = list()
board.append(deque(list(map(int,input().split())))) # 큐를 사용하여 append해줌

#물고기가 가장 적은 어항에 물고기 한 마리 넣기
def push_fish_to_min_bowl(board):
    min_bowl_fish_num=min(board[0])
    for i in range(len(board[0])):
        if board[0][i] ==min_bowl_fish_num:
            board[0][i]+=1

# 가장 왼쪽의 어항을 위에 쌓기
def popleft_and_stack(board):
    pop=board[0].popleft()
    #위에 쌓는 것을 아래로 쌓아간다고 보면됨

    # 여기서 각 행 또한 deque여야하고 전체 리스트도 deque여야한다. 우리는 둘 다에서 popleft를 쓸 것이기때문
    board.append(deque([pop])) # 처음에는 board: (deque)(deque(1,2,3,4,5)) -> pop 후 그것을 추가 (deque)(deque(2,3,4,5) deque(1))

#2개 이상 쌓인 어항들을 분리해서 공중부양
def fly_blocks(board):
    while True:
        #행>열-board의 맨 뒷 칸에 있는 길이(즉 쌓아올린 어항의갯수)
        #쌓아올린 후 그 줄의 어항의 갯수보다 밑에 있는 어항의 갯수가 적으면 안되므로
        # 쌓아올릴 행이 쌓아올린 후 열보다 크면 break
        if len(board)>len(board[0])-len(board[-1]):
            break
        will_fly_blocks=[]
        will_fly_blocks_row = len(board) #행
        will_fly_blocks_col = len(board[-1])  #위에 올라진 어항의 수

        for i in range(will_fly_blocks_row):
            new_deque = deque()
            for _ in range(will_fly_blocks_col):
                new_deque.append(board[i].popleft()) # 맨 아랫줄의 어항을 올려진 어행의 수 만큼 append. new_deque((1,2,3...))
            will_fly_blocks.append(new_deque) # 90도 회전시킬 어항이 will_fly_bolcks에 들어감. 맨 아랫줄 맨 왼쪽부터. will_flt_blocks((1,2,3),(4,5,6)...)

        board=[board[0]] # 항상 첫번째 행은 위에서 하나 뺀 어항을 제외한 어항으로 reset
        rotated_blocks=rotate_90_clockwise(will_fly_blocks)
        for row in rotated_blocks:
            board.append(deque(row)) #board([2,3,4,5,6] [1,0])
    return board
#시계방향 90도 회전
def rotate_90_clockwise(will_fly_blocks)  :
    #new_will_fly_blocks([0],[1]) => 행 :1 열 2로 시계방향으로 돌려서 만들어줄 것
    new_will_fly_blocks = [[0]*len(will_fly_blocks) for _ in range(len(will_fly_blocks[0]))]
    for i in range(len(will_fly_blocks[0])): # will_fly_blocks[0]는 공중부양 후 행의 갯수가 됨
        for j in range(len(will_fly_blocks)): #will_fly_blocks는 공중부양 후 열의 갯수가 됨
            new_will_fly_blocks[i][j]=will_fly_blocks[j][len(will_fly_blocks[0])-1-i]  #코드 상에서 가장 아랫줄 어항이 맨 윗 행에 위치
    return new_will_fly_blocks

def fly_fish_num(board):
    # dp를 선언해준 이유:값의 움직임이 동시에 일어나므로 변하지 않는 상태에서 모든 물고기 수를 확인해야하므로
    # board에 바로 계산하고 대입 불가능
    dp = [[0] * len(board[x]) for x in range(len(board))] #board의 각 행의 갯수마다 [0]으로 초기화
    for x in range(len(board)):
        for y in range(len(board[x])):
            for d in direction:
                nx = x + d[0]
                ny = y + d[1]
                # ny>len(board[nx]) 로 board안에 nx인 이유는 nx의 위치에서 ny에 값이 위치하는지 확인하기 위해
                # ex) [9 5 10 8] [10 6] [5 3] x:0 y:2 nx:1 ny:2 인데 nx줄에 ny 위치에 값이 존재하지 않으므로
                # if nx<0 or nx>len(board)-1 or ny<0 or ny>len(board[nx])-1:
                #     continue
                if 0 <= nx < len(board) and 0 <= ny < len(board[nx]):
                    if board[x][y]>board[nx][ny]:
                        if (board[x][y] - board[nx][ny])//5>= 1:
                            dp[x][y]-=(board[x][y] - board[nx][ny])//5
                    else:
                        if (board[nx][ny] - board[x][y])//5>= 1:
                            dp[x][y]+=(board[nx][ny] - board[x][y])//5
    for i in range(len(board)):
        for j in range(len(board[i])):
            board[i][j]+=dp[i][j]

# 어항을 다시 일렬로
def put_bowl_in_a_row(board):
    new_board = deque()

    # 모든 행의 가장 위에 쌓인 어항의 열까지 넣어줌. 따라서 밑에 행의 어항은 크기가 크면 다 못들어감.
    for i in range(len(board[-1])): # 위에 쌓였던 열
        for j in range(len(board)): # 행
            new_board.append(board[j][i])

    # 나머지 넣어줌
    # 가장 아랫줄 어항의 갯수는 위에 쌓인 어항의 갯수보다 많으므로 아래 어항에서 못 넣어준 어항도 넣어준다
    for i in range(len(board[-1]),len(board[0])):
        new_board.append(board[0][i])

    result_list = list()
    result_list.append(new_board)
    # result_list.append(new_board)
    return result_list

def rotate_180_clockwise(left):
    new_board = []
    # reversed(range(len(left)))는 len(left)-1부터 0까지 거꾸로 수를 반복
    for i in reversed(range(len(left))):
        left[i].reverse()
        new_board.append((left[i]))
    return new_board

# 공중부양2 작업. 절반을 자르는데 2번 수행
def fly_blocks2(board):
    left1=list()
    left2=list()
    new_dq = deque()
    for i in range(n//2):
        new_dq.append(board[0].popleft())
    left1.append(new_dq) # 가장 아랫 줄 어항은 여기서 가장 윗 행에 속한다
    rotated_left1 = rotate_180_clockwise(left1)
    board += rotated_left1

    for i in range(2):
        temp_dq=deque()
        for j in range(n//4): #움직 일 n//4 개 선택 시 모든 행 (0,1)에서 열(0~n//4-1) 선택
            temp_dq.append(board[i].popleft())
        left2.append(temp_dq) # 아래 어항 부터(여기서는 윗 행부터) 윗 어항까지(여기서는 2번째 행이 윗 어항임)
    rocated_left2 = rotate_180_clockwise(left2) # 180도 돌리면 가장 윗 어항이 180도 회전하여 맨 윗줄 어항 바로 위에 위치함
    board += rocated_left2

# 물고기가 가장 많은 어항과 가장 적은 어항의 차이를 구하는 함수
def get_result(board):
    first_q = board[0]
    result = max(first_q) - min(first_q)
    return result

answer = 0
while True:
    result = get_result(board)
    if result<=k:
        print(answer)
        break
    push_fish_to_min_bowl(board)
    popleft_and_stack(board) #맨 왼쪽 값 올리기
    board=fly_blocks(board) # 공중부양
    fly_fish_num(board) # 근처 물고기 갯수와 비교해서 옮겨주기
    board = put_bowl_in_a_row(board) # 어항을 다시 일렬로
    fly_blocks2(board) # 어항을 반으로 올리는 작업 두번 반복
    fly_fish_num(board)
    board=put_bowl_in_a_row(board)
    answer += 1
