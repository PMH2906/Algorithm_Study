from collections import deque
def solution(bridge_length, weight, truck_weights):
    
    truck_weights = deque(truck_weights)
    bridge = deque([truck_weights.popleft()])
    answer = 1
    
    while truck_weights:
        truck_weight = truck_weights.popleft()
        
        if len(bridge) < bridge_length:
            if sum(bridge)+truck_weight > weight:
                bridge.popleft
                count+1
                
                if sum(bridge)+truck_weight > weight:
                    bridge.append()
        
    return answer




from collections import deque
def solution(bridge_length, weight, truck_weights):
    
    truck_weights = deque(truck_weights)
    bridge = deque([(truck_weights.popleft(),bridge_length-1)])
    answer = 1
    while truck_weights:
        truck_weight = truck_weights.popleft() # 다음 트럭
        if sum(bridge) + truck_weight > weight:
            pass_truck = bridge.popleft() # 지니간 트럭
            if sum(bridge) + truck_weight < weight:
                bridge.append(truck_weight)
            answer+= pass_truck[1]+1
        
    
    
    print(bridge)
        
    return answer




    from collections import deque
def solution(bridge_length, weight, truck_weights):
    
    truck_weights = deque(truck_weights)
    bridge = deque([0 for _ in range(bridge_length)])
    answer = 0
    while truck_weights:
        truck_weight=truck_weights.popleft()
        if sum(bridge)+truck_weight <= weight:
            answer+=1
            bridge.popleft()
            bridge.append(truck_weight)
        else:
            while True:
                bridge.popleft()
                bridge.append(0)
                answer+=1
                if sum(bridge)+truck_weight <= weight:
                    bridge.popleft()
                    bridge.append(truck_weight)
                    break
    if sum(bridge) != 0:
        while bridge:
            bridge.popleft()
            answer+=1
            if sum(bridge) == 0:
                break
            
        
    return answer