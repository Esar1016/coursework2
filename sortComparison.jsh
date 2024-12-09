for (String filename : filenames) {
        try {
                ArrayList<String> data = readFile(filename);
        
                SortResult bubbleSortResult = measureBubbleSortWithList(data);
                SortResult mergeSortResult = measureMergeSortWithList(data);
        
                System.out.println("File: " + filename);
                System.out.println("Bubble Sort Time: " + bubbleSortResult.time + " nanoseconds");
                System.out.println("Bubble Sorted List: " + bubbleSortResult.sortedList);
                System.out.println("Merge Sort Time: " + mergeSortResult.time + " nanoseconds");
                System.out.println("Merge Sorted List: " + mergeSortResult.sortedList);
                System.out.println("---------------------------------------------------");
        } catch (IOException e) {
                System.out.println("Error reading file: " + filename);
        } catch (NullPointerException e) {
                System.out.println("Error in cardCompare: " + e.getMessage());
        } catch (IllegalArgumentException e) {
                System.out.println("Error in card format: " + e.getMessage());
        }
}
sortComparison(new String[]{
        "C:/Users/hunte/.vscode/vs/coursework2_files/sort10.txt",
        "C:/Users/hunte/.vscode/vs/coursework2_files/sort100.txt",
        "C:/Users/hunte/.vscode/vs/coursework2_files/sort10000.txt"
});
saveCodeToFile("C:/Users/hunte/.vscode/vs/sortComparison.jsh", code);
import java.util.*;
import java.io.*;
int cardCompare(String card1, String card2) {
        Map<String, Integer> cardValues = new HashMap<>();
        cardValues.put("Ace", 1);
        cardValues.put("1", 1);    // Map "1" to Ace
        cardValues.put("2", 2);
        cardValues.put("3", 3);
        cardValues.put("4", 4);
        cardValues.put("5", 5);
        cardValues.put("6", 6);
        cardValues.put("7", 7);
        cardValues.put("8", 8);
        cardValues.put("9", 9);
        cardValues.put("10", 10);
        cardValues.put("Jack", 11);
        cardValues.put("Queen", 12);
        cardValues.put("King", 13);
        cardValues.put("11", 11);   // Jack
        cardValues.put("12", 12);   // Queen
        cardValues.put("13", 13);   // King
    
        String cardValue1 = extractCardValue(card1);
        String cardValue2 = extractCardValue(card2);
    
        Integer value1 = cardValues.get(cardValue1);
        Integer value2 = cardValues.get(cardValue2);
    
        if (value1 == null) {
                throw new NullPointerException("Invalid card name: " + cardValue1);
        }
        if (value2 == null) {
                throw new NullPointerException("Invalid card name: " + cardValue2);
        }
    
        return Integer.compare(value1, value2);
}
String extractCardValue(String card) {
        List<String> cardValuesList = Arrays.asList(
            "Ace", "1", "King", "Queen", "Jack", "10", "9", "8", "7", "6", "5", "4", "3", "2",
            "11", "12", "13" // Added numerical representations for face cards
        );
        for (String value : cardValuesList) {
                if (card.startsWith(value)) {
                        return value;
                }
        }
        throw new IllegalArgumentException("Invalid card format: " + card);
}
ArrayList<String> bubbleSort(ArrayList<String> array) {
        ArrayList<String> sortedArray = new ArrayList<>(array);
        for (int i = 0; i < sortedArray.size() - 1; i++) {
                for (int j = 0; j < sortedArray.size() - 1 - i; j++) {
                        if (cardCompare(sortedArray.get(j), sortedArray.get(j + 1)) > 0) {
                                String temp = sortedArray.get(j);
                                sortedArray.set(j, sortedArray.get(j + 1));
                                sortedArray.set(j + 1, temp);
                        }
                }
        }
        return sortedArray;
}
ArrayList<String> mergeSort(ArrayList<String> array) {
        if (array.size() <= 1) {
                return array;
        }
    
        int mid = array.size() / 2;
        ArrayList<String> left = new ArrayList<>(array.subList(0, mid));
        ArrayList<String> right = new ArrayList<>(array.subList(mid, array.size()));
    
        left = mergeSort(left);
        right = mergeSort(right);
    
        return merge(left, right);
}
ArrayList<String> merge(ArrayList<String> left, ArrayList<String> right) {
        ArrayList<String> merged = new ArrayList<>();
        while (!left.isEmpty() && !right.isEmpty()) {
                if (cardCompare(left.get(0), right.get(0)) <= 0) {
                        merged.add(left.remove(0));
                } else {
                        merged.add(right.remove(0));
                }
        }
        while (!left.isEmpty()) {
                merged.add(left.remove(0));
        }
        while (!right.isEmpty()) {
                merged.add(right.remove(0));
        }
        return merged;
}
class SortResult {
        ArrayList<String> sortedList;
        long time;
    
