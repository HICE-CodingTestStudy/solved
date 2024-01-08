#BOJ 10997
import sys
input = sys.stdin.readline

N = int(input())

#맨 윗줄
print("*" * (4 * N - 3))
#그 다음부터 중간 출발점 부분까지
for i in range(N-1):
    print(("* "*(i+1)+" "*(4*N-4*i-5)+" *"*i).strip())
    print("* "*(i+1)+"*"*(4*N-4*i-5)+" *"*i)
#출발점 두칸
if N!=1:
    print("* "*(2*N-1))
    print("* "*(2*N-1))
#나머지
for i in range(N-1):
    print("* "*(N-1-i) + " "*(4*i+1)+" *"*(N-1-i))
    print("* "*(N-2-i) + "*"*(4*i+5)+" *"*(N-2-i))
