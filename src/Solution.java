import java.util.*;
class Solution {
    public List<List<Integer>> subsets(int[] nums, int weight)
    {
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;

        int maxProfit = 0;
        //loop will run for 2^n-1 times
        for(int i=0;i<(1<<n) ; i++)
        {
            int sum = 0;
            ArrayList<Integer> list = new ArrayList<>();
            for(int k=0;k<n;k++)
            {
                if((i & (1<<k))>0) {
                    System.out.println(list.add(nums[k]));
                }

            }
            System.out.println(list);
            for(int a = 0; a < list.size(); a++){
                sum = sum + list.get(a);
            }
            if(sum > maxProfit && sum <= weight){
                maxProfit = sum;
            }
//            ans.add(new ArrayList<>(list));
        }
        System.out.println(maxProfit);
        return ans;
    }
}