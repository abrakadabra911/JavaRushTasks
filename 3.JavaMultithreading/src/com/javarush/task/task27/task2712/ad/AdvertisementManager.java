package com.javarush.task.task27.task2712.ad;


import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

public class AdvertisementManager {
   private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
   int timeSeconds;

   public AdvertisementManager(int timeSeconds) {
      this.timeSeconds = timeSeconds;
   }

   public Comparator<List<Advertisement>> bestCombinationComparator
           = new Comparator<List<Advertisement>>() {
      public int compare(List<Advertisement> combination1, List<Advertisement> combination2) {
         long profitOfCombination1 = 0;
         long profitOfCombination2 = 0;
         long durationOfCombination1 = 0;
         long durationOfCombination2 = 0;
         int quantityOfVideosOfCombination1 = combination1.size();
         int quantityOfVideosOfCombination2 = combination2.size();

         for (Advertisement i : combination1) {
            profitOfCombination1 += i.getAmountPerOneDisplaying();
            durationOfCombination1 += i.getDuration();
         }
         for (Advertisement j : combination2) {
            profitOfCombination2 += j.getAmountPerOneDisplaying();
            durationOfCombination2 += j.getDuration();
         }

         if (profitOfCombination1 > profitOfCombination2) return -1;  //descending order
         if (profitOfCombination1 < profitOfCombination2) return 1;
         if (durationOfCombination1 > durationOfCombination2) return -1;   //descending order
         if (durationOfCombination1 < durationOfCombination2) return 1;
         if (quantityOfVideosOfCombination1 > quantityOfVideosOfCombination2) return 1;   //ascending order
         if (quantityOfVideosOfCombination1 < quantityOfVideosOfCombination2) return -1;
         return 0;
      }
   };
      public Comparator<Advertisement> moreExpensiveVideoComparator
              = new Comparator<Advertisement>() {

         public int compare(Advertisement ad1, Advertisement ad2) {
            if(ad1.getAmountPerOneDisplaying()>ad2.getAmountPerOneDisplaying()) return -1;
            if(ad1.getAmountPerOneDisplaying()<ad2.getAmountPerOneDisplaying()) return 1;
            return 0;
         }
   };

   public void processVideos(){
      if(storage.list().isEmpty()) throw new NoVideoAvailableException();
      List<Advertisement> copyOfVideoStorage = new LinkedList<>();
      copyOfVideoStorage.addAll(storage.list());

      // creating of all possible combinations of all videos
      List<List<Advertisement>> allCombinations =  permute(copyOfVideoStorage, 0);

      // all possible combinations of all videos with  fixed maximum summ of time
      List<List<Advertisement>> combinationsMaxTime = new LinkedList<>();
      for(List<Advertisement> i: allCombinations) {
         List<Advertisement> trimmedByTime = new LinkedList<>();
         long cookingTimeOfActualCombination=0;
         for(Advertisement j: i) {
            cookingTimeOfActualCombination+= j.getDuration();
            if(cookingTimeOfActualCombination<=timeSeconds) { //cheking if summ of videos duration is less then maximum time of cooking.
               trimmedByTime.add(j);
            }
            else{break;}
         }
         if(!trimmedByTime.isEmpty()) combinationsMaxTime.add(trimmedByTime);
      }

      combinationsMaxTime.sort(bestCombinationComparator);   // sorting of best-profit combinations
      List<Advertisement> bestCombination = combinationsMaxTime.get(0);
      bestCombination.sort(moreExpensiveVideoComparator);   // sorting of best-profit combination of videos

      //sending statistics
      long amount=0;
      int totalDuration=0;
      for(Advertisement x: bestCombination) {
         amount+=x.getAmountPerOneDisplaying();
         totalDuration+=x.getDuration();
      }
      StatisticManager.getInstance().register(new VideoSelectedEventDataRow(bestCombination, amount, totalDuration));

      // displaying of videos
      for(Advertisement x: bestCombination) {
         ConsoleHelper.writeMessage(x.getName() + " is displaying... " + x.getAmountPerOneDisplaying() + ", "
                 + (int)((double)x.getAmountPerOneDisplaying()/x.getDuration()*1000));
         x.revalidate();
       }

   }

  public List<List<Advertisement>> permute(java.util.List<Advertisement> arr2, int k){
     List<List<Advertisement>> result = new LinkedList<>();
     List<Advertisement> arr = new ArrayList<>();
     arr.addAll(arr2);
      for(int i = k; i < arr.size(); i++){
         java.util.Collections.swap(arr, i, k);
         result.addAll(permute(arr, k+1));
         java.util.Collections.swap(arr, k, i);
      }
      if (k == arr.size() -1){
         result.add(arr);
      }
      return result;
   }

}
