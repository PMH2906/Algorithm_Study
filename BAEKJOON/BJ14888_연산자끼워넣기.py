import sys
minvalue = 2e9
maxvalue = -2e9
input = sys.stdin.readline
n = int(input())

num = list(map(int, input().split()))
a, b, c, d = map(int, input().split())

def cal(i,a,b,c,d,result):
    
    if i >= n-1:
        global minvalue, maxvalue
        minvalue = min(minvalue, result)
        maxvalue = max(maxvalue, result)
        return
    
    if a>0:
        cal(i+1,a-1,b,c,d,result+num[i+1])
    if b>0:
        cal(i+1,a,b-1,c,d,result-num[i+1])
    if c>0:
        cal(i+1,a,b,c-1,d,result*num[i+1])
    
    if d>0:
        if (result<0):
            cal(i+1,a,b,c,d-1,((result*-1)//num[i+1])*-1)
        else:
            cal(i+1,a,b,c,d-1,result//num[i+1])

cal(0, a, b, c, d, num[0])
print(maxvalue)
print(minvalue)
