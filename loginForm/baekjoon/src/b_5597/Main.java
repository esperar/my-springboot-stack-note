package b_5597;

import java.util.*;

public class Main {

    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      Map<Integer , String> map = new HashMap<>();
      int[] inputArr = new int[28];
      for(int i = 1; i < 31; i++) {
          map.put(i,"");
      }

      for(int i = 0; i < inputArr.length; i++){
          inputArr[i] = sc.nextInt();
      }

      for(int i = 0; i < inputArr.length; i++) {
          if(map.containsKey(inputArr[i])) {
              map.remove(inputArr[i]);
          }
      }

      for(int key : map.keySet()) {
          System.out.println(key);
      }
    }
}