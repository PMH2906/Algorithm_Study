from collections import deque
n =int(input())
k = int(input())

udlr = [(-1,0),(1,0),(0,-1),(0,1)] #상하좌우
dl = ((3,2),(2,3),(0,1),(1,0)) #상하좌우를 볼때, 오른쪽 왼쪽 방향
nx, ny =0,0
maps = [[False]*n for _ in range(n)] 
maps[nx][ny] = True
x,y=0,0
tail=deque()

for _ in range(k):
    x,y=map(int,input().split())
    maps[x-1][y-1]='A'

l = int(input())

count=0 #시간
x,y=0,0 #현재 좌표
region = 3
start=0
tail.append((0,0)) #현재 꼬리 위치
for i in range(l):
    time, dir = input().split()
    time = int(time)
    for _ in range(start,100000000):
        if count == time: #입력한 시간을 만나면 방향 바꾸기
            region = dl[region][0] if dir=='D' else dl[region][1]
            start=time
            if i != l-1:
                break
        count+=1 # 시간 재기
        nx=x+udlr[region][0] # 현재 바라본 위치에 맞는 다음 이동 칸 설정
        ny=y+udlr[region][1]
        if nx<0 or nx>n-1 or ny<0 or ny>n-1: # 벽만나면 종료
            print(count)
            start=100000000
            break
        if maps[nx][ny] == True: #자기 꼬리 만나면 종료
            print(count)
            start=100000000
            break
        if maps[nx][ny] == False: # 빈칸이면 현재 꼬리 위치는 False, 다음 꼬리 위치 True하고 tail에 추가
            x,y=tail.popleft()
            maps[x][y] = False
            maps[nx][ny] = True
            tail.append((nx,ny))
            x,y=nx,ny
            continue 
        if maps[nx][ny] == 'A': # 사과면 다음 꼬리 위치 True, tail에 다음 꼬리 위치 추가만 하기
            maps[nx][ny] = True
            tail.append((nx,ny))
            x,y=nx,ny

        

    



