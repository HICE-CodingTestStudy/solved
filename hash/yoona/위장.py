from collections import defaultdict


def solution(clothes):
    answer = 1

    hash_table = defaultdict(list)

    for cloth in clothes:
        name, sort = cloth[0], cloth[1]
        hash_table[sort].append(name)

    for key, value in hash_table.items():
        answer *= len(value) + 1

    return answer - 1
