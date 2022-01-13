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
}
