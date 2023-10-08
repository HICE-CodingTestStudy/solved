package week15.soobin;

import java.util.*;

public class ParkingFee {
    private final int COMMON_OUT_TIME = 23 * 60 + 59;

    private Map<String, Integer> totalTimeOfCars = new HashMap<>();
    private Map<String, Boolean> lastStatusOfCars = new HashMap<>();

    private int convertTimeFromString(String timestamp) {
        StringTokenizer st = new StringTokenizer(timestamp, ":");
        return Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
    }

    private List<String> parseInput(String[] records) {
        Set<String> cars = new HashSet<>();

        for (String record : records) {
            StringTokenizer st = new StringTokenizer(record);
            String timestampString = st.nextToken(), car = st.nextToken(), type = st.nextToken();

            int timestamp = convertTimeFromString(timestampString);
            boolean isIn = type.equals("IN");
            timestamp *= isIn ? -1 : 1;

            cars.add(car);
            lastStatusOfCars.put(car, isIn);
            totalTimeOfCars.put(car, totalTimeOfCars.getOrDefault(car, 0) + timestamp);
        }

        return new ArrayList<>(cars);
    }

    private int[] calculateParkingFee(List<String> cars, int[] fees) {
        int[] answer = new int[cars.size()];

        for (int i = 0; i < cars.size(); i++) {
            String car = cars.get(i);
            int totalTime = totalTimeOfCars.get(car);
            boolean isLastStatusIn = lastStatusOfCars.get(car);

            if (isLastStatusIn) totalTime += COMMON_OUT_TIME;

            answer[i] = totalTime <= fees[0]
                    ? fees[1]
                    : (int) (fees[1] + Math.ceil((double) (totalTime - fees[0]) / fees[2]) * fees[3]);
        }

        return answer;
    }

    public int[] solution(int[] fees, String[] records) {
        List<String> cars = parseInput(records);
        Collections.sort(cars);

        int[] answer = calculateParkingFee(cars, fees);

        return answer;
    }

    public static void main(String[] args) {
        ParkingFee f = new ParkingFee();

        int[] fees = new int[] {120, 0, 60, 591};
        String[] records = new String[] {"16:00 3961 IN","16:00 0202 IN","18:00 3961 OUT","18:00 0202 OUT","23:58 3961 IN"};
        int[] result = f.solution(fees, records);

        for (int r : result) {
            System.out.print(r + " ");
        }
        System.out.println("");
    }
}
