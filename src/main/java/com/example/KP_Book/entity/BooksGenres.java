package com.example.KP_Book.entity;

import javax.persistence.*;


@Entity
@Table(name = "books_genres", schema = "kpschema", catalog = "")
public class BooksGenres {
    @Id
    @Basic
    @Column(name = "book_id")
    private long bookId;
    @Basic
    @Column(name = "genre_id")
    private long genreId;

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getGenreId() {
        return genreId;
    }

    public void setGenreId(long genreId) {
        this.genreId = genreId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BooksGenres that = (BooksGenres) o;

        if (bookId != that.bookId) return false;
        if (genreId != that.genreId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (bookId ^ (bookId >>> 32));
        result = 31 * result + (int) (genreId ^ (genreId >>> 32));
        return result;
    }
}
