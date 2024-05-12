#include <iostream>

using namespace std;

int n;
char x, y, str[26];

int main() {
	cin >> n;
	while (n--) {
		scanf(" %c is %c", &x, &y);
		str[x] = y;
	}

	cin >> n;
	while (n--) {
		scanf(" %c is %c", &x, &y);
		while (x != y && str[x]) x = str[x];
		puts(x == y ? "T" : "F");
	}
}
