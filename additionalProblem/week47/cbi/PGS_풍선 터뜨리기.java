class Solution {
    final static int INF = (int) 2e9; 
    public int solution(int[] a) {
        int answer = 0;
        int[] left = new int[a.length], right = new int[a.length];
        left[0] = a[0];
        right[a.length - 1] = a[a.length - 1];
        for(int i = 1; i < a.length; i++) {
            left[i] = Math.min(left[i - 1], a[i - 1]);
            right[a.length - i - 1] = Math.min(right[a.length - i], a[a.length - i]);
        }
        
        for(int i = 0; i < a.length; i++) {
            if(left[i] < a[i] && right[i] < a[i]) continue;
            answer++;
        }
        return answer;
    }
}