import java.io.*;
import java.util.*;

public class RankingQueue {
    private static class Player {
        int level;
        String nickname;

        Player(int level, String nickname) {
            this.level = level;
            this.nickname = nickname;
        }

        private String getInfo() {
            return level + " " + nickname + "\n";
        }
    }
    private static class Room {
        int initLevel;
        Queue<Player> players;
        boolean isStarted;

        Room(int initLevel) {
            this.initLevel = initLevel;
            this.players = new PriorityQueue<>(Comparator.comparing(o -> o.nickname));
            this.isStarted = false;
        }

        public void addPlayer(Player player) {
            players.add(player);
            if (players.size() == roomLimit) isStarted = true;
        }

        public boolean isAvailable(int level) {
            return (level >= initLevel - 10 && level <= initLevel + 10) && !isStarted;
        }

        public void printStatus() throws IOException {
            bw.write((isStarted ? "Started!" : "Waiting!") + "\n");
            while (!players.isEmpty()) {
                Player player = players.poll();
                bw.write(player.getInfo());
            }
        }
    }
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final List<Room> waitingRooms = new LinkedList<>();

    private static int totalPlayerNum, roomLimit;

    public static void main(String[] args) throws IOException {
        initialize();
        matchPlayers();
        printRoomStatus();
    }

    private static void initialize() throws IOException {
        String[] input = br.readLine().split(" ");
        totalPlayerNum = Integer.parseInt(input[0]);
        roomLimit = Integer.parseInt(input[1]);
    }

    private static void matchPlayers() throws IOException {
        for (int i = 0; i < totalPlayerNum; i++) {
            String[] input = br.readLine().split(" ");
            int level = Integer.parseInt(input[0]);
            String nickname = input[1];
            Player player = new Player(level, nickname);

            Room availableRoom = findAvailableRoom(level);
            availableRoom.addPlayer(player);
        }
    }

    private static Room findAvailableRoom(int level) {
        for (Room room : waitingRooms)
            if (room.isAvailable(level)) return room;

        Room newRoom = new Room(level);
        waitingRooms.add(newRoom);
        return newRoom;
    }

    private static void printRoomStatus() throws IOException {
        for (Room room : waitingRooms)
            room.printStatus();
        bw.flush();
    }
}
