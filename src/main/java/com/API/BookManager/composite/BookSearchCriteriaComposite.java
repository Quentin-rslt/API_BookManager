package com.API.BookManager.composite;

import lombok.Data;

@Data
public class BookSearchCriteriaComposite {
    private String title;
    private String author;
    private int numberOPStart;
    private int numberOPEnd;
    private double notePersoStart;
    private double notePersoEnd;
    private int releaseYearStart;
    private int releaseYearEnd;
    private String summary;
    private boolean isFav;
}
