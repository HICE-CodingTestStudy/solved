
#include <iostream>
using namespace std;

// 완전 이진 트리로 착각한 풀이
//
// int n, k = 1, inor[100001], postor[100001], bt[100001];
//void inorder(int idx) {
//	if (idx > n) return;
//	inorder(idx * 2);
//	bt[idx] = inor[k];
//	k++;
//	inorder(idx * 2 + 1);
//}
//
//void preorder(int idx) {
//	if (idx > n) return;
//	cout << bt[idx] << " ";
//	preorder(idx * 2);
//	preorder(idx * 2 + 1);
//}
//
//int main() {
//	cin >> n;
//
//	for (int i = 1; i <= n; i++)
//		cin >> inor[i];
//
//	for (int i = 1; i <= n; i++)
//		cin >> postor[i];
//
//	inorder(1);
//
//	preorder(1);
//}


int n, inor[100001], postor[100001], idx[100001];

void divconq(int is, int ie, int ps, int pe) {
	if (is > ie || ps > pe) return;

	int ridx = idx[postor[pe]];
	
	cout << inor[ridx] << " ";

	int left = ridx - is;
	divconq(is, ridx - 1, ps, ps + left - 1);
	divconq(ridx + 1, ie, ps + left, pe - 1);
}

int main() {
	cin >> n;

	for (int i = 1; i <= n; i++) {
		cin >> inor[i];
		idx[inor[i]] = i;
	}
		
	for (int i = 1; i <= n; i++)
		cin >> postor[i];

	divconq(1, n, 1, n);
}