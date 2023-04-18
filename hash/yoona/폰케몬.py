from collections import defaultdict


def solution(nums):
    answer = 0

    hash_table = defaultdict(int)
    for num in nums:
        hash_table[num] += 1

    variety = len(hash_table.keys())
    answer = min(variety, len(nums) / 2)

    return answer
