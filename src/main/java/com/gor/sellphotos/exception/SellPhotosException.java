package com.gor.sellphotos.exception;

/**
 *
 */
public class SellPhotosException extends RuntimeException {

    public final static String COMMANDE_NON_TROUVEE = "COMMANDE_NON_TROUVEE";

    public final static String ELEVE_NON_TROUVE = "ELEVE_NON_TROUVE";

    public final static String ELEVE_NON_EXISTANT_DANS_FAMILLE = "ELEVE_NON_EXISTANT_DANS_FAMILLE";

    public SellPhotosException(final String message) {
        super(message);
    }

    public SellPhotosException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
