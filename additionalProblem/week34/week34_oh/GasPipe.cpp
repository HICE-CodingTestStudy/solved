
#include <iostream>
using namespace std;

int r, c;
string map[25];
int dx[] = { 0, 0, -1, 1 }, dy[] = { 1, -1, 0, 0 };
bool visited[25][25];

bool checkl(char ch) {
	if (ch == '-' || ch == '+' || ch == '1' || ch == '2' || ch == 'Z' || ch == 'M') return true;
	return false;
}

bool checkr(char ch) {
	if (ch == '-' || ch == '+' || ch == '3' || ch == '4' || ch == 'Z' || ch == 'M') return true;
	return false;
}

bool checku(char ch) {
	if (ch == '|' || ch == '+' || ch == '1' || ch == '4' || ch == 'Z' || ch == 'M') return true;
	return false;
}

bool checkd(char ch) {
	if (ch == '|' || ch == '+' || ch == '2' || ch == '3' || ch == 'Z' || ch == 'M') return true;
	return false;
}

bool checkCh(int x, int y, char ch) {
	if (map[x - 1][y] == ch || map[x + 1][y] == ch || map[x][y - 1] == ch || map[x][y + 1] == ch) return false;
	return true;
}

void dfs(int x, int y, char mz) {

	visited[x][y] = 1;

	char ch = map[x][y];

	if (ch == '|') {
		if (!visited[x - 1][y]) dfs(x - 1, y, mz);
		if (!visited[x + 1][y]) dfs(x + 1, y, mz);
	}
	else if (ch == '-') {
		if (!visited[x][y - 1]) dfs(x, y - 1, mz);
		if (!visited[x][y + 1]) dfs(x, y + 1, mz);
	}
	else if (ch == '+') {
		if (!visited[x - 1][y]) dfs(x - 1, y, mz);
		if (!visited[x + 1][y]) dfs(x + 1, y, mz);
		if (!visited[x][y - 1]) dfs(x, y - 1, mz);
		if (!visited[x][y + 1]) dfs(x, y + 1, mz);
	}
	else if (ch == '1') {
		if (!visited[x + 1][y]) dfs(x + 1, y, mz);
		if (!visited[x][y + 1]) dfs(x, y + 1, mz);
	}
	else if (ch == '2') {
		if (!visited[x - 1][y]) dfs(x - 1, y, mz);
		if (!visited[x][y + 1]) dfs(x, y + 1, mz);
	}
	else if (ch == '3') {
		if (!visited[x - 1][y]) dfs(x - 1, y, mz);
		if (!visited[x][y - 1]) dfs(x, y - 1, mz);
	}
	else if (ch == '4') {
		if (!visited[x][y - 1]) dfs(x, y - 1, mz);
		if (!visited[x + 1][y]) dfs(x + 1, y, mz);
	}
    
	else if (ch == '.') {
		cout << x + 1 << " " << y + 1 << " ";

		if ((y && checkl(map[x][y - 1])) && (y + 1 < c && checkr(map[x][y + 1]))
			&& (x && checku(map[x - 1][y])) && (x + 1 < r && checkd(map[x + 1][y]))
			&& checkCh(x, y, mz))
			cout << '+';
		else if ((x && checku(map[x - 1][y])) && (x + 1 < r && checkd(map[x + 1][y])))
			cout << '|';
		else if ((y && checkl(map[x][y - 1])) && (y + 1 < c && checkr(map[x][y + 1])))
			cout << '-';
		else if ((x + 1 < r && checkd(map[x + 1][y])) && (y + 1 < c && checkr(map[x][y + 1])))
			cout << '1';
		else if ((x && checku(map[x - 1][y])) && (y + 1 < c && checkr(map[x][y + 1])))
			cout << '2';
		else if ((x && checku(map[x - 1][y])) && (y && checkl(map[x][y - 1])))
			cout << '3';
		else if ((x + 1 < r && checkd(map[x + 1][y])) && (y && checkl(map[x][y - 1])))
			cout << '4';

		return;
	}
}

int main() {
	cin >> r >> c;

	for (int i = 0; i < r; i++)
		cin >> map[i];

	int zr, zc, mr, mc;

	for (int i = 0; i < r; i++)
		for (int j = 0; j < c; j++)
			if (map[i][j] == 'M') {
				for (int k = 0; k < 4; k++) {
					int mr = i + dx[k], mc = j + dy[k];
					if (mr >= 0 && mc >= 0 && mr < r && mc < c && map[mr][mc] != '.' && map[mr][mc] != 'Z') {
						dfs(mr, mc, 'M');
						return 0;
					}
				}
			}
			else if (map[i][j] == 'Z') {
				for (int k = 0; k < 4; k++) {
					int zr = i + dx[k], zc = j + dy[k];
					if (zr >= 0 && zc >= 0 && zr < r && zc < c && map[zr][zc] != '.' && map[zr][zc] != 'M') {
						dfs(zr, zc, 'Z');
						return 0;
					}
				}
			}
}
