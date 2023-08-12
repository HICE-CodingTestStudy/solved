def solution(brown, yellow):
    answer = []
    mult = brown+yellow
    for i in range(3, mult):
        if mult % i == 0:
            j = mult//i
            if (2*i+(j*2-4)) == brown or ((i*2-4)+2*j) == brown:
                answer = [i, j]
                answer.sort(reverse=True)

    return answer


print(solution(10, 2))
print(solution(8, 1))
print(solution(24, 24))
