def solution(word):
    answer = 0
    order = 0

    def permutation(choosed):
        nonlocal order, answer

        if "".join(choosed) == word:
            answer = order

        if len(choosed) == 5:
            return

        for alphabet in ["A", "E", "I", "O", "U"]:
            order += 1
            choosed.append(alphabet)
            permutation(choosed)
            choosed.pop()

    choosed = []
    permutation(choosed)
    return answer
