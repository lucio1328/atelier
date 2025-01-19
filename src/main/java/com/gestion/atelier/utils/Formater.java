package com.gestion.atelier.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Formater {
    public static String dateEnLettres(Date daty) {
        LocalDate localDate = daty.toLocalDate();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.FRANCE);
        String dateEnLettre = localDate.format(formatter);

        return dateEnLettre;
    }
}
