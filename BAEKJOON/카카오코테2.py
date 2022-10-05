import sys
sys.setrecursionlimit(10**6)
def dfs(x, y, path, n, m, k, r, c):
    if x<0 or x>=n or y<0 or y>=m:
        return False
    if len(path) == k and x==r and y==c:
        global answer
        answer=path
        return True
    elif len(path)==k and (x!=r or y!= c): return False

    if dfs(x+1, y, path+"d", n, m, k, r, c): return True
   
    if dfs(x, y-1, path+"l", n, m, k, r, c): return True
  
    if dfs(x, y+1, path+"r", n, m, k, r, c): return True
   
    if dfs(x-1, y, path+"u", n, m, k, r, c): return True
  
    return False
def solution(n, m, x, y, r, c, k):

    path=""
    dist = abs(x-r)+abs(y-c)
    if (dist-k)%2==1 : return "impossible"
    if dfs(x-1,y-1,path, n, m, k, r-1, c-1) : return answer
    else : return "impossible"





def dfs(x, y, path, n, m, k, r, c, dist):
   
    if x<0 or x>=n or y<0 or y>=m:
        return False
    if len(path) > dist: return False
    if x==r and y==c:
        global answer
        answer=path
        return True

    if dfs(x+1, y, path+"d", n, m, k, r, c,dist): return True
   
    if dfs(x, y-1, path+"l", n, m, k, r, c,dist): return True
  
    if dfs(x, y+1, path+"r", n, m, k, r, c,dist): return True
   
    if dfs(x-1, y, path+"u", n, m, k, r, c,dist): return True
  
    return False
def solution(n, m, x, y, r, c, k):

    path=""
    dist = abs(x-r)+abs(y-c)
    if (dist-k)%2==1 : return "impossible"
    
    if dfs(x-1,y-1,path, n, m, k, r-1, c-1,dist) :
        
        if (k-len(answer))%2 == 1 :return "impossible"

        if len(answer) == k: return answer
        if r-1+1<n: return answer+"du"*int((k-len(answer))/2)
        if c-1-1>=0 : return answer+"lr"*int((k-len(answer))/2)
        if c-1+1<n : return answer+"rl"*int((k-len(answer))/2)
        if r-1-1>=0 : return answer+"ud"*int((k-len(answer))/2)
    else : return "impossible"



# print(solution(3, 3, 1, 2, 3, 3, 4))
print(solution(3, 4, 2, 3, 3, 1, 7))
# print(solution(2, 2, 1, 1, 2, 2, 2500))
