def sequential_search(n, target, array):
    for i in range(n):
        if array[i] == target:
            return i+1

    return -1

input_data = input().split() #원소 갯수, 찾을 문자열
n = int(input_data[0])
target =input_data[1]

array = input().split() #문자열입력

print(sequential_search(n,target,array))