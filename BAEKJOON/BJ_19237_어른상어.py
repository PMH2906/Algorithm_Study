# 포인트 상어의 숫자가 작은 것이 살아남는다.
# 그러므로 상어의 숫자를 1부터 규칙에 맞게 array에 상어를 넣은다면
# 큰 숫자가 작은 숫자의 상어에 움직인다면 무시하고 넘어가도 됨

# 1.데이터 받을 때 상어가 위치하면 상어의 정보에 x,y,d를 저장하고, 해당 array의 위치에 상어숫자, 남은 냄새 저장. 0이면 array를 모두 none으로 초기화
# while
# 주의!상어의 정보에 다음 위치를 저장했으므로 x,y는 다음 위치에 상어가 올 정보
# 2. if 상어를 순서대로(1-m)확인할 것. 상어의 현재 위치의 array[x][y] 데이터가 none이거나 저장된 상어숫자가 현재 상어숫자와 동일하면 해당 위치 array에 상어숫자, 남은 냄새 시간 저장
#    else 아니라는것은 현재 위치에 상어가 존재한다는 말. 상어를 순서대로 처리하였으므로 같은 위치에 동시에 도착하면 숫자가 큰 상어는 사라져야함. 따라서 상어가 같이 도착했으나 이미 상어가 존재하면 해당 상어 지우기
# 3. 상어를 순서대로(1-m) 다음 nx, ny 구할 것임. 
#    모든 상어에서 for (해당 방향의 우선순위 즉, 4번의 for문 돔) 방향으로 이동한 nx,ny가 격자안에있고, array[nx][ny]=none이면 shark[상어 번호] = [nx, ny, nd] => 상어 정보 업데이트. 대입 후 break
#    for문 다 돌고 빈 칸이 없다면 
#    모든 상어에서 for (해당 방향의 우선순위 즉, 4번의 for문 돔) 방향으로 이동한 nx,ny가 격자안에있고, (냄새를 준 상어)array[nx][ny][0] == 현재 상어 번호shark[상어 번호] = [nx, ny, nd] => 상어 정보 업데이트. 대입 후 break
#    다음 칸이 빈칸과 자신이 남긴 냄새의 칸이 아니라면 물고기 소멸
# 4. 모든 array 탐색해서 상어냄세 -1. 만약 0이면 array[x][y] = none
# 5. 숫자 카운트


# 3. for문 : 우선순위에 맞게 다음 nx,ny를 구함.
# 3. 해당array[nx]ny]위치에 데이터가 none이거나 저장된 상어숫자가 현재 상어숫자와 동일하면 해당 위치 array에 상어숫자, 남은 냄새 시간 저장
# 4. 빈칸도 없으며 자신이 남긴 냄새 칸도 없다면 상어 삭제

# 상, 하, 좌, 우
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

if __name__ == "__main__":
    n, m, k = map(int, input().split())
    array = [list(map(int, input().split())) for _ in range(n)]
    direction = list(map(int, input().split()))  # 각 상어의 방향
    shark = {}  # 상어 정보를 dictionary 형태로 저장
    for i in range(n):
        for j in range(n):
            if array[i][j] != 0:
                shark[array[i][j]] = [i, j, direction[array[i][j] - 1]]  # 위치와 방향 정보 저장
            array[i][j] = None
    dir = [list(map(int, input().split())) for _ in range(m * 4)]  # 각 상어의 방향 우선순위 정보 입력

    time = -1
    while time <= 1000:
        # 1번 상어만 남으면 경우 종료
        if len(shark) == 1:
            print(time)
            exit(0)

        # 각 상어의 냄새를 뿌립니다.
        keys = list(shark.keys())
        keys.sort()  # 번호가 낮은 것 부터 먼저 처리 하기 위해서 정렬
        for index in keys:
            if array[shark[index][0]][shark[index][1]] is None:  # 현재 상어의 위치가 빈칸이면
                array[shark[index][0]][shark[index][1]] = [index, k]  # 상어 번호, 남은 냄새 시간 입력
            elif array[shark[index][0]][shark[index][1]][0] == index:  # 자기 냄새가 있던 칸
                array[shark[index][0]][shark[index][1]] = [index, k]
            else:  # 이동할 수 없으므로 삭제. 
                del shark[index]

        # 상어 이동
        keys = list(shark.keys())
        keys.sort()
        for index in keys:
            x, y, d = shark[index]  # 위치, 방향
            # 해당 상어의 방향 우선 순위에 맞게 빈칸 탐색
            flag_blank = False
            for i in dir[(index - 1) * 4 + (d - 1)]:
                nx, ny, nd = x + dx[i - 1], y + dy[i - 1], i
                if 0 <= nx < n and 0 <= ny < n:
                    if array[nx][ny] is None:  # 빈칸이면
                        flag_blank = True  # 빈칸을 찾은 상황
                        shark[index] = [nx, ny, nd]
                        break
            flag_same = False
            if not flag_blank:  # 빈칸이 없었던 경우
                for i in dir[(index - 1) * 4 + (d - 1)]:
                    nx, ny, nd = x + dx[i - 1], y + dy[i - 1], i
                    if 0 <= nx < n and 0 <= ny < n:
                        if array[nx][ny][0] == index:  # 똑같은 냄새 냄새
                            flag_same = True  # 똑같은 냄새를 찾은 상황
                            shark[index] = [nx, ny, nd]
                            break
            else:
                continue
            if not flag_same:  # 똑같은 냄새도 없던 경우
                del shark[index]  # 삭제

        # 상어 냄새 1 감소
        for i in range(n):  # 냄새 시간 1초 감소
            for j in range(n):
                if array[i][j] is not None:
                    array[i][j][1] -= 1
                    if array[i][j][1] == 0:  # 냄새가 0이면 삭제
                        array[i][j] = None

        time += 1
    # 시간이 1,000 초가 넘으면 -1 출력
    print(-1)