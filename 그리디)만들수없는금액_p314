n = int(input())

money = list(map(int, input().split()))
target = 0
sum = 0
i = 0
result = 0
money.sort()

while True:
    target += 1
    i=0
    sum=0
    for num in money:
        sum += num
        i+= 1
        if target == sum:
            break
        elif target < sum:
            for index in range(i):
                sum -= money[index]
                if sum == target:
                    break
                elif index == i-1 and sum < target:
                    result = target
            break
        else:
            continue 
    if result != 0:
        break

print(result)