package bitset;

import java.util.BitSet;

public class BitSetTest {
    public static void main(String[] args) {
        BitSet bitSet = new BitSet();
        bitSet.set(1,true);
        bitSet.cardinality();//统计true有多少个
        System.out.println(bitSet.get(1));
        System.out.println(bitSet.get(2));
    }
}
