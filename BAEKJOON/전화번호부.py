import sys

t = int(input())
num = [[] for _ in range(t)]

def check(array):
    array.sort()
    for i in range(0,len(array)-1):
        if array[i] == array[i+1][0:len(array[i])]:
            print('NO')
            return
    print('YES')

for i in range(t):
    n = int(input())
    for _ in range(n):
        num[i].append(sys.stdin.readline().strip())
    check(num[i])
