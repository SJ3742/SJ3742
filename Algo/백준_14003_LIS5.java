import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 백준_14003_LIS5 {
    public static int[] arr; // 입력 배열
    public static int[] lis; // LIS 배열에 인덱스를 저장할 공간
    public static int[] prevIndex; // 이전 원소 인덱스를 저장할 배열
    public static int result = 0; // LIS의 길이를 저장하는 변수
    public static int lastIndex = 0; // 마지막 원소의 인덱스 저장

    // 이진 탐색을 사용하여 LIS 배열에 들어갈 위치를 찾는 함수
    public static int binarySearch(int left, int right, int target) {
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[lis[mid]] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right; // LIS 배열에서 target이 들어갈 위치
    }

    public static void findLIS(int N) {
        lis = new int[N]; // LIS 배열 크기 설정 (인덱스를 저장)
        prevIndex = new int[N]; // 이전 원소 인덱스 배열 초기화
        lis[0] = 0; // 첫 번째 원소의 인덱스를 LIS에 추가
        prevIndex[0] = -1; // 첫 번째 원소는 이전 인덱스가 없음
        result = 1; // 첫 번째 원소로 길이 1부터 시작
        lastIndex = 0; // 첫 번째 원소를 마지막 원소로 초기화

        for (int i = 1; i < N; i++) {
            if (arr[i] > arr[lis[result - 1]]) {
                // 현재 원소가 LIS의 마지막 원소보다 크면 추가
                prevIndex[i] = lis[result - 1];
                lis[result] = i;
                result++;
                lastIndex = i;
            } else {
                // 그렇지 않으면 적절한 위치에 덮어쓰기 위해 이진 탐색
                int pos = binarySearch(0, result, arr[i]);
                lis[pos] = i;
                prevIndex[i] = (pos > 0) ? lis[pos - 1] : -1;
                if (pos == result - 1) lastIndex = i;
            }
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // LIS 찾기
        findLIS(N);
        // 최장 길이
        sb.append(result).append("\n");

        // LIS 수열을 역추적하여 출력
        Stack<Integer> stack = new Stack<>();
        for (int i = lastIndex; i >= 0; i = prevIndex[i]) {
            stack.push(arr[i]);
            if (prevIndex[i] == -1) break;
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        System.out.println(sb.toString());
    }
}