def solution(cap, n, deliveries, pickups):
    answer,delivery, pickup  = 0, 0, 0
    deliveries.reverse()
    pickups.reverse()

    for i in range(n):
        delivery += deliveries[i]
        pickup += pickups[i]

        while have_to_deli > 0 or have_to_pick > 0:
            delivery -= cap
            pickup -= cap
            answer += (n - i) * 2

    return answer
