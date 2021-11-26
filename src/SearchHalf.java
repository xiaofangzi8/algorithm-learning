public class SearchHalf {
    public static void main(String[] args) {
        int[]  nums = {-1,0,3,5,9,12};
        System.out.println(search(nums,9));

    }
    public static int search(int[] nums,int target){
        if(target < nums[0] || target > nums[nums.length-1]){
            return -1;
        }

        int left = 0;
        int right = nums.length-1;
        while(left <= right){
            //右移1位 = 除以2
            int mid = left + ((right -left) >> 1);
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else if(nums[mid] > target){
                right = mid -1;
            }
        }
        return -1;
    }
}
