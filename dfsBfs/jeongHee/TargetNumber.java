class Solution {
    int pCnt=0;

    void comb(int cnt,int[] num, int[] selected, int startIdx, int target) {
        if(cnt ==num.length ) {
            int ans=0;
            for(int i=0;i<cnt;i++) {
                ans+=selected[i];

            }

            if(ans==target){
                pCnt++;
            }
            return;
        }
        for(int i=startIdx;i<num.length;i++) {
            selected[cnt] = num[i];
            comb(cnt+1,num ,selected, i+1, target);
            selected[cnt] = -num[i];
            comb(cnt+1,num, selected, i+1, target);

        }
    }

    public int solution(int[] numbers, int target) {
        int[] selected = new int[numbers.length];
        this.comb(0,numbers,selected,0,target);
        return pCnt;
    }

}