package string;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 给定一个字符串数组，再给定整数 k ，请返回出现次数前k名的字符串和对应的次数。
 * 输入：
 * ["a","b","c","b"],2
 * 返回值：
 * [["b","2"],["a","1"]]
 * 说明：
 * "b"出现了2次，记["b","2"]，"a"与"c"各出现1次，但是a字典序在c前面，记["a","1"]，最后返回[["b","2"],["a","1"]]
 *
 */
public class TopKString {
    public static String[][] topKStrings (String[] strings, int k) {
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < strings.length; i++) {
            if (map.containsKey(strings[i])) {
                int times = map.get(strings[i]);
                times = times + 1;
                map.put(strings[i], times);
            } else {
                map.put(strings[i], 1);
            }
        }
        List<Map.Entry<String,Integer>> list = new ArrayList<>(map.entrySet());
        // 先比较值是否相同，相同按键升序进行比较，不相同按值降序比较
        Collections.sort(list,
                (o1, o2) -> (o1.getValue().compareTo(o2.getValue()) == 0 ? o1.getKey().compareTo(o2.getKey()) : o2.getValue().compareTo(o1.getValue()))
        );

        String[][] array = new String[k][2];
        for(int i=0;i<k;i++){
            array[i][0] = list.get(i).getKey();
            array[i][1] = String.valueOf(list.get(i).getValue());
        }
        return array;
    }

    public static void main(String[] args) {
        String[] test={"a","b","c","b"};
        int k = 2;
        String[][] strings = topKStrings(test, k);
        String s = Arrays.deepToString(strings);
        System.out.println(s);
    }
}
