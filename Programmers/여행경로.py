from collections import defaultdict
def solution(tickets):
    answer = []
    routes = defaultdict(list) #사전 기본값으로 빈 리스트 생성
    for ticket in tickets:
        routes[ticket[0]].append(ticket[1])

    for key in routes.keys():
        routes[key].sort(reverse=True) #해당 key의 value 내림차순 정렬

    stack = ['ICN'] #출발지점
    while stack:
        tmp = stack[-1] #top stack

        if not routes[tmp]: #해당 출발 지점의 도착 정보가 더 이상 없을 경우, 차례로 answer에 넣어줌 
            answer.append(stack.pop()) 
        else:
            stack.append(routes[tmp].pop()) #해당 출발 지점의 도착 정보를 stack에 넣어줌
    answer.reverse() #즉, stack의 역순이 여행 경로

    return answer