from itertools import permutations


def solution(k, dungeons):
    answer = -1
    answers = []
    pDungeons = list(permutations(dungeons, len(dungeons)))

    for i in pDungeons:
        tempAns = 0
        tempK = k
        for j in i:
            if tempK < j[0]:
                break
            else:
                tempAns += 1
                tempK -= j[1]

        answers.append(tempAns)
    answer = max(answers)
    return answer


print(solution(80, [[80, 20], [50, 40], [30, 10]]))
