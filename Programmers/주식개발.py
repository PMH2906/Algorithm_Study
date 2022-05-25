from collections import deque
def solution(prices):
    answer=[] 
    q = deque(prices)
    # for i in range(len(prices)):
    #     count = 0
    #     for j in range(i+1,len(prices)):
    #         if prices[i] <= prices[j] :
    #             count+=1
    #         else:
    #             count+=1
    #             break
    #     answer.append(count)

    while q:
        count = 0
        now_price = q.popleft()
        for next_price in list(q):
            if now_price <= next_price:
                count+=1
            else:
                count+=1
                break
        answer.append(count)
        
    return answer