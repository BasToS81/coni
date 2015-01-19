package com.gor.sellphotos.utils;

public class FinanceUtils {

    public static double getTTCFromHT(double valueHT, double tva) {
        return valueHT * (1 + (tva / 100));
    }

    public static double getHTFromTTC(double valueTTC, double tva) {
        return valueTTC / (1 + (tva / 100));
    }

}
