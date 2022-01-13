package string;

public class FindSubString {

    /**
     * 暴力查找法
     * @param pat
     * @param text
     * @return
     */
    public static int search(String pat,String text){
        int m = pat.length();
        int n = text.length();
        for (int i=0;i<n-m;i++){
            int j;
            for (j=0;j<m;j++){
                if(text.charAt(i+j) != pat.charAt(j)){
                    break;
                }
                if(j == m){
                    return i;//找到匹配
                }
            }
        }
        return n;//未找到匹配
    }
}
