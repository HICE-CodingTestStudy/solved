def solution(brown, yellow):
    answer = []
    mult = brown+yellow
    # print("mult", mult)
    for i in range(3, mult):
        if mult % i == 0:
            j = mult//i
            # print('후보')
            # print("i", i)
            # print("j", j)
            if (2*i+(j*2-4)) == brown or ((i*2-4)+2*j) == brown:
                # print("아이", i)
                # print('제이', j)
                answer = [i, j]
                answer.sort(reverse=True)

    return answer


print(solution(10, 2))
print(solution(8, 1))
print(solution(24, 24))
