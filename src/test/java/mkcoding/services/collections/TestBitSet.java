package mkcoding.services.collections;

import java.util.Arrays;
import java.util.BitSet;

/**
 * Created by mx on 16/12/23.
 */
public class TestBitSet {
    public static void main(String[] args) {
        BitSet separators = new BitSet(128);
        separators.set('(');
        separators.set(')');
        separators.set('<');
        separators.set('>');
        separators.set('@');
        separators.set(',');
        separators.set(';');
        separators.set(':');
        separators.set('\\');
        separators.set('\"');
        separators.set('/');
        separators.set('[');
        separators.set(']');
        separators.set('?');
        separators.set('=');
        separators.set('{');
        separators.set('}');
        separators.set(' ');
        separators.set('\t');

//        System.out.println(separators.toString());

        System.out.println(toStringBitSet(separators));
    }

    public static String toStringBitSet(BitSet bitSet) {
        StringBuilder b = new StringBuilder(6 * 12 + 2);
        b.append('{');

        int i = bitSet.nextSetBit(0);
        if (i != -1) {
            b.append(i);
            for (i = bitSet.nextSetBit(i + 1); i >= 0; i = bitSet.nextSetBit(i + 1)) {
                int endOfRun = bitSet.nextClearBit(i);
                do {
                    b.append(", ").append(i);
                }
                while (++i < endOfRun);
            }
        }

        b.append('}');

        return b.toString();
    }
}
