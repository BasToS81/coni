package com.gor.sellphotos.utils;

import java.util.Date;

import com.gor.sellphotos.dao.Tva;
import com.gor.sellphotos.repository.TvaRepository;

public class FinanceUtils {

    public static double getTTCFromHT(double valueHT, double tva) {
        return valueHT * (1 + (tva / 100));
    }

    public static double getHTFromTTC(double valueTTC, double tva) {
        return valueTTC / (1 + (tva / 100));
    }

    public static double getCurrentTTCFromHT(double valueHT, TvaRepository tvaRepository) {
        return getTTCFromHT(valueHT, getCurrentTVA(tvaRepository));
    }

    public static double getCurrentHTFromTTC(double valueTTC, TvaRepository tvaRepository) {
        return getHTFromTTC(valueTTC, getCurrentTVA(tvaRepository));
    }

    public static double getTVAValue(Date dateValidation, TvaRepository tvaRepository) {
        Tva tva = null;
        double resultatTva = 0;
        if (dateValidation != null) {
            tva = tvaRepository.findByDateDeDemande(dateValidation, dateValidation);
        } else {
            tva = tvaRepository.findByDateCourante();
        }
        if (tva != null) {
            resultatTva = tva.getTva();
        }

        return resultatTva;
    }

    public static double getCurrentTVA(TvaRepository tvaRepository) {
        return getTVAValue(null, tvaRepository);
    }

}
