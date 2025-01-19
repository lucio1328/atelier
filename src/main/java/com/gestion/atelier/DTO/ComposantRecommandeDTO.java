package com.gestion.atelier.DTO;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class ComposantRecommandeDTO {
    private Long id;
    private Date date;
    private PiecesDetacheesDTO pieceDetachee;

    public ComposantRecommandeDTO(){

    }

    public ComposantRecommandeDTO(Long id, Date date, PiecesDetacheesDTO pieceDetachee) {
        this.id = id;
        this.date = date;
        this.pieceDetachee = pieceDetachee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public PiecesDetacheesDTO getPieceDetachee() {
        return pieceDetachee;
    }

    public void setPieceDetachee(PiecesDetacheesDTO pieceDetachee) {
        this.pieceDetachee = pieceDetachee;
    }

    //=====================================================================================================
    public String getMonthName() {
        if (this.date != null) {
            LocalDate localDate = this.date.toLocalDate();
            return localDate.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
        }
        return null;
    }
}