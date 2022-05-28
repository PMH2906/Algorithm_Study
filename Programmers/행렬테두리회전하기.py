def solution(rows, columns, queries):
    grid=[[] for _ in range(rows)]
    cnt=0
    for i in range(rows): # 행렬 만들기
        for j in range(columns):
            cnt+=1
            grid[i].append(cnt)
            
    def clock(query):
        nonlocal grid
        dx=[0,1,0,-1]
        dy=[1,0,-1,0]
        d=0
        min_v=grid[query[0]-1][query[1]-1] #테두리 첫 시작(가장 위, 가장 왼쪽)을 가장 작은 수로 초기화 시킴 
        temp=grid[query[0]-1][query[1]-1] #옮긴 위치의 원래 있던 수를 담을 공간(가장 위, 가장 왼쪽)
        x,y=query[0]-1,query[1]-1 # 현재 위치
        
        # 테두리를 가장 위, 가장 왼쪽을 시작으로 시계방향으로 회전(우, 하, 좌, 상)으로 살펴볼 것임
        # 위 과정을 반복하다 다시 첫번째 위치 (가장 위, 가장 왼쪽)에 도착하면 종료
        while True:
            nx, ny = x+dx[d], y+dy[d]
            if nx < query[0]-1 or nx > query[2]-1 or ny < query[1]-1 or ny>query[3]-1: #테두리 범위를 넘을 경우 방향 바꾸기
                    d=(d+1)%4
                    nx, ny = x+dx[d], y+dy[d]
            grid[nx][ny],temp=temp,grid[nx][ny] # 시계 방향으로 수 옮기고, 해당 위치에 존재하던 수는 다음 위치에 옮겨 줄 것이므로 temp에 담아두기
            min_v = min(min_v,temp) #테두리에서 가장 작은 수 찾기
            if nx== query[0]-1 and ny == query[1]-1: # 위 과정 반복하다 다시 첫번째 위치 (가장 위, 가장 왼쪽)에 도착하면 종료
                break
            else: 
                x,y=nx,ny
                
        return min_v # 최솟값 반환
    answer=[]
    for query in queries:
        min_v=clock(query)
        answer.append(min_v) # 반환된 최솟값 추가
    
    return answer