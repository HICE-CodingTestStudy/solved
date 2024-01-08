        #BOJ 1138
        import sys
        input = sys.stdin.readline

        N = int(input())
        line = list(map(int, input().split()))
        ans = [False] * N

        for i in range(N):
            cnt = 0
            for j in range(N):
                if cnt == line[i] and not ans[j]:
                    ans[j] = i+1
                    break
                elif not ans[j]:
                    cnt += 1
        print(' '.join(map(str,ans)))