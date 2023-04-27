# 이상하게 푼 ver.
def solution(prices):
    answer = []
    prices.reverse()

    pop = prices.pop()
    while True:
        if len(prices) == 0:
            break
        up = 0
        for price in reversed(prices):

            if price >= pop:
                up += 1
                print(f"(1)pop:{pop} price:{price} up:{up}")
            else:
                up += 1
                print(f"(2)pop:{pop} price:{price} up:{up}")
                break

        answer.append(up)
        print("answer", answer)
        pop = prices.pop()
        print(f"prices: {prices} new pop:{pop}")
    answer.append(0)
    return answer


print(solution([1, 2, 3, 2, 3]))  # [4,3,1,1,0]
print(solution([1, 2, 3, 2, 1]))  # [4,3,1,1,0]
