from cv2 import split


def solution(today, terms, privacies):
    answer = []

    todayY, todayM, todayD = map(int,today.split('.'))
    for nth, pri in enumerate(privacies):
        day, type = pri.split(' ')
        y, m, d = map(int,day.split('.'))
        for term in terms:
            type2, month = term.split(' ')
            if type==type2 :
                break
        
        month=int(month)
        print(month)
        y+=int(month/12)
        month-=12*int(month/12)

        if m+month>12:
            y+=1
            m=(m+month)%12
        else: m+=month
        
        if todayY==y: 
            if todayM == m:
                if todayD >= d :answer.append(nth+1)
            elif todayM> m : answer.append(nth+1)
        elif todayY>y : answer.append(nth+1)
        print(todayY,todayM ,todayD,month,y,m,d )
    return answer


print(solution("2022.05.19", ["A 6", "B 12", "C 3"], ["2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"]))