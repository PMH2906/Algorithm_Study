import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ20006_랭킹전대기열 {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder out = new StringBuilder();
    static int P, M;

    static List<Info> roomInfo;

    public static class PlayerInfo implements Comparable<PlayerInfo> {
        int score;
        String name;

        public PlayerInfo(int score, String name) {
            this.score=score;
            this.name=name;
        }

        @Override
        public int compareTo(PlayerInfo o) {
            return this.name.compareTo(o.name);
        }
    }
    public static class Info {
        int baseScore;
        String status;
        int cnt;

        PriorityQueue<PlayerInfo> playerName = new PriorityQueue<>();

        public Info(int score, PlayerInfo playerInfo) {
            this.baseScore=score;
            playerName = new PriorityQueue<>();
            this.playerName.add(playerInfo);
            this.cnt=1;
        }

        public void updateStatus(int maxCnt) {
            if(cnt==maxCnt) status="Started!";
            else status="Waiting!";
        }
    }

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(input.readLine());

        P=Integer.parseInt(tokens.nextToken());
        M=Integer.parseInt(tokens.nextToken());

        roomInfo = new ArrayList<>();

        loop : for(int p=0;p<P;p++) {
            tokens = new StringTokenizer(input.readLine());

            int score=Integer.parseInt(tokens.nextToken());
            String name=tokens.nextToken();

            for(Info info : roomInfo) {
                if(info.cnt<M) {
                    if (info.baseScore - 10 <= score && info.baseScore + 10 >= score) {
                        info.cnt += 1;
                        info.playerName.add(new PlayerInfo(score, name));
                        continue loop;
                    }
                }
            }
            roomInfo.add(new Info(score, new PlayerInfo(score, name)));

        }

        for(Info info : roomInfo) {
            info.updateStatus(M);
            out.append(info.status+"\n");

            // PriorityQueue는 위의 방법으로 정렬이 되긴하지만 정렬 결과를 보장하지는 않는다. 오로지 poll() 연산을 수행할 때만 정렬을 보장한다!!
            while(info.playerName.size()>0) {
                PlayerInfo now = info.playerName.poll();

                out.append(now.score + " " + now.name+"\n");
            }
        }

        System.out.print(out);
    }

}
