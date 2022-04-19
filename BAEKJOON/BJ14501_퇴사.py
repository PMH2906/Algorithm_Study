n=int(input())

days =[]
for _ in range(n):
    days.append(list(map(int,input().split())))

pay = [0 for _ in range(n+1)]

for i in range(n-1,-1,-1): # 리스트를 역순으로 접근. 두번째 인자를 -1로 해주면 0까지 감.
    if i+days[i][0]>n: 
        continue
    else:
        pay[i] = days[i][1]+max(pay[i+days[i][0]:n+1])

print(max(pay))



