import java.util.Random;

public class gnomeSort {
    public static void main(String args[]) {
        Random random = new Random();
        int numOfTests = 10000;
        int sortLength = 100;
        int[] array1 = new int[sortLength];
        for (int t = 0; t < numOfTests; t++)
        {
            for (int i = 0; i < sortLength; i++)
            {
                array1[i] = random.nextInt(1000);
            //}
            gnomeSort(array1);
            System.out.println(array1);
            }
        }
        
    }
    public static void gnomeSort(int[] a) {
        int pos = 0;
        int value = 0;
        while (pos < a.length)
        {
            if (pos == 0 || a[pos] >= a[pos-1])
            {
                pos += 1;
            }
            else
            {
                value = a[pos];
                a[pos] = a[pos - 1];
                a[pos - 1] = value;
                pos -= 1;
            }
        
        }
    }
}
