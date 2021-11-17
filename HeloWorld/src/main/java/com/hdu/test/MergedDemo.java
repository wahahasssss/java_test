package com.hdu.test;

import java.util.ArrayList;

public class MergedDemo {

    public static MergedAB[] merge(ProA[] arrProA, ProB[] arrProB) {
        ArrayList<MergedAB> mergedABS = new ArrayList<>();
        for (ProA a : arrProA) {
            for (ProB b : arrProB) {
                if (a.getKey1().equals(b.getKey1()) && a.getKey2().equals(b.getKey2())) {
                    MergedAB mergedAB = new MergedAB(a.getKey1(), a.getKey2(), a.getV1(), b.getV2());
                    mergedABS.add(mergedAB);
                }
            }
        }
        MergedAB[] results = new MergedAB[mergedABS.size()];
        mergedABS.toArray(results);
        return results;
    }

    public static void main(String[] args) {
        ProA[] arrProA = new ProA[]{
                new ProA("alice", "box", "xx"),
                new ProA("alice", "bob", "yy"),
                new ProA("catch", "dog", "zz")
        };
        ProB[] arrProB = new ProB[]{
                new ProB("alice", "bob", "oo"),
                new ProB("alice", "bee", "pp")
        };
        MergedAB[] results = merge(arrProA, arrProB);
        for (MergedAB m : results) {
            System.out.println(m.toString());
        }
    }
}
