from matplotlib.pyplot import inferno


n=int(input())
pInfo = []
students = []
position=[[0]*n for _ in range(n)] 
like = [[0]*n for _ in range(n)] 
dx=[-1, 0, 0, 1]
dy=[0, -1, 1, 0]

def count(x,y,info):
    emtyCount = 0
    likeCount = 0
    if (x>=0 and x<n) and (y>=0 and y<n):
        if position[x][y] == 0:
            for j in range(4):
                if (x+dx[j]>=0 and x+dx[j]<n) and (y+dy[j]>=0 and y+dy[j]<n):
                    if position[x+dx[j]][y+dy[j]] == 0:
                        emtyCount += 1
                    if position[x+dx[j]][y+dy[j]] in info:
                        likeCount += 1
        else:
            return False
    else:
        return False
    return (emtyCount, likeCount, x,y)

def likeSum(x,y,info):
    likeCount = 0
    for j in range(4):
        if (x+dx[j]>=0 and x+dx[j]<n) and (y+dy[j]>=0 and y+dy[j]<n):
            if position[x+dx[j]][y+dy[j]] in info:
                likeCount += 1
    return likeCount


for i in range(n*n):
    info = list(map(int,input().split()))
    students.append(info[0])
    x=int(-1e9)
    y=int(-1e9)
    max_like = -1
    max_empty = -1

    if i==0:
        position[1][1]=info[0]
        pInfo.append((1,1))
        like[1][1] = info[1:5]
    else:
        for idx, student in enumerate(students[0:i+1]):
            if student in info[1:5]:
                for j in range(4):
                    if count(pInfo[idx][0]+dx[j],pInfo[idx][1]+dy[j],info[1:5]) == False:
                        emtyCount, likeCount, x, y = max_like, max_empty, x, y 
                    else:
                        emtyCount, likeCount, x, y = count(pInfo[idx][0]+dx[j],pInfo[idx][1]+dy[j],info[1:5])
                        if max_like <= likeCount:
                            if max_like == likeCount:
                                if max_empty < emtyCount:
                                    max_empty=max(max_empty, emtyCount)
                                    nx, ny = x,y
                                elif max_empty == emtyCount:
                                    max_v = min([x,y],[nx,ny])
                                    nx,ny=max_v[0],max_v[1]
                            else:
                                nx, ny = x,y
                                max_like=max(max_like, likeCount)  
                                max_empty=max(max_empty, emtyCount) 
                            

                if max_empty== -1 and max_like ==-1:
                    p = [[x, y] for x in range(n) for y in range(n) if position[x][y]==0]
                    nx=p[0][0]
                    ny=p[0][1]                 
        position[nx][ny]=info[0]
        pInfo.append((nx,ny))
        like[nx][ny] = info[1:5]

sums = [0, 1, 10, 100,1000]
sum = 0
for i in range(n):
    for j in range(n):
        likeCount=likeSum(i,j,like[i][j])
        sum+=sums[likeCount]

print(sum)


