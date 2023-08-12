def solution(sizes):
    answer = 0
    temp = 0

    garo = []
    sero = []
    for i in sizes:
        if i[0] < i[1]:
            temp = i[0]
            i[0] = i[1]
            i[1] = temp
        garo.append(i[0])
        sero.append(i[1])
    answer = max(garo)*max(sero)

    return answer


print(solution([[60, 50], [30, 70], [60, 30], [80, 40]]))
print(solution([[10, 7], [12, 3], [8, 15], [14, 7], [5, 15]]))
print(solution([[14, 4], [19, 6], [6, 16], [18, 7], [7, 11]]))
