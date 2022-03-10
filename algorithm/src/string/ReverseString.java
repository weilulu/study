package string;

/**
 * 反转字符串，如：
 * "abc"->"cba"
 */
public class ReverseString {
    public String reverse1(String str){
        return new StringBuilder(str).reverse().toString();
    }

    /**
     * 从后往前遍历
     * @param str
     * @return
     */
    public String reverse2(String str){
        char[] chars = str.toCharArray();
        String reverse = "";
        for (int i=chars.length-1;i>=0;i--){
            reverse += chars[i];
        }
        return reverse;
    }

    /**
     * 向前叠加
     * @param str
     * @return
     */
    public String reverse3(String str){
        String reverse = "";
        int len = str.length();
        for (int i=0;i<len;i++){
            reverse = str.charAt(i) + reverse;
        }
        return reverse;
    }

    /**
     * 反转数字
     * 如：1230->321;-12->-21;324->423
     */
    public void test() {
        int x = 1230;
        while(x % 10==0){
            x = x / 10;
        }
        String s = "";
        char[] y = String.valueOf(x).toCharArray();
        for(int i=y.length-1;i>=0;i--){
            s =  s + y[i];
        }
        if(x<0){
            System.out.println(Integer.valueOf("-"+s.substring(0, s.length()-1)));
        }else{
            System.out.println(Integer.valueOf(s));
        }
    }
}
