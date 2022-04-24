from collections import deque
n =int(input())
k = int(input())

udlr = [(-1,0),(1,0),(0,-1),(0,1)]
dl = ((3,2),(2,3),(0,1),(1,0))
nx, ny =0,0
maps = [[False]*n for _ in range(n)] 
maps[nx][ny] = True
x,y=0,0
tail=deque()

for _ in range(k):
    x,y=map(int,input().split())
    maps[x-1][y-1]='A'

l = int(input())


count=0
x,y=0,0
sum_time=0
time=0
dir=False
region = 3
start=0
tail.append((0,0))
for i in range(l):
    time, dir = input().split()
    time = int(time)
    for _ in range(start,100000000):
        if count == time:
            region = dl[region][0] if dir=='D' else dl[region][1]
            start=time
            if i != l-1:
                break
        count+=1
        nx=x+udlr[region][0]
        ny=y+udlr[region][1]
        if nx<0 or nx>n-1 or ny<0 or ny>n-1:

            print(count)
            start=100000000
            break
        if maps[nx][ny] == True:

            print(count)
            start=100000000
            break
        if maps[nx][ny] == False:

            x,y=tail.popleft()
            maps[x][y] = False
            maps[nx][ny] = True
            tail.append((nx,ny))
            x,y=nx,ny
            continue
        if maps[nx][ny] == 'A':

            maps[nx][ny] = True
            tail.append((nx,ny))
            x,y=nx,ny

        

    



