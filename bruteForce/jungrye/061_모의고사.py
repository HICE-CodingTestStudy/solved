def solution(answers):
    answer = []

    supoza1 = [1, 2, 3, 4, 5]
    supoza2 = [2, 1, 2, 3, 2, 4, 2, 5]
    supoza3 = [3, 3, 1, 1, 2, 2, 4, 4, 5, 5]
    right1 = 0
    right2 = 0
    right3 = 0

    for i in range(len(answers)):
        if answers[i] == supoza1[i % 5]:
            right1 += 1
        if answers[i] == supoza2[i % 8]:
            right2 += 1
        if answers[i] == supoza3[i % 10]:
            right3 += 1

    temp = [right1, right2, right3]
    maxCount = max(temp)

    for i in range(len(temp)):
        if temp[i] == maxCount:
            answer.append(i+1)
    return answer


print(solution([1, 2, 3, 4, 5]))
print(solution([1, 3, 2, 4, 2]))
