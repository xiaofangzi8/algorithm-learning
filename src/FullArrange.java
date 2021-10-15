import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FullArrange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入排列字符串(逗号分割)：");
        if(scanner.hasNext()){
            String input = scanner.next();
            String[]  arryStr = input.split(",");
            List<String> resultList = arrangeFunc(arryStr);
            for(String result:resultList){
                System.out.print(result + " ");
            }
        }
    }

    public static List<String> arrangeFunc(String[] arry){
        List<String> result = new ArrayList<>();
        int product = 1;
        for(int i=0; i < arry.length;i++){
            String item = arry[i];
            if(item.length() != 3 || (item.indexOf("A") == -1 && item.indexOf("C") == -1)){
                result.add("非规范项");
                continue;
            }

            int num1 = Integer.parseInt(item.substring(1)) / 10;
            int num2 = Integer.parseInt(item.substring(1)) % 10;

            if(item.indexOf("A") != -1){
                product = productFunc(num1,num1-num2+1);
            }else if(item.indexOf("C") != -1){
                product = productFunc(num1,num1-num2+1)/productFunc(num2,1);
            }
            result.add(String.valueOf(product));
        }

        return result;
    }

    public static int productFunc(int n1,int n2){
        int result = 1;
        for(int i=n1; i>=n2;i--){
            result *=i;
        }
        return result;
    }

}
