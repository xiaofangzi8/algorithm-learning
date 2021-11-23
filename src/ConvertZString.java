import java.util.ArrayList;
import java.util.List;

public class ConvertZString {
    public static void main(String[] args) {
        String str="PAYPALISHIRING";
        String result= convert(str,3);
        System.out.println(result);
    }

    public static String convert(String s,int numRows){
        if(numRows <=1) {
            return s;
        }
        List<StringBuilder> rows = new ArrayList<StringBuilder>();
        for(int i=0; i< numRows; i++){
            rows.add(new StringBuilder());
        }
        int j=0;
        int flag = -1;

        for(char c:s.toCharArray()){
            rows.get(j).append(c);
            if(j==0 || j==numRows-1){
                flag = -flag;
            }
            j += flag;
        }

        StringBuilder ret = new StringBuilder();
        for(StringBuilder row:rows){
            ret.append(row);
        }
        return ret.toString();

    }
}
