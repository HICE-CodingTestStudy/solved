def solution(sizes):
    x_max, y_max = 0, 0

    for size in sizes:
        x, y = size
        if x > y:
            x_max = max(x_max, x)
            y_max = max(y_max, y)

        else:
            x_max = max(x_max, y)
            y_max = max(y_max, x)

    return x_max * y_max
