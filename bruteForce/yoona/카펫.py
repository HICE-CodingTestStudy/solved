def solution(brown, yellow):
    answer = []

    plus = (brown - 4) // 2
    mul = yellow

    for x in range(1, yellow + 1):  # 1, 2
        y = plus - x
        if x * y == mul:
            answer.append(x + 2)
            answer.append(y + 2)
            break

    answer.sort(reverse=True)

    return answer
