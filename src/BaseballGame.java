
//BaseballGame.java
import java.util.*;
import java.util.stream.IntStream;

public class BaseballGame {
    private int gameCount = 0;
    private Set<Integer> answerSet;
    private BaseballGameDisplay display = new BaseballGameDisplay();

    // 생성자에서 정답을 생성하도록 함
    public BaseballGame() {
        this.answerSet = generateAnswerSet();
        System.out.println("정답은 " + answerSet);
    }

    public int play() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            //유저에게 입력값을 받음
            System.out.println("숫자 3개를 입력하세요.");
            List<Integer> inputList = new ArrayList<>(); //입력값 저장을 위한 로컬 변수. 순서를 위해 Set이 아닌 List

            boolean isValid = false; // 올바른 입력을 받을 때까지 반복
            while (!isValid) {
                try {
                    int input = sc.nextInt();
                    switch (validateInput(input, inputList)) {
                        case "중복":
                            System.out.println("중복된 숫자는 입력할 수 없습니다.");
                            break;
                        case "세자리x":
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

            //게임 진행 횟수 증가
            gameCount++;
//            System.out.println("게임 진행 횟수: " + gameCount);
            display.displayGameCount(gameCount);

            //스트라이크 && 볼 로직 처리
            long strikes = countStrikeAndBall(inputList,true);
            long balls = countStrikeAndBall(inputList,false);

            //힌트출력
            display.displayHint(strikes, balls);
            if (strikes == 3) break; //정답이므로 반복문 탈출

        }
        return gameCount;
    }

    private static Set<Integer> generateAnswerSet() {
        HashSet<Integer> answerSet = new HashSet<>();
        Random random = new Random();

        //중복되지 않도록 3개의 랜덤한 숫자 생성
        while (answerSet.size() < 3) {
            int randomInt = random.nextInt(9) + 1; // 1부터 9까지의 숫자 생성
            answerSet.add(randomInt);
        }
        return answerSet;
    }

    protected String validateInput(int input, List<Integer> inputList) {
        inputList.clear(); // 기존 입력값 초기화 (재활용)
        if (input < 100 || input > 999) {
            return "세자리x"; // 세 자리 숫자가 아닌 경우
        }

        inputList.add(input / 100); // 백의 자리
        inputList.add((input / 10) % 10); // 십의 자리
        inputList.add(input % 10); // 일의 자리

        if (new HashSet<>(inputList).size() < 3) {
            //같은 숫자가 입력되어 HashSet에 3자리 숫자가 저장되지 못하는 경우
            return "중복";
        }
        return "정상";
    }

    private long countStrikeAndBall(List<Integer> inputList, boolean StrikeOrBall) {
        List<Integer> answerList = new ArrayList<>(answerSet); //순서가 필요함으로 List를 사용

        //스트림에서 count() 메서드가 반환하는 값이 long 타입
        long strikes = 0;
        long balls = 0;

        strikes = IntStream.range(0, 3)
                .filter(i -> inputList.get(i).equals(answerList.get(i)))
                .count();
        balls = IntStream.range(0,3)
                .filter(i -> answerList.contains(inputList.get(i)) && !answerList.get(i).equals(inputList.get(i)))
                .count();
        if(StrikeOrBall) {
            return strikes;
        } else {
            return balls;
        }

    }
}