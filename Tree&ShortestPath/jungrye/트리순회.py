tree = {}


def preorder(node):
    global tree

    if node == '.':
        return
    print(node, end='')
    preorder(tree[node][0])
    preorder(tree[node][1])


def inorder(node):
    global tree

    if node == '.':
        return
    inorder(tree[node][0])
    print(node, end='')
    inorder(tree[node][1])


def postorder(node):
    global tree

    if node == '.':
        return
    postorder(tree[node][0])
    postorder(tree[node][1])
    print(node, end='')


def main():
    global tree

    N = int(input())

    for _ in range(N):
        n, l, r = list(input().split())
        tree[n] = [l, r]

    preorder('A')
    print()
    inorder('A')
    print()
    postorder('A')


main()
