import java.util.Scanner;

/**
 * Created by Sydney on 7/10/2016.
 */
public class kthSmallest {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.printf("How many items are in the array?");
          int[] array = new int[input.nextInt()];

        System.out.println("Enter the numbers in the array");
        for(int i = 0; i < array.length; i++) {
           array[i] = input.nextInt();
        }

        System.out.printf("Enter the kth index you would like to find.");
        int kth = input.nextInt();

        System.out.printf("" + kSmall(kth, array, 0, array.length));

    }
    //returns the kth smallest value in an Array(first...last)
    public static int kSmall(int k, int[] anArray, int first, int last) {
        int p = anArray[first];
        int pivotIndex = partition(anArray, first, last);

        if (k < pivotIndex - first + 1) {
            return kSmall(k, anArray, first, pivotIndex-1);
        }
        else if (k == pivotIndex - first + 1) {
            return p;
        }
        else {
            return kSmall(k-(pivotIndex - first + 1), anArray,pivotIndex+1, last);
        }
    }

    private static int partition(int[] anArray, int first, int last){
        int tempItem;
        //choose pivot

        int pivot = anArray[first];
        int lastS1 = first;

        for (int firstUnknown = first + 1; firstUnknown < last; ++firstUnknown) {
            if (anArray[firstUnknown] < (pivot)) {
                ++lastS1;
                tempItem = anArray[firstUnknown];
                anArray[firstUnknown] = anArray[lastS1];
                anArray[lastS1] = tempItem;
            }
        }

        tempItem = anArray[first];
        anArray[first] = anArray[lastS1];
        anArray[lastS1] = tempItem;
        return lastS1;
    }
}
