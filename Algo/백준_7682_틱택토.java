import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static char[][] board;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        StringBuilder sb = new StringBuilder();
        while (!str.equals("end")) {
            board = new char[3][3];
            int xCount = 0;
            int oCount = 0;
            boolean isBlank = false;
            for (int i = 0; i < str.length(); i++) {
                char temp = str.charAt(i);
                board[i / 3][i % 3] = temp;

                if (temp == 'X') xCount++;
                if (temp == 'O') oCount++;
                if(temp=='.') isBlank=true;
            }

            
			// 게임이 끝났는지 확인하고 결과 출력
            if (isEnd(xCount, oCount, isBlank)) {
                sb.append("valid").append("\n");
            } else {
                sb.append("invalid").append("\n");
            }
            str = br.readLine();
        }
        System.out.println(sb.toString());
    }

    // 게임 종료 여부 확인
    public static boolean isEnd(int xCount, int oCount, boolean isBlank) {
        // X와 O의 개수가 맞지 않는 경우
        if (!(xCount == oCount || xCount == oCount + 1)) return false;

        // 승리 여부 확인
        boolean xWin = checkWin('X');
        boolean oWin = checkWin('O');

        // X와 O가 동시에 승리하면 invalid
        if (xWin && oWin) return false;

        // O가 승리했는데 X가 더 많으면 invalid
        if (oWin && xCount != oCount) return false;

        // X가 승리했는데 X와 O 개수가 같으면 invalid
        if (xWin && xCount != oCount + 1) return false;
        if(!xWin && !oWin && isBlank) return false;

        return true;
    }

    // 특정 플레이어가 승리했는지 확인
    public static boolean checkWin(char stone) {
        // 가로 체크
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == stone && board[i][1] == stone && board[i][2] == stone) {
                return true;
            }
        }

        // 세로 체크
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == stone && board[1][i] == stone && board[2][i] == stone) {
                return true;
            }
        }

        // 대각선 체크
        if (board[0][0] == stone && board[1][1] == stone && board[2][2] == stone) {
            return true;
        }

        if (board[0][2] == stone && board[1][1] == stone && board[2][0] == stone) {
            return true;
        }

        // 승리 조건을 만족하지 않음
        return false;
    }
}