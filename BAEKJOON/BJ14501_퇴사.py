n=int(input())

days =[]
for _ in range(n):
    days.append(list(map(int,input().split())))

pay = [0 for _ in range(n+1)]
# days[i][0] : 소요시간
# days[i][1] : 돈
for i in range(n-1,-1,-1): # 리스트를 역순으로 접근. 두번째 인자를 -1로 해주면 0까지 감.
    if i+days[i][0]>n: 
        continue
    else:
        # 방법 1
        pay[i] = days[i][1]+max(pay[i+days[i][0]:n+1]) #해당 인덱스에서 필요한 기간 이후의 pay에서 가장 큰 값을 더해줌
        # 방법 2
        # max(day[i][1] + pay[i + day[i][1]], pay[i + 1])
        # max의 첫번째 인자 : [현재 일을 맡았을 때 들어오는 보상 + 현재 일을 끝낸 다음날에 쌓여있는 보상]
        # max의 두번째 인자 : 해당 인덱스에서 일을 맡지 않을 경우 보상
        # 둘을 비교하여 큰 값을 받아줌
print(max(pay))