        SortResult(ArrayList<String> sortedList, long time) {
                this.sortedList = sortedList;
                this.time = time;
        }
}
SortResult measureBubbleSortWithList(ArrayList<String> array) {
        long startTime = System.nanoTime();
        ArrayList<String> sorted = bubbleSort(array);
        long endTime = System.nanoTime();
        return new SortResult(sorted, endTime - startTime);
}
SortResult measureMergeSortWithList(ArrayList<String> array) {
        long startTime = System.nanoTime();
        ArrayList<String> sorted = mergeSort(array);
        long endTime = System.nanoTime();
        return new SortResult(sorted, endTime - startTime);
}
long measureBubbleSort(ArrayList<String> array) {
        long startTime = System.nanoTime();
        bubbleSort(array);
        long endTime = System.nanoTime();
        return endTime - startTime;
}
long measureMergeSort(ArrayList<String> array) {
        long startTime = System.nanoTime();
        mergeSort(array);
        long endTime = System.nanoTime();
        return endTime - startTime;
}
ArrayList<String> readFile(String filename) throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
                lines.add(line.trim());
        }
        reader.close();
        return lines;
}
for (String filename : filenames) {
        try {
                ArrayList<String> data = readFile(filename);
        
                SortResult bubbleSortResult = measureBubbleSortWithList(data);
                SortResult mergeSortResult = measureMergeSortWithList(data);
        
                System.out.println("File: " + filename);
                System.out.println("Bubble Sort Time: " + bubbleSortResult.time + " nanoseconds");
                System.out.println("Bubble Sorted List: " + bubbleSortResult.sortedList);
                System.out.println("Merge Sort Time: " + mergeSortResult.time + " nanoseconds");
                System.out.println("Merge Sorted List: " + mergeSortResult.sortedList);
                System.out.println("---------------------------------------------------");
        } catch (IOException e) {
                System.out.println("Error reading file: " + filename);
        } catch (NullPointerException e) {
                System.out.println("Error in cardCompare: " + e.getMessage());
        } catch (IllegalArgumentException e) {
                System.out.println("Error in card format: " + e.getMessage());
        }
}
void sortComparison(String[] filenames) throws IOException {
        String csvFile = "C:/Users/hunte/.vscode/vs/coursework2_files/sortComparison.csv";
    
        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
                writer.print(", ");
                for (int i = 0; i < filenames.length; i++) {
                        String number = filenames[i].replaceAll(".*sort(\\d+)\\.txt", "$1");
                        writer.print(number);
                        if (i < filenames.length - 1) {
                                writer.print(", ");
                        }
                }
                writer.println();
        
                List<Long> bubbleSortTimes = new ArrayList<>();
                List<Long> mergeSortTimes = new ArrayList<>();
        
                for (String filename : filenames) {
                        ArrayList<String> data = readFile(filename);
                        long bubbleTime = measureBubbleSort(data);
                        long mergeTime = measureMergeSort(data);
            
                        bubbleSortTimes.add(bubbleTime);
                        mergeSortTimes.add(mergeTime);
                }
        
                writer.print("bubbleSort, ");
                for (int i = 0; i < bubbleSortTimes.size(); i++) {
                        writer.print(bubbleSortTimes.get(i));
                        if (i < bubbleSortTimes.size() - 1) {
                                writer.print(", ");
                        }
                }
                writer.println();
        
                writer.print("mergeSort, ");
                for (int i = 0; i < mergeSortTimes.size(); i++) {
                        writer.print(mergeSortTimes.get(i));
                        if (i < mergeSortTimes.size() - 1) {
                                writer.print(", ");
                        }
                }
                writer.println();
        
                System.out.println("CSV 文件已成功生成：" + csvFile);
        } catch (IOException e) {
                System.err.println("无法写入 CSV 文件：" + e.getMessage());
        }
}
sortComparison(new String[]{
        "C:/Users/hunte/.vscode/vs/coursework2_files/sort10.txt",
        "C:/Users/hunte/.vscode/vs/coursework2_files/sort100.txt",
        "C:/Users/hunte/.vscode/vs/coursework2_files/sort10000.txt"
});
void saveCodeToFile(String filePath, String code) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
                writer.print(code);
        }
}
String code = 
    "String[] filenames = {\n" +
    "    \"C:/Users/hunte/.vscode/vs/coursework2_files/sort10.txt\",\n" +
    "    \"C:/Users/hunte/.vscode/vs/coursework2_files/sort100.txt\",\n" +
    "    \"C:/Users/hunte/.vscode/vs/coursework2_files/sort10000.txt\"\n" +
    "};\n\n" +
    "for (String filename : filenames) {\n" +
    "    try {\n" +
    "        ArrayList<String> data = readFile(filename);\n" +
    "        long bubbleTime = measureBubbleSort(data);\n" +
    "        long mergeTime = measureMergeSort(data);\n" +
    "        System.out.println(\"File: \" + filename);\n" +
    "        System.out.println(\"Bubble Sort Time: \" + bubbleTime + \" nanoseconds\");\n" +
    "        System.out.println(\"Merge Sort Time: \" + mergeTime + \" nanoseconds\");\n" +
    "} catch (IOException e) {\n" +
    "        System.out.println(\"Error reading file: \" + filename);\n" +
    "}\n" +
    "}";
saveCodeToFile("C:/Users/hunte/.vscode/vs/sortComparison.jsh", code);
String[] filenames = {
    "C:/Users/hunte/.vscode/vs/coursework2_files/sort10.txt",
    "C:/Users/hunte/.vscode/vs/coursework2_files/sort100.txt",
    "C:/Users/hunte/.vscode/vs/coursework2_files/sort10000.txt"
};
for (String filename : filenames) {
    try {
        ArrayList<String> data = readFile(filename);
        long bubbleTime = measureBubbleSort(data);
        long mergeTime = measureMergeSort(data);
        System.out.println("File: " + filename);
        System.out.println("Bubble Sort Time: " + bubbleTime + " nanoseconds");
        System.out.println("Merge Sort Time: " + mergeTime + " nanoseconds");
} catch (IOException e) {
        System.out.println("Error reading file: " + filename);
}
}