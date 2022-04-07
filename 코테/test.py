'''def solution(money, costs):
    answer = 0
    moneys = [500,100,50,10,5,1]
    
    for i in range(len(costs)-1):
        if costs[5-i]<costs[4-i]*(moneys[i]//moneys[i+1]):
            answer += (money//moneys[i])*costs[5-i]
            money -= (money//moneys[i])*moneys[i]
            #print(money)
            #print(money//moneys[i])

    answer += money*costs[0]
    money -= money//moneys[5]
    return answer

print(solution(4578,[1, 4, 99, 35, 50, 1000]))'''
# 우, 하, 좌, 상
dx = [0,1,0,-1]
dy = [1,0,-1,0]


# 정방향
def clock_true(idx, point, answer, n, main_point):
    cnt = 1
    x, y = point[0], point[1]
    answer[x][y] = 1
    for i in range(n-2):
        x = x+dx[idx]
        y = y+dy[idx]
        cnt+=1
        answer[x][y] = cnt
    for i in range(n-3):
        x = x+dx[(idx+1)%4]
        y = y+dy[(idx+1)%4]
        cnt+=1
        answer[x][y] = cnt

    if len(main_point) == 1 :
        answer[main_point[0][0]][main_point[0][1]] = cnt+1
    else:
        for m_x,m_y in main_point:
            answer[m_x][m_y] = cnt+1

    return answer

def clock_false(idx,point, answer, n, main_point):
    cnt = 1
    x, y = point[0], point[1]
    answer[x][y] = 1
    for i in range(n-2):
        x = x+dx[idx]
        y = y+dy[idx]
        cnt+=1
        answer[x][y] = cnt
    for i in range(n-3):
        x = x+dx[(idx-1)%4]
        y = y+dy[(idx-1)%4]
        cnt+=1
        answer[x][y] = cnt

    if len(main_point) == 1 :
        answer[main_point[0][0]][main_point[0][1]] = cnt+1
    else:
        for m_x,m_y in main_point:
            answer[m_x][m_y] = cnt+1
    return answer
def solution(n, clockwise):
    answer = [[0]*n for _ in range(n)]
    points = [[0, 0], [0, n - 1], [n - 1, n - 1], [n - 1, 0]]
    m = n//2

    if n%2 == 0:
        main_point = [[m-1,m-1], [m-1, m],[m, m-1],[m,m]]
    else:
        main_point= [[m,m]]

    #print(main_point)
    for idx, point in enumerate(points):
        # 시계방향
        if clockwise is True:
            answer = clock_true(idx, point, answer, n, main_point)
        # 반시계 방향
        else:
            answer = clock_false((idx+1)%4, point, answer, n, main_point)
        #print(answer)

    return answer

print(solution(9,False))