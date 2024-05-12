# BOJ 25918 북극곰은 괄호를 찢어
import sys
input = sys.stdin.readline

if __name__ == "__main__":
    n = int(input())
    s = list(input().rstrip())
    if n%2==1:
        print(-1)
        
    else:
        summ, day = 0, 0
        for letter in s:
            if letter == '(':
                summ += 1
            else:
                summ -= 1
            if abs(summ) > day:
                day = abs(summ)
        if not summ:
            print(day)
        else:
            print(-1)
