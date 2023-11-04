
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int p, m, n, t, sum, ans, a[1001], b[1001];
vector<int> A, B;

int main() {
	cin >> p >> m >> n;

	for (int i = 0; i < m; i++)
		cin >> a[i];
	for (int i = 0; i < n; i++)
		cin >> b[i];

	t = 0;
	for (int i = 0; i < m; i++)
		if (a[i] <= p) {
			A.push_back(a[i]);
			int j = i + 1;
			if (j == m) j = 0;
			sum = a[i];
			while (sum <= p && i != j) {
				sum += a[j];
				if (sum <= p) A.push_back(sum);
				else break;
				j++;
				if (j == m) j = 0;

				// 모든 조각
				if (i == j && t) A.pop_back();
				if (i == j && !t) t = 1;
			}
		}

	t = 0;
	for (int i = 0; i < n; i++)
		if (b[i] <= p) {
			B.push_back(b[i]);
			int j = i + 1;
			if (j == n) j = 0;
			sum = b[i];
			while (sum <= p && i != j) {
				sum += b[j];
				if (sum <= p) B.push_back(sum);
				else break;
				j++;
				if (j == n) j = 0;

				// 모든 조각
				if (i == j && t) B.pop_back();
				if (i == j && !t) t = 1;
			}
		}

	A.push_back(0); B.push_back(0);
	sort(A.begin(), A.end());
	sort(B.begin(), B.end());

	for (int i = 0; i < A.size(); i++)
		ans += upper_bound(B.begin(), B.end(), p - A[i]) - lower_bound(B.begin(), B.end(), p - A[i]);

	cout << ans;
 }
