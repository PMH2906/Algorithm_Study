n = int(input())
g = list(map(int, input().split()))
m = int(input())

min_g = min(g)

if m <= min_g:
    result = m//n
    print(result)

else:
    start = max(min_g,m//n)
    end = max(max(g),m//n)

    while start<=end:
        money = 0
        mid = (start+end)//2
        
        for i in g:
            if i <= mid:
                money += i
            else:
                money += mid

        print(mid,money )
        if money > m:
            end = mid-1
        else:
            start = mid+1

    print(mid)
