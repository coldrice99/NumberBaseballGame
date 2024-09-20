
//BaseballGame.java

import java.util.*;
import java.util.stream.IntStream;

public class BaseballGame {
    private Set<Integer> answerSet;
    private BaseballGameDisplay display = new BaseballGameDisplay();

    public int play(int digitLength) {
        System.out.println("< 게임을 시작합니다 >");
        Scanner sc = new Scanner(System.in);
        int attempts = 0; // 시도 횟수 기록

        // 새로운 게임 시작 시 정답 생성
        this.answerSet = generateAnswerSet(digitLength);
        System.out.println("정답은 " + answerSet); // 디버깅용 출력

        while (true) {
            //유저에게 입력값을 받음
            System.out.println("숫자 " + digitLength + "개를 입력하세요.");
            List<Integer> inputList = new ArrayList<>(); //입력값 저장을 위한 로컬 변수. 순서를 위해 Set이 아닌 List

            boolean isValid = false; // 올바른 입력을 받을 때까지 반복
            while (!isValid) {
                try {
                    int input = sc.nextInt();
                    switch (validateInput(input, inputList, digitLength)) {
                        case "중복":
                            System.out.println("중복된 숫자는 입력할 수 없습니다.");
                            break;
                        case "자릿수x":
                            System.out.println("세 자리 숫자만 입력 가능합니다.");
                            break;
                        case "정상":
                            isValid = true;
                            break;
                    }
                } catch (InputMismatchException e) {
                    //숫자가 아닌 값이 입력된 경우 처리
                    System.out.println("숫자만 입력 가능합니다.");
                    sc.next(); //입력 버퍼 초기화
                }
            }

            //시도 횟수 증가
            attempts++;

            //스트라이크 && 볼 처리
            long strikes = countStrikeAndBall(inputList, true, digitLength);
            long balls = countStrikeAndBall(inputList, false, digitLength);

            //힌트출력
            display.displayHint(strikes, balls, digitLength);
            //정답을 맞춘 경우
            if (strikes == digitLength) {
                return attempts; // 시도 횟수를 반환하고 게임 종료
            }

        }
    }


    private static Set<Integer> generateAnswerSet(int digitLength) {
        HashSet<Integer> answerSet = new HashSet<>();
        Random random = new Random();

        // 중복되지 않도록 자릿수 만큼의 랜덤한 숫자 생성
        while (answerSet.size() < digitLength) {
            int randomInt = random.nextInt(9) + 1; // 1부터 9까지의 숫자 생성
            answerSet.add(randomInt);
        }
        return answerSet;
    }

    protected String validateInput(int input, List<Integer> inputList, int digitLength) {
        inputList.clear(); // 기존 입력값 초기화 (재활용)

        // 입력값이 지정한 자릿수인지 확인
        int min = (int) Math.pow(10, digitLength - 1); //3자리면 100, 4자리면 1000..
        int max = (int) Math.pow(10, digitLength) - 1; //3자리면 999, 4자리면 9999..

        if (input < min || input > max) {
            return "자릿수x";
        }
        // 자릿수에 맞게 입력값 분리
        for (int i = digitLength - 1; i >= 0; i--) {
            inputList.add(input / (int) Math.pow(10, i) % 10);
        }
        // 중복 확인
        if (new HashSet<>(inputList).size() < digitLength) {
            return "중복";
        }
        return "정상";
    }

    private long countStrikeAndBall(List<Integer> inputList, boolean StrikeOrBall, int digitLength) {
        List<Integer> answerList = new ArrayList<>(answerSet); //순서가 필요함으로 List를 사용

        //스트림에서 count() 메서드가 반환하는 값이 long 타입
        long strikes = 0;
        long balls = 0;

        strikes = IntStream.range(0, digitLength)
                .filter(i -> inputList.get(i).equals(answerList.get(i)))
                .count();
        balls = IntStream.range(0, digitLength)
                .filter(i -> answerList.contains(inputList.get(i)) && !answerList.get(i).equals(inputList.get(i)))
                .count();
        if (StrikeOrBall) {
            return strikes;
        } else {
            return balls;
        }

    }
}