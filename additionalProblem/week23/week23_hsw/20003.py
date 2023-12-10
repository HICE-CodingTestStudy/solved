#BOJ 20003
import sys, fractions
input = sys.stdin.readline

def GCD(a, b):
    while b > 0:
        a, b = b, a % b
    return a

def LCM(a, b):
    return a * b // GCD(a, b)

N = int(input())
a, b = map(int, input().split())
frac = fractions.Fraction(a, b)
g, l = frac.numerator, frac.denominator
while (N-1):
    a, b = map(int, input().split())
    frac = fractions.Fraction(a, b)
    g = GCD(g, frac.numerator)
    l = LCM(l, frac.denominator)
    N -= 1

print(g, l)
    
