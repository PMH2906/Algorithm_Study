import sys
input = sys.stdin.readline
n = int(input())
data = list(map(int,input().split()))
min_sum = 2e9

data.sort()

left = 0
right = n-1

final = []

while left<right:
    left_v = data[left]
    right_v = data[right]

    sum = left_v+right_v

    if abs(sum)<min_sum:
        min_sum = abs(sum)
        final = [left_v, right_v]

    if sum<0:
        left+=1
    else :
        right-=1
    
print(final[0], final[1])