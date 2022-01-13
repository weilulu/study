package string;

/**
 * 判断字符数组中是否所有的字符都只出现一次
 * 举例：
 * char=['a','b','c'],返回true
 * char=['a','b','a'],返回false
 */
public class IsOnlyOne {
    public boolean isUnique(char[] chars){
        if(chars == null){
            return true;
        }
        boolean[] map = new boolean[256];
        for (int i=0;i<chars.length;i++){
            if(map[chars[i]]){//如果为true，说明之前已经设置为true了，也就是出现过了
                return false;
            }
            map[chars[i]] = true;
        }
        return true;
    }
}
