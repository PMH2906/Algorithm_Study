def solution(record):
    answer = []
    userDB = dict() #사전자료형
    #사전자료형 data => {'key값':'value값'}
    #접근법 data[key값] => 해당 key값의 value값 출력
    actions = [] #[(action, userid)]

    for event in record:
        info = event.split(" ") #문자열을 해당 문자로 잘라 리스트로 변환
        action, userid = info[0], info[1]
        if action in ("Enter","Change"):
            nickname = info[2]
            userDB[userid] = nickname #Enter와 Change시 해당 Userid를 기준으로 nickname을 최근 걸로 모두 바꿈
        actions.append((action,userid)) #모든 행동과 userid만 저장
        
    for actionInfo in actions:
        action, userid = actionInfo[0], actionInfo[1]
        if action == 'Enter':
            answer.append(f'{userDB[userid]}님이 들어왔습니다.') #f-string 포매팅
        elif action == 'Leave':
            answer.append(f'{userDB[userid]}님이 나갔습니다.') #f-string 포매팅
    return answer