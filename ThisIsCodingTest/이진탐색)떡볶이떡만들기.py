n, m = map(int, input().split())
g = list(map(int, input().split()))
result = 0
start = 0
end = max(g)

while start<=end:
    total = 0
    mid = (start+end)//2
    
    for i in g:
        if i > mid:
            total += i - mid
    if total < m :
        end = mid-1
    else:
        result = mid #최대한 덜 잘랐을 때가 정답이므로, 여기에서 result에 기록(중요) 
        start = mid+1

print(result)
