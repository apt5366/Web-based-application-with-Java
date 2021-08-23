package com.oneupalbums.repository;

import com.oneupalbums.model.Album;
import com.oneupalbums.model.Artist;
import com.oneupalbums.model.Genre;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface AlbumRepository {
    List<Album> getAlbums();

    Album addAlbum(Album album);

//    Integer getNextAlbumID();

    Album findAlbumByID(Integer albumID);

    //TODO COCE ADDED ERE
    Album findArtistByID(Integer albumID);

    Album editAlbum(Integer albumID, String title, /*String*/ Artist artist, /*Date dateReleased*/ /*String*/ LocalDate dateReleased, Genre genre, Integer noOfTracks, Double price);

    boolean deleteAlbumByID(Integer albumID);
}
