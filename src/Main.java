
//Main.java

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BaseballGame game = new BaseballGame();
        BaseballGameDisplay display = new BaseballGameDisplay();
        int gameCount = 0; // 게임 횟수 추적
        List<Integer> gameRecords = new ArrayList<>(); // 각 게임의 시도 횟수를 저장할 리스트
        
        System.out.println("환영합니다! 원하시는 번호를 입력해주세요.");
        while (true) {
            System.out.println("1. 게임 시작하기 " +
                    "2. 게임 기록 보기 " +
                    "3. 종료하기");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("< 게임을 시작합니다 >");
                    int attempts = game.play(); // 게임을 진행하고 시도 횟수를 반환받음
                    gameCount++; // 게임 횟수 증가
                    gameRecords.add(attempts); // 게임 기록 저장
                    break;
                case 2:
                    System.out.println("< 게임 기록 보기 >");
                    display.displayGameRecords(gameRecords);
                    break;
                case 3:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 입력하세요.");
            }
        }

    }
}
