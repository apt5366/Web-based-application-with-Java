package com.oneupalbums.service.impl;

import com.oneupalbums.model.Album;
import com.oneupalbums.model.Artist;
import com.oneupalbums.model.Genre;
import com.oneupalbums.repository.AlbumRepository;
import com.oneupalbums.service.AlbumService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements AlbumService {

    // field-injection (not recommended)
//    @Autowired  // managed by spring!
//    private AlbumRepository albumRepository;

    // managed by spring!
    private final AlbumRepository albumRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository) {

        // instance managed by the developer
//        albumRepository = new AlbumRepositoryImpl();

        // injected by constructor!
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Album> getAlbums() {
        return albumRepository.getAlbums();
    }

    @Override
    public Album addAlbum(String title, String artist, /*Date*/ LocalDate dateReleased, Genre genre, Integer noOfTracks, Double price){

        //
        //  validation
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("album name cannot be null");
        }

/*
        Double price2;
        try {
            price2 = Double.valueOf(price);
        } catch (NumberFormatException nfex) {
            throw new IllegalArgumentException("invalid album price");
        }*/

        //  test if null/empty
       /*
       // without daatbase
       Integer nextID = albumRepository.getNextAlbumID();
        Album album = new Album(nextID, title, artist,dateReleased,genre,noOfTracks, price);*/


        Album album = new Album( title, new Artist(artist),dateReleased,genre,noOfTracks, price);
        return albumRepository.addAlbum(album);
    }

    @Override
    public List<Album> findAllFilteredAlbums(String filter) {

        List<Album> allAlbums = albumRepository.getAlbums();
        /*return allAlbums.stream()
                .filter(g -> g.getTitle().toLowerCase().contains(filter))
                .collect(Collectors.toList());
        */


        //TODO PLEASE CHECK THE FOLLOWING CODE...
        /*if(allAlbums.stream()
                .filter(g -> g.getTitle().toLowerCase().contains(filter))
                .collect(Collectors.toList())== null) {
            return allAlbums.stream()
                    .filter(g -> g.getTitle().toLowerCase().contains(filter))
                    .collect(Collectors.toList());*/

        if(!allAlbums.stream()
                .filter(g -> g.getTitle().toLowerCase().contains(filter))
                .collect(Collectors.toList()).isEmpty()) {
            /*List<Album> list = new ArrayList<>();
            for (Album g : allAlbums) {
                if (g.getTitle().toLowerCase().contains(filter)) {
                    list.add(g);
                }
            }*/
            return allAlbums.stream()
                    .filter(g -> g.getTitle().toLowerCase().contains(filter))
                    .collect(Collectors.toList());

        }
        else{
            return allAlbums.stream()
                    .filter(g -> g.getArtist().getArtistName().toLowerCase().contains(filter))
                    .collect(Collectors.toList());
        }

    }

    @Override
    public Album findAlbumByID(Integer albumID) {
        return albumRepository.findAlbumByID(albumID);
    }

    /*@Override
    public Album editAlbum(Integer albumID, String title, String artist, Date dateReleased, Genre genre, Integer noOfTracks, Double price) {
        return albumRepository.editAlbum(albumID,title,artist,dateReleased,genre,noOfTracks,price );
    }*/

    @Override
    public Album editAlbum(Integer albumID, String title, String artist, /*Date*/ /*String*/LocalDate dateReleased/*Released*/, Genre genre, Integer noOfTracks, Double price) {

//        Date dateReleasedMod=new Date(dateReleased);
        return albumRepository.editAlbum(albumID,title,new Artist(artist), dateReleased/*dateReleasedMod.toString()*/,genre,noOfTracks,price );
    }

    @Override
    public boolean deleteAlbumByID(Integer albumID) {
        return albumRepository.deleteAlbumByID(albumID);
    }
}
