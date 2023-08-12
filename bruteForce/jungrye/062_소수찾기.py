from itertools import permutations
import math


def solution(numbers):
    answer = 0
    combination = []
    numbersList = list(numbers)

    for i in range(1, len(numbersList)+1):
        for j in permutations(numbersList, i):
            if int(''.join(j)) in combination:
                continue
            else:
                combination.append(int(''.join(j)))
    # print(combination)

    for i in combination:
        if i == 1 or i == 0:
            continue
        isPrime = True
        for j in range(2, i//2+1):
            # print("i,j:", i, j)
            if i % j == 0:
                print(f'{i}%{j}==0')
                isPrime = False
                break
        if (isPrime):
            print(f'{i} is a primenumber')
            answer += 1

    return answer


print(solution("17"))
print(solution("011"))
print(solution("1231"))
