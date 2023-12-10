#BOJ 16974

import sys
input = sys.stdin.readline

N, X = map(int, input().split())


"""
# L(N) = B + L(N-1) + P + L(N-1) + B
# L(0) = P
# L(1) = BPPPB

 패티를 먹는 경우
 
 1) B L(N-1) P | L(N-1) B
 2) B L(N-1) | P L(N-1) B
 3) B | L(N-1) P L(N-1) B
 4) | B L(N-1) P L(N-1) B
 5) P
 
"""

def nyam(N, X):
    if N == 0:
        return X
    if X == 1:
        return 0;
    #  1) B L(N-1) P | L(N-1) B
    elif X <= B[N-1] + 1:
        return nyam(N-1, X-1)
    #  2) B L(N-1) | P L(N-1) B
    elif X == 1 + B[N-1] + 1:
        return P[N-1] + 1
    # 3) B | L(N-1) P L(N-1) B
    elif X <= B[N-1] + 1 + B[N-1] + 1:
        return P[N-1] + 1 + nyam(N-1, X-1-B[N-1]-1)
    # 4) | B L(N-1) P L(N-1) B
    else: 
        return P[N-1] * 2 + 1

B = [0] * 51; P = [0] * 51; B[0] = 1; P[0] = 1

for i in range(1, N):
    B[i] = 1 + B[i-1] + 1 + B[i-1] + 1
    P[i] = P[i-1] + 1 + P[i-1]

print(nyam(N, X))

    


