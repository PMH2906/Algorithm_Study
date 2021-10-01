from itertools import combinations

data = input()

result = []
sum = 0

for x in data:
    if x.isalpha(): #알파벳인 경우
        result.append(x)
    else:
        sum += int(x)
result.sort()
if sum != 0:
    result.append(str(sum))

print('.'.join(result)) #리스트를 문자열로 변환하여 출력 if. '.'.join(result)라면 A.B.C.K.K.13으로 출력

#입력값 : K1KA5CB7