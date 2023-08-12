from math import sqrt


def solution(numbers):
    answer = []
    number_list = [number for number in numbers]  # 문자열을 리스트로 변환

    def permutation(choosed, used):
        if choosed:
            num = int("".join(choosed))

            chk = True
            for i in range(2, int(sqrt(num)) + 1):
                if num % i == 0:
                    chk = False
                    break

            if chk and num > 1:
                answer.append(num)

        if len(choosed) == len(numbers):
            return

        for idx in range(len(number_list)):
            if not used[idx]:
                choosed.append(number_list[idx])
                used[idx] = 1
                permutation(choosed, used)
                used[idx] = 0
                choosed.pop()

    choosed = []
    used = [0 for _ in range(len(number_list))]
    permutation(choosed, used)

    return len(set(answer))
