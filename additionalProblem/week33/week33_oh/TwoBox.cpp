
#include <iostream>
#include <vector>

using namespace std;

struct line {
	int s, e;
};

int f(line a, line b) {
	if (a.s > b.s) {
		line tmp = a;
		a = b;
		b = tmp;
	}
	if (a.e < b.s) return 0;
	else if (a.e == b.s) return 1;
	else return 2;
}

int main() {
	line x1, y1;
	line x2, y2;

	cin >> x1.s >> y1.s >> x1.e >> y1.e;
	cin >> x2.s >> y2.s >> x2.e >> y2.e;

	int X = f(x1, x2), Y = f(y1, y2);

	if (X == 2 && Y == 2) cout << "FACE";
	else if (X == 1 && Y == 2 || X == 2 && Y == 1) cout << "LINE";
	else if (X == 1 && Y == 1) cout << "POINT";
	else cout << "NULL";
}
