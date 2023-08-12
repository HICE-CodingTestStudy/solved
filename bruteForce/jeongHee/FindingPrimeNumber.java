package bruteFroce;

import java.util.HashSet;

public class FindingPrimeNumber {
    //https://school.programmers.co.kr/learn/courses/30/lessons/42839
    //소수 찾기
    public boolean checkPrime(int n){
        for (int i = 2; i <= n/2; i++) {
            if(n%i==0)
                return false;
        }
        if(n==1||n==0) return false;
        return true;
    }
    public void makePrime(int[] numbers, int newNumber, int count, boolean[] used, HashSet<Integer> prime){
        if(count==numbers.length)
            if(checkPrime(newNumber)){
                prime.add(newNumber);
                return;
            }
        for (int i = 0; i < numbers.length ; i++) {
            if(checkPrime(newNumber))
                prime.add(newNumber);
            if(!used[i]){
                used[i]=true;
                makePrime(numbers,newNumber*10+numbers[i],count+1,used,prime);
                used[i]=false;
            }
        }

    }
    public int solution(String numbers) {
        int[] numArr = new int[numbers.length()];
        boolean[] used = new boolean[numbers.length()];
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < numbers.length(); i++) {
            numArr[i]=Integer.parseInt(String.valueOf(numbers.charAt(i)));
        }
        makePrime(numArr,0,0,used,hashSet);
        return hashSet.size();
    }

    public static void main(String[] args) {
        FindingPrimeNumber fp = new FindingPrimeNumber();
        System.out.println(fp.solution("011"));
    }
}
