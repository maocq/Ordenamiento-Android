package com.example.johnmcarmona.ordenamiento.ordenamiento;

/**
 * Created by John M. Carmona on 16/11/2015.
 */
public class Bucket {

    public static void sort(int[] a, int maxVal) {
        int [] bucket = new int[maxVal+1];

        for (int i=0; i<bucket.length; i++) {
            bucket[i]=0;
        }

        for (int i=0; i<a.length; i++) {
            bucket[a[i]]++;
        }

        int outPos=0;
        for (int i=0; i<bucket.length; i++) {
            for (int j=0; j<bucket[i]; j++) {
                a[outPos++]=i;
            }
        }
    }

}
