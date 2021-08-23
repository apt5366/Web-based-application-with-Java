package com.oneupalbums.model;

import org.checkerframework.checker.units.qual.A;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Album {

    //
    //  instance data

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "album_id_seq")
    @SequenceGenerator(name = "album_id_seq", sequenceName = "album_id_seq", allocationSize = 100)
    private Integer albumId;
    private String title;
    private LocalDate /*Date*/ /*String*/  dateReleased;
    private Genre genre;
    private Integer noOfTracks;
    private Double price;
//    private String artist;

    @ManyToOne
    @JoinColumn(name="artist_id")
    private Artist artist;
//    private Integer artistId;

    public Album() {
    }

//    public Album(String title, String artist, LocalDate dateReleased, Genre genre, Integer noOfTracks, Double price) {
    public Album(String title, Artist artist, LocalDate dateReleased, Genre genre, Integer noOfTracks, Double price) {
        this.title = title;
        this.artist = artist;
        this.dateReleased = dateReleased;
        this.genre = genre;
        this.noOfTracks = noOfTracks;
        this.price = price;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public /*String*/ Artist getArtist() {
        return artist;
    }

    public void setArtist(/*String*/ Artist artist) {
        this.artist = artist;
    }

    public LocalDate getDateReleased() {
        return dateReleased;
    }

    public void setDateReleased(LocalDate dateReleased) {
        this.dateReleased = dateReleased;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Integer getNoOfTracks() {
        return noOfTracks;
    }

    public void setNoOfTracks(Integer noOfTracks) {
        this.noOfTracks = noOfTracks;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
