import java.util.ArrayList;
import java.util.Arrays;
public class findprimes{
    public static void main(String[] args) {
        int[] arr1 = new int[20];
        for (int i = 0; i < 20; i++) {
            arr1[i] = (int) (Math.random() * 1000);
        }
        System.out.println("Starting list: " + Arrays.toString(arr1));
        System.out.println("Sorted list: " + Arrays.toString(sort(arr1)));
    }
    public static int[] swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return arr;
    }
    public static int[] sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    arr = swap(arr, i, j);
                }
            }
        }
        return arr;
    }
}
