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


'''
from collections import deque
def solution(n, computers):
    answer = 0
    visited = [0 for _ in range(n)]
    def bfs(node):
        q = deque()
        q.append(node)
        
        while q:
            node = q.popleft()
            for i in range(n):
                if computers[node][i] == 1 and node != i:
                    computers[node][i]=0
                    visited[i] = 1
                    q.append(i)
                    
    for i in range(n):
        if visited[i] == 0:
            visited[i]=1
            bfs(i)
            answer+=1
        
    return answer
    '''

