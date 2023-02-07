package com.API.BookManager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Date;

@Getter
@Setter
@Entity
@DynamicUpdate
@Table(name = "reading")
public class ReadingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idBook")
    private Long idReading;

    @Temporal(TemporalType.DATE)
    @Column(name="startReadingDate")
    private Date startReadingDate;

    @Temporal(TemporalType.DATE)
    @Column(name="endReadingDate")
    private Date endReadingDate;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="idBook")
    private BookEntity book;


    public ReadingEntity() {
    }

    public ReadingEntity(Date startReadingDate, Date endReadingDate) {
        super();
        this.startReadingDate = startReadingDate;
        this.endReadingDate = endReadingDate;
    }

}