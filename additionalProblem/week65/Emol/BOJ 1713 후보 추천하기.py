# BOJ 1713 후보 추천하기
import sys

input = sys.stdin.readline

if __name__ == "__main__":
    n = int(input())
    m = int(input())
    order = list(map(int, input().split()))

    # key : 학생 번호, value : (추천 횟수, 시간)
    board = {}
    time = 0

    for rec in order:
        if rec in board:
            cnt, post_time = board[rec]
            board[rec] = (cnt + 1, post_time)

        else:
            if len(board) < n:
                board[rec] = (1, time)
                time += 1

            else:
                remove_candidate = None
                min_count = 1000
                min_time = 1000
                for student, (count, post_time) in board.items():
                    if count < min_count or (
                        count == min_count and post_time < min_time
                    ):
                        min_count = count
                        min_time = post_time
                        remove_candidate = student

                del board[remove_candidate]
                board[rec] = (1, time)
                time += 1

    print(" ".join(map(str, sorted(board.keys()))))
