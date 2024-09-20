import java.util.List;

public class BaseballGameDisplay {
    public void displayHint(long strikes, long balls, int digitLength) {
        if (strikes == digitLength) {
            System.out.println("정답입니다! 넘모넘모 축하해용~");
        } else if (strikes > 0 && balls > 0) {
            System.out.println(strikes + " 스트라이크! " + balls + " 볼!");
        } else if (strikes == 0 && balls > 0) {
            System.out.println(balls + " 볼!");
        } else if (strikes > 0 && balls == 0) {
            System.out.println(strikes + " 스트라이크!");
        } else {
            System.out.println("아웃~");
        }
    }

    public void displayAttemptCount(int attempts) {
        System.out.println("시도 횟수 : " + attempts);

    }

    public void displayGameRecords(List<Integer> gameRecords) {
        if (gameRecords.isEmpty()) {
            System.out.println("아직 진행된 게임이 없습니다.");
        } else {
            for (int i = 0; i < gameRecords.size(); i++) {
                System.out.println((i + 1) + "번째 게임 : 시도 횟수 -  " + gameRecords.get(i));
            }
        }
    }
}
