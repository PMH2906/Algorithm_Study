n= int(input())

# dfs
# 백트래킹(dfs)으로 풀었다.
# 백트래킹은 답이 될 수 없는 후보는 더이상 깊게 들어가지않고 되돌아가는 방법을 의미
# 백트래킹은 모든 경우의 수를 탐색하는 브루트포스(brute force) 방법보다 훨씬 더 시간을 절약


# 스타트와 링크 두 팀으로 나누기 위하여 한 팀에 속하면 visited 리스트를 통하여 방문처리 해주면서 재귀함수 형태로 만든다.
# 만약 한 팀에 속한 팀원의 명수가 n//2로 다 채워졌으면 스타트팀의 능력치와 링크팀의 능력치를 구한다.
# 방문처리된 팀이 스타트팀이라고 하면, 방문처리 안된 팀이 링크팀이다. 이것을 이용해서 능력치를 구할 수 있다.
# 스타트팀의 능력치와 스타트팀의 능력치의 차이의 절대값과 최소 능력치값을 비교하면서 계속 갱신해준다.

def dfs(idx):
    global min_diff
    #백트래킹 답 체크 시점
    if idx == n//2:
        startSum, linkSum = 0, 0
        for i in range(n):
            if i not in start:
                link.append(i) # start 팀이 아니면 link에 넣어줌
        for i in range(0,n//2-1):
            for j in range(i+1,n//2): # start팀과 link팀의 팀 구성도는 n//2이므로, start(1, 2, 3)이면 (1,2) (1,3) (2,3)일때 더해주기 위해서 j는 i+1부터 시작
                startSum += info[start[i]][start[j]]+info[start[j]][start[i]]
                linkSum += info[link[i]][link[j]]+info[link[j]][link[i]]
            
        min_diff = min(min_diff, abs(startSum-linkSum)) #둘 비교
        link.clear() #링크팀은 항상 계산이 끝나면 비워줘야한다. 쌓이면 안되므로
        return

    #순열 대신 사용
    for i in range(n): 
        if i in start:continue
        if len(start)>0 and start[len(start)-1]>i:continue #현재 start가 없거나, 현재 인덱스보다 statr의 맨 뒤 요소가 크면 이미 본 순열 조합이므로 pass
        start.append(i)
        dfs(idx+1) # n//2이면 return으로 인해 start.pop() 코드 실행. 모든 경우의 수를 볼 수 O -> start:(0,1) pop후 ->start:(0) ->for문 돌면 start:(0,2) -> dfs()함수
        start.pop() # 모든 순열 돌아보기 위해서 

# visited = [False]*n
start =[]
link=[]
info = [list(map(int, input().split())) for _ in range(n)]

min_diff=int(1e9)

dfs(0)
print(min_diff)