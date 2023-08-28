package queue;

public class BiggerNumber {
    //https://school.programmers.co.kr/learn/courses/30/lessons/12911
    //다음 큰 숫자
    public int convertToBinary(int n) {
        int count = 0;
        boolean isFirst = true;
        int oneCount = 1;
        while (n != 0) {
            int tmpN = n;
            if (isFirst) {
                while (tmpN / 2 != 0) {
                    tmpN /= 2;
                    count++;
                }
                n -= Math.pow(2, count);
                isFirst = false;
                count--;
            } else {
                int tmpCount = count;
                if (Math.pow(2, tmpCount) > tmpN) {
                    count--;
                    continue;
                }
                oneCount++;
                n -= Math.pow(2, count);
                count--;
            }
        }
        return oneCount;
    }

    //다 도는것 말고 만들어내려다 실패
//
//    public int convertFromBinary(String binary) {
//        int ans = 0;
//        for (int i = 0; i < binary.length(); i++) {
//            if (binary.charAt(i) == '0') continue;
//            ans += Math.pow(2, binary.length() - 1 - i);
//        }
//        return ans;
//    }
//
//    public int solution(int n) {
//        String binary = convertToBinary(n);
//        int count1 = 0;
//        int count0 = 0;
//        int convertCount = 0;
//        for (int i = 0; i < binary.length(); i++) {
//            if (binary.charAt(i) == '0') count0++;
//            else count1++;
//            if (i > 0 && binary.charAt(i) != binary.charAt(i - 1)) convertCount++;
//        }
//        StringBuilder biggerNumber = new StringBuilder();
//        if (convertCount <= 1) {
//            biggerNumber.append("1");
//            count0++;
//            count1--;
//            while (count0 > 0) {
//                biggerNumber.append("0");
//                count0--;
//            }
//            while (count1 > 0) {
//                biggerNumber.append("1");
//                count1--;
//            }
//            return convertFromBinary(biggerNumber.toString());
//        }
//        for (int i = 0; i < binary.length(); i++) {
//            if (i < binary.length() - 1 && binary.charAt(i) == '0' && binary.charAt(i + 1) == '1') {
//                biggerNumber.append("1");
//                count1--;
//                break;
//            }
//            if (binary.charAt(i) == '0') {
//                count0--;
//                biggerNumber.append("0");
//                continue;
//            }
//            count1--;
//            biggerNumber.append("1");
//        }
//        while (count0 > 0) {
//            biggerNumber.append("0");
//            count0--;
//        }
//        while (count1 > 0) {
//            biggerNumber.append("1");
//            count1--;
//        }
//        return convertFromBinary(biggerNumber.toString());
//    }

    public int solution(int n) {
        for (int i = 1; true; i++) {
            if(convertToBinary(n)==convertToBinary(n+i)) return n+i;
        }
    }

    public static void main(String[] args) {
        BiggerNumber b = new BiggerNumber();
        System.out.println(b.convertToBinary(5));
//        System.out.println(b.convertFromBinary("110"));
        System.out.println(b.solution(15));
    }
}
