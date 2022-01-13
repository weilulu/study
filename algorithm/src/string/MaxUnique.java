package string;

/**
 * 找到字符串的最长无重复字符子串
 * 给定一个字符串str，返回str的最长无重复字符子串的长度，如：
 * str="abcd",返回4
 * str="aabcb",返回3
 * 这个不太明白
 */
public class MaxUnique {

    public int maxUnique(String str){
        if(str == null || str.equals("")){
            return 0;
        }
        char[] chars = str.toCharArray();
        //哈希表map里key表示某个字符，value为这个字符最近一次出现的位置
        int[] map = new int[256];
        for (int i=0;i<256;i++){
            map[i] = -1;
        }
        int len = 0;
        int pre = -1;
        int cur = 0;
        //map[chars[i]]的值表示之前的遍历中最近一次出现chars[i]字符的位置
        for (int i=0;i != chars.length;i++){
            pre = Math.max(pre,map[chars[i]]);
            cur = i - pre;
            len = Math.max(len,cur);
            map[chars[i]] = i;
        }
        return len;
    }



    public static void main(String[] args) {
        String test = "11234";
        MaxUnique unique = new MaxUnique();
        int i = unique.maxUnique(test);
        System.out.println(i);
    }
}
