def transformation(N,B): # 재귀함수 사용
    # 변환할 수를 리스트로 변환 한 후, XXXXX => 맨 뒤의 자리 탐색
    # EX ) 12345 2진법

    # 12345
    # 1 탐색(항상 맨 뒤의 자리를 탐색) => 1 * (2**4) : 탐색숫자 * (B(진법)**(리스트 길이 -1))
    # 탐색한 후 => 다시 함수 불러서 탐색할 숫자에서 1을 뺀 2345를 N에 삽입

    # 2345
    # 2 탐색(항상 맨 뒤의 자리를 탐색) => 2 * (2**3) : 탐색숫자 * (B(진법)**(리스트 길이 -1))
    # 탐색한 후 => 다시 함수 불러서 탐색할 숫자에서 2를 뺀 345를 N에 삽입

    # N(리스트)에 숫자가 없을 때까지 반복.... 과정에서 계산된 값을 모두 sum

    if N:
        if '0'<= N[0] <='9': 
    
            return transformation(N[1:len(N)],B)+int(N[0])*(B**(len(N)-1)) # chr(정수) : 정수 -> 정수에 맞는 아스키코드 반환 / ord("str") : str -> 문자에 해당하는 아스키코드로 반환

        else:
            
            return transformation(N[1:len(N)],B)+(10+(ord(N[0])-ord('A')))*(B**(len(N)-1))
    else :
        return 0

N, B = input().split() # 입력

N = list(N) # 변환할 숫자
B = int(B) # B진법


print(transformation(N,B)) 