import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;
import java.util.*;
import java.lang.*;
import java.io.PrintWriter;


public class BruteForce {
    public static ArrayList BruteForce(HashMap<Integer, HashMap<String, Integer>> map, ArrayList<Integer> weight, HashMap<String, Integer> map2) throws FileNotFoundException {
        PrintWriter out = new PrintWriter("output.txt");
        //declarations
        int maxProfit = 0; //store max profit
        Set<Integer> s = map.keySet(); //store current set
        Set<String> m = Collections.emptySet(); //Store final output set
        ArrayList output = new ArrayList();
        Set<String> cards = new HashSet<>();

        ArrayList<Integer> sum;
        int sum1;


//        if sum of all elements = 0 return set s
//        else bitwise logic
//        System.out.println(s);
        for (int index :
                s) {
            HashMap<String, Integer> var = map.get(index);
            sum1 = 0;
            //index gives each inner hashmap with name and value of card
//            System.out.println("Map.get(index)"+map.get(index));
            //var.keySet() gives keys in hashmap, i.e., card names
//            System.out.println("var.keySet()"+var.keySet());
            for (String indexInner : var.keySet()) {
                //var.get(indexInner) iterates over values of hashmap
//               System.out.println("Inner index of var"+var.get(indexInner));
                sum = new ArrayList<>();
                sum.add(var.get(indexInner));
//                System.out.println(sum);
                sum1 += var.get(indexInner);
//               int n = var.size();
//               System.out.printf("var size" + n + "\n");
            }
            int maxProfitSimp;
            if (sum1 <= weight.get(index)) {
                maxProfitSimp = 0;
                output.add(var);
                for (String key : var.keySet()) {
                    if (map2.containsKey(key)) {
//                                System.out.println(maxProfitBrute);
//                                System.out.println(map2.get(listCards.get(b)));
                        maxProfitSimp = maxProfitSimp + (map2.get(key) - var.get(key));
                    }
                }

                out.println(var.size() + " " + maxProfitSimp + " " + var.size() + " " + (TimeUnit.NANOSECONDS.toSeconds(System.nanoTime())));
                for (String key : var.keySet()) {
                    out.println(key);
                }
            } else {
                int n = var.size();
                Set<String> keySet = var.keySet();
                ArrayList<String> storeKeys = new ArrayList<>(keySet);
                Collection<Integer> values = var.values();
                ArrayList<Integer> storeValues = new ArrayList<>(values);


                int maxProfitBrute = 0;
                int maxSumBrute = 0;
                int outputListSize = 0;
                ArrayList<String> listCardsOutput = new ArrayList<>();
                //loop will run for 2^n-1 times
                int profitBrute = 0;
                for (int i = 0; i < (1 << n); i++) {
                    int sumBrute = 0;


//                        int sum = 0;
                    ArrayList<Integer> list = new ArrayList<>();
                    ArrayList<String> listCards = new ArrayList<>();
                    for (int k = 0; k < n; k++) {
                        if ((i & (1 << k)) > 0) {
                            list.add(storeValues.get(k));
                            listCards.add(storeKeys.get(k));
                        }

                    }
//                        System.out.println(list);
//                        System.out.println(listCards);
                    for (int a = 0; a < list.size(); a++) {
                        sumBrute = sumBrute + list.get(a);
                    }
                    if (sumBrute >= maxSumBrute && sumBrute <= weight.get(index)) {
                        maxSumBrute = sumBrute;
                        maxProfitBrute = 0;


                        for (int b = 0; b < listCards.size(); b++) {
                            if (map2.containsKey(listCards.get(b))) {

//                                System.out.println(maxProfitBrute);
//                                System.out.println(map2.get(listCards.get(b)));
                                maxProfitBrute = maxProfitBrute + (map2.get(listCards.get(b)) - list.get(b));
                                if (maxProfitBrute > profitBrute) {
                                    listCardsOutput.clear();
//                                    outputListSize = 0;
                                    profitBrute = maxProfitBrute;
                                    for (String key : listCards) {
                                        listCardsOutput.add(key);
                                    }
//                                    outputListSize = listCardsOutput.size();

                                }
                                outputListSize = listCardsOutput.size();





                            }
                        }


//                System.out.println(var.size() +" "+ maxProfitBrute + " "+ );


                    }

                }
//                System.out.println("max Sum " + maxSumBrute);
//                System.out.println("max Profit " + maxProfitBrute);
                out.println(var.size() + " " + profitBrute + " " + outputListSize + " " + (TimeUnit.NANOSECONDS.toSeconds(System.nanoTime())));
                for (String key : listCardsOutput) {
                    out.println(key);
                }
            }

        }
        out.close();
        return new ArrayList();
    }
}