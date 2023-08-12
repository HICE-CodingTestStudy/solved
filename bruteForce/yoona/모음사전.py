from itertools import product

# product는 중복순열 product(list, repeat = 몇 개로 이루어진 순열?)


def solution(word):
    answer = 0
    alphabet = ["A", "E", "I", "O", "U"]
    tmp = []

    for i in range(1, 6):
        for j in product(alphabet, repeat=i):
            tmp.append("".join(list(j)))

    tmp.sort()
    answer = tmp.index(word) + 1
    return answer
