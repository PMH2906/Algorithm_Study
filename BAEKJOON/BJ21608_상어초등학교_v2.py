# 마지막에 학생들이 인접한 지역에 좋아하는 학생들이랑 앉았는지 확인하기 위하여 like_room 리스트를 통해 각 학생 인덱스에 맞는 좋아하는 사람을 저장
# p(n*n)명 모두 확인해야함 -> 각 학생 인덱스와 그 학생이 좋아하는 사람을 입력받고 like_room 리스트에 append
# 만약 처음 학생의 자리 배치 경우 1, 2, 3 조건에 해당하는 자리는 무조건 (1,1)이므로 위치 시킴
# 모든 행과 열(n*n) 범위를 돌면서 상하좌우 살펴 3개의 조건에 해당되는 최적 자리를 선정함
# 상하좌우 돌 때, 리스트 인덱스 범위 벗어나면 continue 
# 모든 행과 열 (n*n) 범위를 돌면서 해당 자리에서 인접한 곳에 좋아하는 학생이 있을 때 sum_like, 인접한 곳이 빈 경우일 때 sum_empty를 1증가 시킴
# 상하좌우 다 살폈을 때 temp 리스트에 sum_like, sum_empty, (i,j => 해당 행,열값) append 해줌
#  모든 행과 열(n*n) 범위 살핀 후, temp 리스트를 sort 해줘서 조건에 맞게 sum_like 값이 많은 때, 그 후 sum_like 값이 동일하다면 sum_empty 값이 많은 때,  또한 sum_empty 값도 동일하다면 행 값이 작을 때, 행 값도 동일하다면 열 값이 작을 때 순으로 정렬
# 정렬 후 가장 첫 번째 있는 값의 행, 열값을 최적 위치로 선정하기 위하여 classroom 리스트의 해당 행, 열에 학생 번호로 변경
# 마지막으로  classroom에 최적 자리로 선정된 학생들 하나하나 인접 지역에 얼마나 좋아하는 학생이 배치되었는지 확인하여 학생의 만족도를  계산
n = int(input())

p = n*n
classroom = [[0]*n for _ in range(n)]
like_room = [[] for _ in range(p+1)]
dx=[0, 0, 1, -1]
dy=[1, -1, 0, 0]

for _ in range(p):
    array = list(map(int, input().split()))
    # like = array[1:]
    like_room[array[0]] = array[1:] # 좋아하는 학생 추가
    if p==0:
        classroom[1][1]=array[0] #처음은 무조건 이 자리임. 추가하고 뒤에 조건 볼 필요없으므로 pass
        continue
    temp=[]
    for i in range(n):
        for j in range(n):
            sum_like, sum_empty = 0, 0
            if classroom[i][j] != 0: # 이 자리는 차지하고 있으므로 pass
                continue
            for k in range(4): # 만약 자리가 빈 자리라면 해당 자리 상,하 좌, 우 살피기
                nx=i+dx[k]
                ny=j+dy[k]
                if nx<0 or nx>n-1 or ny<0 or ny > n-1:
                    continue
                if classroom[nx][ny] in array[1:]: #해당 자리의 상,하,좌,우에 좋아하는 학생 있으면 sum_like 추가
                    sum_like+=1
                if classroom[nx][ny] == 0: #해당 자리의 상,하,좌,우에 좋아하는 학생 있으면 sum_emtpy 추가
                    sum_empty += 1
            temp.append((sum_like,sum_empty,(i,j))) # 한줄마다 classroom의 모든 자리 탐색한 것을 temp에 저장

    temp.sort(key=lambda x: (-x[0],-x[1], x[2])) # 모든 classroom 자리 탐색했으면 temp에 추가한 자료를 like, empty, 자리위치 순으로 정렬. 이때 오름차순 정렬되므로 like와 empty는 -붙이기
    classroom[temp[0][2][0]][temp[0][2][1]] = array[0] # temp의 맨 앞 자료가 조건에 맞는 위치임

sum_answer = 0
# classroom의 모든 위치 기준에서 상, 하, 좌, 우 살피며 좋아하는 학생 수 있다면,
# answer + 1 해주고 해당 수에 맞는 수 더해주기
# [0, 1, 10, 100, 1000] => 10**좋아하는 학생 수
#좋아하는 학생수가 0이면 더해줄필요X
for i in range(n):
    for j in range(n):
        answer =0
        for k in range(4):
            nx = i + dx[k]
            ny = j + dy[k]
            if nx < 0 or nx > n - 1 or ny < 0 or ny > n - 1:
                continue
            if classroom[nx][ny] in like_room[classroom[i][j]]: 
                answer += 1
                continue
        if answer != 0:
            sum_answer += (10**(answer-1))
print(sum_answer)