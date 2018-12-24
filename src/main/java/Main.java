import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    static HashMap<String, Integer> numbers = new HashMap<String, Integer>();

    static HashMap<String, Integer> onumbers = new HashMap<String, Integer>();
    static HashMap<String, Integer> tnumbers = new HashMap<String, Integer>();

    static {
        numbers.put("zero", 0);
        numbers.put("one", 1);
        numbers.put("two", 2);
        numbers.put("three", 3);
        numbers.put("four", 4);
        numbers.put("five", 5);
        numbers.put("six", 6);
        numbers.put("seven", 7);
        numbers.put("eight", 8);
        numbers.put("nine", 9);
        numbers.put("ten", 10);
        numbers.put("eleven", 11);
        numbers.put("twelve", 12);
        numbers.put("thirteen", 13);
        numbers.put("fourteen", 14);
        numbers.put("fifteen", 15);
        numbers.put("sixteen", 16);
        numbers.put("seventeen", 17);
        numbers.put("eighteen", 18);
        numbers.put("nineteen", 19);


        tnumbers.put("twenty", 20);
        tnumbers.put("thirty", 30);
        tnumbers.put("fourty", 40);
        tnumbers.put("fifty", 50);
        tnumbers.put("sixty", 60);
        tnumbers.put("seventy", 70);
        tnumbers.put("eighty", 80);
        tnumbers.put("ninety", 90);

        onumbers.put("hundred", 100);
        onumbers.put("thousand", 1000);
    }

    public static ArrayList<Integer> inputCollection = new ArrayList<>();

    public static void main(String[] args) {
        String inputString;
        int result = 0;
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
            int min;
            min = collectionManipulate(inputCollection, 0, false);
            if (min!=0)
                System.out.println("Min value: "+ min);
            else
                System.out.println("Collection is empty");


        };

        executor.scheduleWithFixedDelay(task, 10, 5, TimeUnit.SECONDS);

        Scanner in = new Scanner(System.in);
        do {
            System.out.print("Input a word of number: ");
            inputString = in.nextLine();
            result = wordToNumber(inputString);
            if (result == 0)
                System.out.println("Wrong enter");
            else
                collectionManipulate(inputCollection, result, true);
            System.out.println("Digit = " + result);

        } while (!inputString.equals("end"));
    }

    private static int wordToNumber(String input) {
        int sum = 0;
        Integer temp = null;
        Integer previous = 0;
        input = input.replaceAll("-", " ");
        input = input.toLowerCase().replaceAll(" and", " ");
        String[] splitted = input.trim().split("\\s+");


        for (String split : splitted) {

            if (numbers.get(split) != null) {
                temp = numbers.get(split);
                sum = sum + temp;
                previous = previous + temp;
            } else if (onumbers.get(split) != null) {
                if (sum != 0) {
                    sum = sum - previous;
                }
                sum = sum + previous * onumbers.get(split);
                temp = null;
                previous = 0;
            } else if (tnumbers.get(split) != null) {
                temp = tnumbers.get(split);
                sum = sum + temp;
                previous = temp;
            }

        }
        return sum;
    }

    private static synchronized int collectionManipulate(ArrayList<Integer> current, int result, boolean action) {

        if (action) {
            current.add(result);
            return 0;
        } else {
            if (!current.isEmpty()){
            int index = current.indexOf(Collections.min(current));
            int value = current.get(index);
            current.remove(index);
            return value;}
            else return 0;
        }

    }


}
