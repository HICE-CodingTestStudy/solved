def solution(cap, n, deliveries, pickups):
    answer,delivery, pickup  = 0, 0, 0
    deliveries.reverse()
    pickups.reverse()

    for i in range(n):
        delivery += deliveries[i]
        pickup += pickups[i]

        while delivery > 0 or pickup > 0:
            delivery -= cap
            pickup -= cap
            answer += (n - i) * 2

    return answer
