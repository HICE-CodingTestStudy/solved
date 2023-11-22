
#include <string>
#include <vector>

using namespace std;

vector<int> solution(int n, int s) {
    vector<int> ans;

    int t = s / n;
    int k = s % n;

    if (n > s) {
        ans.push_back(-1);
        return ans;
    }

    for (int i = 0; i < n; i++)
        ans.push_back(t);
    for (int i = n - 1; i > n - 1 - k; i--)
        ans[i]++;

    return ans;
}
