import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_10986_나머지합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] sum = new long[N]; // 부분합 저장용 배열
        long[] count = new long[M]; // 나머지 별로 카운트
        long answer = 0;

        // 첫 번째 값 입력 받기
        st = new StringTokenizer(br.readLine());
        sum[0] = Integer.parseInt(st.nextToken());

        // 부분합 계산
        for (int i = 1; i < N; i++) {
            sum[i] = sum[i - 1] + Integer.parseInt(st.nextToken());
        }

        // 나머지 계산 및 카운트
        for (int i = 0; i < N; i++) {
            int remainder = (int)(sum[i] % M);

            // 나머지가 0이면 바로 카운트
            if (remainder == 0) {
                answer++;
            }

            // 해당 나머지에 해당하는 부분합 카운트
            count[remainder]++;
        }

        // 같은 나머지를 가진 부분합들에서 두 개를 선택해 구간을 만들 수 있는 경우의 수 계산
        for (int i = 0; i < M; i++) {
            if (count[i] > 1) {
                answer += (count[i] * (count[i] - 1)) / 2;
            }
        }

        // 결과 출력
        System.out.println(answer);
    }
}