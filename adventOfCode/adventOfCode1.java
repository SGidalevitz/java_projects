import java.util.Scanner;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;
public class adventOfCode1{
    public static void main(String[] args) {
        try {
            File inputFile = new File("AOCInput1.txt");
            Scanner input = new Scanner(inputFile);
            int[] maxCalories = {0, 0, 0};
            int totalCalories = 0;
            while (input.hasNextLine()) {
                String data = input.nextLine();
                while (!data.equals("") && input.hasNextLine())
                    {
                        totalCalories += Integer.parseInt(data);
                        data = input.nextLine();
                    }
                if (!input.hasNextLine())
                {
                    totalCalories += Integer.parseInt(data);
                }    

                if (totalCalories > maxCalories[0])
                {
                    maxCalories[0] = totalCalories;
                    maxCalories = sortArray(maxCalories);
                    for (int i = 0; i < maxCalories.length; i++) {
                    //System.out.print(maxCalories[i] + " ");
                }
                }
                totalCalories = 0;
            }
            input.close();
            int sum = 0;
            for (int i = 0; i < maxCalories.length; i++) {
                sum += maxCalories[i];
            }
            System.out.println(maxCalories[0] + " + " + maxCalories[1] + " + " + maxCalories[2] + " = " + sum);
            
        } 
        catch (FileNotFoundException e) {
            // Handle the exception, e.g., print an error message
            e.printStackTrace();
        }
    }
    public static int[] sortArray(int[] arr1)
    {
        int a0 = arr1[0];
        int a1 = arr1[1];
        int a2 = arr1[2];
        if ((a0 <= a1) && (a1 <= a2))
        {
            arr1[0] = a0;
            arr1[1] = a1;
            arr1[2] = a2;
            return arr1;
        }
        else if ((a0 <= a2) && (a2<= a1))
        {
            arr1[0] = a0;
            arr1[1] = a2;
            arr1[2] = a1;
            return arr1;
        }
        else if ((a1 <= a0) && (a0 <= a2))
        {
            arr1[0] = a1;
            arr1[1] = a0;
            arr1[2] = a2;
            return arr1;
        }
        else if ((a1 <= a2) && (a2 <= a0))
        {
            arr1[0] = a1;
            arr1[1] = a2;
            arr1[2] = a0;
            return arr1;
        }
        else if ((a2 <= a0) && (a0 <= a1))
        {
            arr1[0] = a2;
            arr1[1] = a0;
            arr1[2] = a1;
            return arr1;
        }
        else if ((a2 <= a1) && (a1<= a0))
        {
            arr1[0] = a2;
            arr1[1] = a1;
            arr1[2] = a0;
            return arr1;
        }
        else
        {
            return arr1;
        }
    }
}