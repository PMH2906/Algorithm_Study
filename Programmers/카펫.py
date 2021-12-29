def solution(brown, yellow):
    answer = []
    for i in range(yellow,0,-1):
        if yellow%i == 0:
            if brown == (((i+2)*(yellow/i+2))-yellow):
                answer.append(i+2)
                answer.append(yellow/i+2)
                return answer