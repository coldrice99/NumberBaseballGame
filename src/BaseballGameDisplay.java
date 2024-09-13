public class BaseballGameDisplay {
    public void displayHint(long strikes, long balls) {
        if (strikes == 3) {
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

    public void displayGameCount(int gameCount) {
        System.out.println("게임 진행 횟수: " + gameCount);
    }
}
