package com.API.BookManager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@DynamicUpdate
@Table(name = "tag")
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idTag")
    private Long idTag;

    @Column(name="textTag")
    private String textTag;

    @Column(name="colorTag")
    private int colorTag;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinTable(name = "tag_book",joinColumns = @JoinColumn(name = "idTag"),inverseJoinColumns = @JoinColumn(name = "idBook"))
    @JsonIgnore
    private List<BookEntity> books = new ArrayList<>();

    public TagEntity() {
    }

    public TagEntity(String textTag, int colorTag) {
        this.textTag = textTag;
        this.colorTag = colorTag;
    }

    /****************************** helpers methods ******************************/
    public void addBook(BookEntity book) {
        books.add(book);
        book.getTags().add(this);
    }

    public void removeBook(BookEntity book) {
        books.remove(book);
        book.getTags().remove(this);
    }
}