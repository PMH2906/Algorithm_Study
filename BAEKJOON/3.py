
from itertools import product

def solution(users, emoticons):
    answer = []
    sale = [10, 20, 30, 40]
    
    saledata = product(sale, repeat=len(emoticons))
   
    registerMax=0
    emoticonPriceMax=0

    for data in saledata:
    
        register=0
        emoticonPrice=0
        for user in users:
            totalprice=0
            for idx, emoticon in enumerate(emoticons):
                if user[0]<=data[idx]:
                    price = emoticon*((100-data[idx])/100)
                    totalprice+=price
            if totalprice>=user[1]:
                register+=1
            else: 
                emoticonPrice+=totalprice

        if register>registerMax:
            registerMax=register
            emoticonPriceMax=emoticonPrice

        elif register==registerMax:
            if emoticonPriceMax<=emoticonPrice:
                emoticonPriceMax=emoticonPrice
                registerMax=register
     

    answer.append(registerMax)
    answer.append(int(emoticonPriceMax))
    return answer

print(solution([[40, 2900], [23, 10000], [11, 5200], [5, 5900], [40, 3100], [27, 9200], [32, 6900]], [1300, 1500, 1600, 4900]))



# print(solution([[40, 10000], [25, 10000]], [7000, 9000]))