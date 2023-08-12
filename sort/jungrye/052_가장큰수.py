def solution(numbers):
    answer = ''

    tempNums = []
    change = {}
    for i in range(len(numbers)):
        strI = str(numbers[i])
        if len(strI) == 1:
            strI = strI+strI+strI+strI+'*'
        elif len(strI) == 2:
            if strI[0] > strI[1]:
                strI = strI+strI[0]+strI[0]+'**'
            else:
                strI = strI+strI[1]+strI[1]+'**'
        elif len(strI) == 3:
            strI = strI+strI[2]+'***'
        tempNums.append(strI)
    temp = sorted(tempNums, reverse=True)
    for i in range(len(temp)):
        if '***' in temp[i]:
            temp[i] = temp[i][0:3]
        elif '**' in temp[i]:
            temp[i] = temp[i][0:2]
        elif '*' in temp[i]:
            temp[i] = temp[i][0:1]
    answer = ''.join(s for s in temp)
    if answer[0] == '0':
        answer = '0'
    return answer


print(solution([6, 10, 2]))
print(solution([3, 30, 34, 5, 9]))
print(solution([0, 0]))
print(solution([1, 10, 100, 1000, 818, 81, 898, 89, 0, 0]))
print(solution([979, 97, 978, 81, 818, 817]))  # 9799797 881881 817
