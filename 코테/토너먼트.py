matchs = [-1,0,0,1,2,2,3,3,4,5]
me = 6
op = 9
#상대방의 위치에서 결승까지의 이길 경우, 상대방이 나갈 경기 리스트 저장 
ops = []
while True:
    ops.append(matchs[op])
    op=matchs[op]
    if op == -1: break

count=0

while True:
    #나의 위치에서 이길 경우, 다음으로 나갈 경기를 찾고
    me = matchs[me]
    #해당 경기가 상대방이 나가는 경기 리스트에 없고, 해당 위치로 가기 위한 match가 있는지 부전승인지
    # 확인하기 위해 matchs리스트에 해당 경기의 숫자가 2개면 경기 추가.  
    if me not in ops and matchs.count(me)==2:
        count+=1
    #만약 해당 경기가 상대방이 나가는 경기 리스트에 있다면, 그 경기에서 마주침. 따라서 while문에서 나가고
    if me in ops:
        break
    #해당 경기에서도 이겨야하므로 +1을 해주었음
print(count+1)

