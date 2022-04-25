# r, c, k = map(int, input().split())

# # graph=[list(map(int,input().split())) for _ in range(3)]
# sort_graph = [[0] * 6 for _ in range(6)]
# sort_data=[]

# for i in range(3):
#     data = list(map(int,input().split()))
#     for j in range(3):
#         sort_graph[i][j] = data[j]

# time=0
# row = 3
# col=3

# sort_data=[]

# while True:
#     print(row,col)
#     if time == 0 and sort_graph[r-1][c-1] == k:
#         print(time)
#         break
#     # sort_graph = [[0] * 6 for _ in range(6)]
#     time+=1
#     row_m =0
#     col_m = 0
#     if row >= col:
#         for i in range(6):
#             col_c=0
#             data = sort_graph[i]
#             for j in range(1,4):
#                 if data.count(j)!= 0 :
#                     sort_data.append((j, data.count(j)))
#                     col_c+=1
#                 else:
#                     sort_data.append((7,7))
#             sort_data.sort(key=lambda x: (x[1],x[0]))
#             print(sort_data)
#             col_m = max(col_c,col_m)
#             for y in range(6):
#                 sort_graph[i][y] = 0
#             for j in range(3):
#                 if sort_data[j][0] + sort_data[j][1] == 14:
#                     continue
#                 else : 
#                     sort_graph[i][j*2] = sort_data[j][0]
#                     sort_graph[i][j*2+1] = sort_data[j][1]
#             sort_data.clear()
#         col=col_m*2
        
#     else :
#         for i in range(6):
#             row_c=0
#             data=[sort_graph[0][i],sort_graph[1][i],sort_graph[2][i],sort_graph[3][i],sort_graph[4][i],sort_graph[5][i]]
#             # for j in range(6):
#             #     sort_graph[j][i] = 0
#             for j in range(1,4):
#                 if data.count(j)!= 0 :
#                     sort_data.append((j, data.count(j)))
#                     row_c+=1
#                 else:
#                     sort_data.append((7,7))

#             sort_data.sort(key=lambda x: x[1])
#             print(sort_data)
#             row_m = max(row_c, row_m)

#             for x in range(6):
#                 sort_graph[x][i] = 0

#             for j in range(3):
#                 if sort_data[j][0] + sort_data[j][1] == 14:
#                     continue
#                 else:
#                     sort_graph[j*2][i] = sort_data[j][0]
#                     sort_graph[j*2+1][i] = sort_data[j][1]
#             sort_data.clear()
#         row=row_m*2

#     print(sort_graph)
#     if sort_graph[r-1][c-1] == k:
#         print(time)
#         break
#     elif time == 10:
#         print(-1)
#         break
# 내가 잘 못 한 것 : 최초의 n=3이여서 최대로 커질 수 있는 이중리스트의 길이가 6인줄 알았음


# print(row,col)
# 참고자료 : https://pacific-ocean.tistory.com/380
from collections import Counter
# rc 계산 방법 
def rc():
    max_len=0
    len_s = len(s)
    for j in range(len_s): # 1. s 에서 한줄씩  가져옴
        a = [i for i in s[j] if i != 0] # 2. s 에서 데이터가 0이 아닌 부분을 a로 지정. why? 0은 무시하므로
                                        # 여기서 a는 정렬할 데이터(요소만) => 리스트
        a = Counter(a).most_common() # 3. a의 요소 기준으로 a에 해당 요소가 총 몇번 나왔는지 (요소, 카운트출력) => 이중리스트
        a.sort(key=lambda x: (x[1],x[0])) # 4.sort
        s[j] = [] # 4. 새로운 데이터 넣기 위해 기존의 데이터 reset

        for num, count_num in a:
            # 5. 다음 데이터는 요소와 요소의 count 모두가 요소가 되므로 해당 열에 데이터 추가
            # 이때 앞에 데이터 reset되었으므로 이 과정에서 추가되는 수만 요소가 됨
            s[j].append(num)
            s[j].append(count_num)
        # 6. 행 or 열의 최대 수 구하기
        # a에 추가된 요소가 많을수록 해당 줄의 가장 큰 행 or 열이 됨
        len_a = len(a)
        if max_len <len_a*2 : max_len=len_a*2 #max_len = max(max_len,len_a*2)

    # 7. 가장 긴 길이의 행 혹은 열 빼고 나머지 행 혹은 열은 0이 존재함. 행(s[j]) 마다 넣어주는 것
    # 이 과정은 각 time마다 최고의 행or 열 길이를 구하고 진행해야 함
    for j in range(len_s):
        for k in range(max_len-len(s[j])):
            s[j].append(0)
        s[j] = s[j][:100]

r, c, k = map(int, input().split())
s=[list(map(int,input().split())) for _ in range(3)]

for i in range(101):
    if r<=len(s) and c<=len(s[0]):
        if s[r-1][c-1] == k :
            print(i)
            break
    if i == 100:
        print(-1)
        break  
    if len(s)<len(s[0]): # len(s)는 행, len(s[0])은 열 의미, 열이 행 보다 크면
        
        # zip : a = [1, 2, 3] b =[a, b, c] zip(a,b) = (1,a) (2, b) (3 c)
        # *s : 2중 리스트 zip([[1,2,3], [4, 5, 6], [7, 8, 9])
        # => [1, 4, 7] [2, 5, 8] [3, 6, 9]
        s=list(zip(*s)) 
        rc()
        s=list(zip(*s))
    else:
        rc()
    






