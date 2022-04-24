# 0 : 빈칸, 1 : 집, 2 : 치킨
from itertools import combinations
n, m = map(int, input().split())
maps = [list(map(int,input().split())) for _ in range(n)]
result = 1e9
house=[]
chick=[]

# 치킨집과 집의 좌표 찾기
for i in range(n): 
    for j in range(n):
        if maps[i][j] == 1:
            house.append([i,j])
        elif maps[i][j] == 2:
            chick.append([i,j])

for chi in combinations(chick, m): # m개의 치킨집 선택, chi에는 [[i,j] .. m개의 좌표 들어감]
    temp = 0
    for h in house:
        chi_dis = 999
        for j in range(m):
            # 집에서 가장 가까운 치킨 집 거리
            chi_dis = min(chi_dis,(abs(h[0]-chi[j][0])+abs(h[1]-chi[j][1])))

        # 각각의 모든 집에서 가장 가까운 치킨 집 거리를 더함    
        temp+=chi_dis
    # 조합을 사용하여 모든 치킨 집 최소 거리 중 가장 작은 값 찾기
    result = min(result, temp)

print(result)

