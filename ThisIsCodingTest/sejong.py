# Type or paste your code in this area
# Type or paste your code in this area
n=int(input())
# O을 n*n만큼 그리기
graph=[["O"]*n for _ in range(n)]
m=int(input())
# X 표 그리기
for i in range(m):
    a,b=map(int,input().split())
    graph[a][b]="X"

# 지도 리스트를 string으로 변경해주는 것
for i in graph:
    a=""
    for j in i:
        a+=j
    print(a)

# 초기 좌표 초기화
x,y=0,0
while True:
    if(y+1==n):
        if(x+1==n):
            print("finish")
            break
        # 아래로 이동
        elif(graph[x+1][y]=="O"):
            print("Down")
            x,y=x+1,y
        elif(graph[x+1][y]=="X"):
            print("error")
            break
    elif(x+1==n):
        if(y+1==n):
            print("finish")
            break
        # 옆으로 이동
        elif(graph[x][y+1]=="O"):
            print("Right")
            x,y=x,y+1
        elif(graph[x][y+1]=="X"):
            print("error")
            break
    elif x>=0 and x<n and y>=0 and y<n:
        # 오른쪽으로 이동
        if (graph[x][y+1]=="O"):
            print("Right")
            x,y=x,y+1
        # 오른쪽이 막히면?
        elif (graph[x][y+1]=="X"):
            # 아래로 이동
            if(graph[x+1][y]=="O"):
                print("Down")
                x,y=x+1,y
            # 아래도 막혀있으면
            elif (graph[x+1][y]=="X"):
                print("error")
                break
