package com.example.android.booklist;

/**
 * Book
 * A custom class to store Book information
 */
class TheBook{

    final String title;
    final String authors;
    final String smallThumbnailLink;


    public TheBook(
            String title,
            String authors,
            String smallThumbnailLink
    ) {

        this.title = title;
        this.authors = authors;
        this.smallThumbnailLink = smallThumbnailLink;

    }

}
