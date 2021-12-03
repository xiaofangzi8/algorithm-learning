public class RemoveElement {
    public static void main(String[] args) {
        int[] nums = {5,2,2,3};
        int val = 3;
        //System.out.println(removeElement(nums,val));
        removeElement(nums,val);
    }

    //暴力解决方法
    public static int removeElement(int[] nums, int val){
        int size = nums.length;
        for(int i = 0; i < size; i++){
            //目标元素后的元素集体像前移动一位
            if(nums[i] == val){
                for(int j=i+1; j<size;j++){
                    nums[j-1] = nums[j];
                }
                //目标后的元素都像前移动一位，所以i要减一
                i--;
                //数组长度减一
                size--;
            }
        }
        for(int k=0; k< nums.length;k++){
            System.out.println(nums[k]);
        }
        return size;
    }

    //暴力解决方法
    public static int removeElement2(int[] nums, int val){
        int size = nums.length;
        int slowIndex  = 0;

        for(int fastIndex = 0; fastIndex< size; fastIndex++){
            //目标元素后的元素集体像前移动一位
            if(nums[fastIndex] != val){
                nums[slowIndex] = nums[fastIndex];
                slowIndex++;
            }
        }
        return slowIndex;
    }


}
