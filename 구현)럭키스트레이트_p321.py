n = input()

half_length = len(n)/2
sum1=0
sum2=0
for num in range(len(n)):
    if num<half_length:
        sum1+=int(n[num])
    else :
        sum2+=int(n[num])

if sum1==sum2:
    print('LUCKY')
else:
    print('READY')