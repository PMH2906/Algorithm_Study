from collections import deque
def solution(tickets):
    
    visited=[0 for _ in range(len(tickets))]
    def bfs(tickets,visited):
        q = deque()
        result = ['ICN']
        check = 'ICN'
        for i in range(len(tickets)):
                if tickets[i][0] in check:
                    q.append((tickets[i][0], tickets[i][1],i))
        while q:
            a,b,index = q.pop()
            while q:
                a_n, b_n,index_n = q.pop()
                if b[0] > b_n[0]:
                    a, b, index= a_n, b_n,index_n
                elif b[0] == b_n[0]:
                    if b[1] > b_n[1]:
                        a, b, index= a_n, b_n,index_n
                    elif b[1] == b_n[1]:
                        if b[2] > b_n[2]:
                            a, b, index= a_n, b_n,index_n     
                            
            visited[index] = 1
            check = b
            result.append(check)
            for i in range(len(tickets)):
                if tickets[i][0] in check and visited[i] == 0:
                    q.append((tickets[i][0], tickets[i][1],i))
        return result
    answer = bfs(tickets, visited)
            
    return answer