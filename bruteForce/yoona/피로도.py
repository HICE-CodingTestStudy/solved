def solution(k, dungeons):
    answer = -1

    def permutation(used, choosed, fatigue):
        nonlocal answer

        if len(choosed) == len(dungeons):
            num = 0
            for choose in choosed:
                x, y = choose
                if fatigue >= x and fatigue - y >= 0:
                    fatigue -= y
                    num += 1
            answer = max(num, answer)

        else:
            for idx in range(len(dungeons)):
                if not used[idx]:
                    choosed.append(dungeons[idx])
                    used[idx] = 1
                    permutation(used, choosed, fatigue)
                    used[idx] = 0
                    choosed.pop()

    used = [0 for _ in range(len(dungeons))]
    choosed = []

    permutation(used, choosed, k)

    return answer
