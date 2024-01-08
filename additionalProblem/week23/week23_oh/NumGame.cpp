
#include <string>
#include <algorithm>
#include <vector>

using namespace std;

int solution(vector<int> A, vector<int> B) {
    int idx, ans = 0;
    sort(A.begin(), A.end());
    sort(B.begin(), B.end());

    for (int i = 0; i < B.size(); i++)
        if (A[idx] < B[i]) {
            idx++;
            ans++;
        }
    return ans;
}
