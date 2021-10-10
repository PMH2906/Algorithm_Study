r, c = map(int, input().split())
g=[list(input()) for _ in range(r)]
result = 1
dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]

def bfs(x,y):
    global result
    q=set() 
    #큐를 집합으로 사용하는 이유:
    # 같은 위치에 같은 문자열을 가진 상황을 중복해서 확인할 필요없으니,
    # 같은 값은 같은 객체로 취급하는 집합 자료형을 사용한다.
    q.add((x,y,g[x][y]))

    while q:
        x, y, visited_al = q.pop() 
        #여기서는 큐의 특성인 first-in, first-out을 지킬 필요가 없다. 
        # 각 칸의 상황을 위치와 함께 저장하고 있기 때문에 어떤 순서로 확인하던지 가능한 경우 전체를 완전 탐색할 수 있다.

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if (0<=nx<r) and (0<=ny<c):
                if g[nx][ny] not in visited_al:
                    next_visited_al = visited_al+g[nx][ny]
                    result = max(result, len(next_visited_al))
                    q.add((nx,ny,next_visited_al))
                    
bfs(0,0)
print(result)

#참고)  https://yerinpy73.tistory.com/48