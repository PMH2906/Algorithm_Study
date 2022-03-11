color = ["RG", "WR", "BW", "GG"]
price= [5000, 6000]

color = list(map(list,color))
noncolor =[]

#하의와 상의를 비교해서 같은 색상이 있다면 각은 set의 가격을 측정하고(count를 세어주고), 
#측정한 모든 하의와 상의는'N'으로 초기화 시켜주었다.
#없은 하의와 상의는 새로운 리스트를 만들어 추가해주었다.
#이때 다른 색상의 set를 사는 것보다 같은 색상의 set를 2개 사는 것이 저렴할 수 있기 때문에
#해당 알고리즘을 사용하여 확인하고 더 저렴한 가격을 더해서 최종 가격을 측정했다.
for i in range(len(color)):
    for j in range(len(color)):
        color[i][0] ==

#더 효율적으로 짤 수 없는지 
