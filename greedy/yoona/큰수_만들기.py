# 해설지 참조


def solution(number, k):
    answer = []

    for idx, num in enumerate(number):
        while k > 0 and answer and answer[-1] < num:
            answer.pop()
            k -= 1
        answer.append(num)

    answer = "".join(answer)
    return answer
