package soobin;

public class MinSquare {
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static int solution(int[][] sizes) {
        for(int[] size : sizes) {
            if (size[0] < size[1]) swap(size, 0, 1);
        }

        int maxWidth = sizes[0][0];
        int maxHeight = sizes[0][1];
        for(int i = 0; i < sizes.length; i++) {
            if (sizes[i][0] > maxWidth) maxWidth = sizes[i][0];
            if (sizes[i][1] > maxHeight) maxHeight = sizes[i][1];
        }

        return maxHeight * maxWidth;
    }

    public static void main(String[] args) {
        int[][] sizes = {{60, 50}, {30, 70}, {60, 30}, {80, 40}};
        System.out.println(solution(sizes));
    }
}
