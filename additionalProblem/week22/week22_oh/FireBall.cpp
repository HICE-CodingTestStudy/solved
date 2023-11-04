
#include <iostream>
#include <vector>
using namespace std;

int N, M, K;
int dx[] = { -1,-1,0,1,1,1,0,-1 };
int dy[] = { 0,1,1,1,0,-1,-1,-1 };
typedef struct fire {
	int x, y, m, s, d;
}fire;
vector<fire> fireBall, newFire;
vector<int> curFire[51][51];

void move() {
	for (int i = 0; i < fireBall.size(); i++) {
		if (fireBall[i].m) { 
			for (int j = 0; j < fireBall[i].s; j++) {
				fireBall[i].x += dx[fireBall[i].d];
				fireBall[i].y += dy[fireBall[i].d];
				if (!fireBall[i].x) fireBall[i].x = N;
				if (!fireBall[i].y) fireBall[i].y = N;
				if (fireBall[i].x > N) fireBall[i].x = 1;
				if (fireBall[i].y > N) fireBall[i].y = 1;
			}
			curFire[fireBall[i].x][fireBall[i].y].push_back(i);
		}
	}
}

void divide(int x, int y) {
	int newM = 0;
	int newS = 0;
	int newD = 0; // 홀수 +1, 짝수 -1
	for (int i = 0; i < curFire[x][y].size(); i++) {
		newM += fireBall[curFire[x][y][i]].m;
		newS += fireBall[curFire[x][y][i]].s;
		if (fireBall[curFire[x][y][i]].d % 2 == 0) newD--;
		else newD++;
	}
	newM /= 5;
	newS /= curFire[x][y].size();
	if(newM){
		if (abs(newD) == curFire[x][y].size())
			for (int i = 0; i < 4; i++)
				newFire.push_back({ x, y, newM, newS, i * 2 });
		else for (int i = 0; i < 4; i++)
			newFire.push_back({ x,y,newM,newS, 1 + i * 2 });
	}
}

void check() {
	for (int i = 1; i <= N; i++)
		for (int j = 1; j <= N; j++)
			if (curFire[i][j].size() >= 2)
				divide(i, j);
			else if(curFire[i][j].size() == 1)
				newFire.push_back(fireBall[curFire[i][j][0]]);
}

int main() {
	cin >> N >> M >> K;
	for (int i = 0; i < M; i++) {
		fire x;
		cin >> x.x >> x.y >> x.m >> x.s >> x.d;
		fireBall.push_back(x);
	}
    
	int ans = 0;
	for (int i = 0; i < K; i++) {
		move();
		check();
		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= N; j++)
				curFire[i][j].clear();
		fireBall.clear();
		fireBall = newFire;
		newFire.clear();
	}
    
	for (int i = 0; i < fireBall.size(); i++)
		ans += fireBall[i].m;
	cout << ans;
}
