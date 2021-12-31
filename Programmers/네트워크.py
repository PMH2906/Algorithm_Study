def solution(n, computers):
    def bfs(i,n,computers):
        visited[i] = True
        for j in range(n):
            if computers[i][j]==1 and i!=j:
                computers[i][j] = 0 
                bfs(j,n,computers)
        return
    answer = 0
    visited = [False for _ in range(n)]
    for i in range(n):
        for j in range(n):
            if visited[i] == False and computers[i][j]==1:
                bfs(i,n,computers)
                answer+=1
    
    
    return answer