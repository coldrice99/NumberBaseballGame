
//Main.java

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BaseballGame game = new BaseballGame();
        BaseballGameDisplay display = new BaseballGameDisplay();
        List<Integer> gameRecords = new ArrayList<>(); // 각 게임의 시도 횟수를 저장하는 리스트
        int gameCount = 0; // 게임 횟수 추적
        int digitLength = 3; // 기본 자릿수는 3자리

        System.out.println("환영합니다! 원하시는 번호를 입력해주세요.");
        while (true) {
            System.out.println("0. 자리수 설정 " +
                    "1. 게임 시작하기 " +
                    "2. 게임 기록 보기 " +
                    "3. 종료하기");
            try {
                int choice = sc.nextInt();
                switch (choice) {
                    case 0:
                        System.out.println("설정하고자 하는 자리수를 입력하세요. (3, 4, 5)");
                        int newDigitLength = sc.nextInt();
                        if (newDigitLength == 3 || newDigitLength == 4 || newDigitLength == 5) {
                            digitLength = newDigitLength;
                            System.out.println(digitLength + "자릿수 난이도를 설정되었습니다.");
                        } else {
                            System.out.println("잘못된 자릿수입니다. 3, 4, 5 중에서 선택하세요.");
                            continue; // 잘못된 자릿수 입력 시 다시 반복
                        }
                        // 자릿수 설정 후 바로 게임 시작으로 이동
                    case 1:
                        //자리수를 설정하지 않으면 디폴트로 3자리로 진행.
                        int attempts = game.play(digitLength); // 게임을 진행하고 시도 횟수를 반환받음
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
                        System.out.println("잘못된 입력입니다. 0, 1, 2, 3 중 하나를 입력해주세요.");
                }
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.");
                sc.next();
            }
        }

    }
}
