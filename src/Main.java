import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;



public class Main {
    public static HashMap<String, Integer> mapMarket;
    public static HashMap<Integer, HashMap<String, Integer>> mapPrice = new HashMap<>();
    public static ArrayList<Integer> arrayPrice = new ArrayList<>();
    public static void main(String[] args) throws IOException
        {
            String market;
            int counterPrice = 0;
            int offsetPrice = 0;
            String price;
            int indexArrayList = 0;
            mapMarket = new HashMap<>();
//            ArrayList<storePriceList> input = new ArrayList<>();
//            storePriceList priceList = new storePriceList();

            if(args.length > 5) {
                System.out.println("Error, usage: java ClassName input file");
                System.exit(1);
            }
            BufferedReader br;
            BufferedReader br1;
            if(args[1].equals("-m") && args[3].equals("-p")) {
                 br = new BufferedReader(new FileReader(args[2]));
                 br1 = new BufferedReader(new FileReader(args[4]));
            }else if(args[1].equals("-p") && args[3].equals("-m")){
               br = new BufferedReader(new FileReader(args[4]));
                br1 = new BufferedReader(new FileReader(args[2]));
            }else{
                br = null;
                br1 = null;
                System.out.println("Error, input files not found");
            }


            int counterMarket = 0;
            while ((market = br.readLine()) != null) {

//               System.out.println(market);
               String[] keyValueMarket = market.split(" ");
               if(keyValueMarket.length > 1 && keyValueMarket.length < 3){
                   String key = keyValueMarket[0];
                   if(Character.isDigit(keyValueMarket[1].toCharArray()[1]) & (Integer.parseInt(keyValueMarket[1]) > 0)){
                       int value = Integer.parseInt(keyValueMarket[1]);
                       mapMarket.put(key, value);
//                       System.out.println("Invalid market price file, program cannot proceed");
//                       System.exit(0);

                   }
                   else {
                       System.out.println("Invalid market price file, program cannot proceed");
                       System.exit(0);
//                       int value = Integer.parseInt(keyValueMarket[1]);
//                       mapMarket.put(key, value);
                   }
//                   mapMarket.put(key, value);
               }else if (Character.isDigit(keyValueMarket[0].toCharArray()[0])) {
                   counterMarket = Integer.parseInt(keyValueMarket[0]);
               }else {
                   System.out.println("Invalid Market Price");
                   System.exit(0);
               }


            }
            if(mapMarket.size() != counterMarket){
                System.out.println("Invalid Market Price");
                System.exit(0);
            }
//            System.out.println(mapMarket);

            while ((price = br1.readLine()) != null){
//                System.out.println(price);
                if(!price.equals("\n")) {
                    String[] keyValuePrice = price.split(" ");
                    if (Character.isDigit(keyValuePrice[0].toCharArray()[0])) {
                        offsetPrice = Integer.parseInt(keyValuePrice[0]);  //To count input lines we received in keyValuePrice[0]
                        counterPrice = 0;
                        arrayPrice.add(Integer.parseInt(keyValuePrice[1]));
                        indexArrayList = arrayPrice.size() - 1;
//                        mapPrice[Integer.parseInt(keyValuePrice[1])] = new HashMap<>();
                        mapPrice.put(indexArrayList, new HashMap<>());
                    } else if(!Character.isDigit(keyValuePrice[0].toCharArray()[0]) && keyValuePrice.length > 1 && keyValuePrice.length < 3 ) {
                        if (counterPrice <= offsetPrice) {
                            String[] ValuePrice = price.split(" ");

                            if (counterPrice == 1) {

                                HashMap<String, Integer> tempMap = new HashMap<>();
                                tempMap.put(ValuePrice[0], Integer.parseInt(ValuePrice[1]));
                                mapPrice.put(indexArrayList, tempMap);

                            } else {
                                mapPrice.get(indexArrayList).put(ValuePrice[0], Integer.parseInt(ValuePrice[1]));
                            }
                        }
                    }else {
                        System.out.println("Invalid Price List");
                    }

                }
            }
//            System.out.println("Array Price" + arrayPrice);
//            System.out.println(mapPrice);
            BruteForce bf = new BruteForce();
            bf.BruteForce(mapPrice, arrayPrice, mapMarket).toString();
//            Solution s = new Solution();
//            System.out.println("\n");
//            int[] n = {202,120,80};
//            System.out.println(s.subsets(n, 200));

        }
    }



