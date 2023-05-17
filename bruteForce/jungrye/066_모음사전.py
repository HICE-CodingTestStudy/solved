from itertools import product


def solution(word):
    answer = 0
    alphabets = ['A', 'E', 'I', 'O', 'U']
    dictionary = []
    for i in range(1, 6):
        for j in product(alphabets, repeat=i):
            str = ''.join(j)
            dictionary.append(str)
    dictionary.sort()
    answer = dictionary.index(word)+1
    return answer


print(solution('AAAAE'))
print(solution("AAAE"))
print(solution('I'))
print(solution('EIO'))
