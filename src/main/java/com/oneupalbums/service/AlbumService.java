package com.oneupalbums.service;

import com.oneupalbums.model.Album;
import com.oneupalbums.model.Genre;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface AlbumService {
    List<Album> getAlbums();

    Album addAlbum(String title, String artist, /*Date*/ LocalDate dateReleased, Genre genre,Integer noOfTracks,Double price);

    List<Album> findAllFilteredAlbums(String filter);

    Album findAlbumByID(Integer albumID);

    Album editAlbum(Integer albumID, String title, String artist, /*Date dateReleased*/ /*String*/ LocalDate dateReleased , Genre genre, Integer noOfTracks, Double price);

    boolean deleteAlbumByID(Integer albumID);
}
