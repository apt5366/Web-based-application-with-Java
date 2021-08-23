package com.oneupalbums.repository.impl;

import com.oneupalbums.jpa.JpaAlbumRepository;
import com.oneupalbums.jpa.JpaArtistRepository;
import com.oneupalbums.model.Album;
import com.oneupalbums.model.Artist;
import com.oneupalbums.model.Genre;
import com.oneupalbums.repository.AlbumRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class AlbumRepositoryImpl implements AlbumRepository {

    //
    // instance data
//    private List<Album> albumList;

    //
    //  PS6
    private final JpaAlbumRepository jpaAlbumRepository;
    private final JpaArtistRepository jpaArtistRepository;

    public AlbumRepositoryImpl(JpaAlbumRepository jpaAlbumRepository, JpaArtistRepository jpaArtistRepository) {
        this.jpaAlbumRepository = jpaAlbumRepository;
//        albumList = new ArrayList<>();
        this.jpaArtistRepository = jpaArtistRepository;
    }

    @Override
    public List<Album> getAlbums() {
//        return albumList;

        return (List<Album>) jpaAlbumRepository.findAll();
    }

    @Override
    public Album addAlbum(Album album) {

//        albumList.add(album);
//        return album;

        if(jpaArtistRepository.findArtistByArtistName(album.getArtist().getArtistName())==null){
            jpaArtistRepository.save(album.getArtist());
        }
        album.setArtist(jpaArtistRepository.findArtistByArtistName(album.getArtist().getArtistName()));
        return jpaAlbumRepository.save(album) ;
    }

    /*@Override
    public Integer getNextAlbumID() {

        Album maxAlbum = albumList.stream().max(Comparator.comparing(Album::getAlbumID)).get();
        return maxAlbum.getAlbumID() + 1;
    }
*/


    @Override
    public Album findAlbumByID(Integer albumID) {

        //
        //  solution 1: using Optional

//        Optional<Album> foundAlbum = albumList.stream()
//                .filter(g -> g.getAlbumId().equals(albumId)).findAny();
//        if (foundAlbum.isPresent()) {
//            return foundAlbum.get();
//        } else {
//            throw new IllegalStateException("Album with ID " + albumId + " is not found!");
//        }

        //
        //  Solution 2: using Google Guava
//        return albumList.stream().filter(g -> g.getAlbumID().equals(albumID)).collect(MoreCollectors.onlyElement());


        //
        //  PS6
        Optional<Album> album = jpaAlbumRepository.findById(albumID);
        if (album.isPresent()) {
            return album.get();
        }
        throw new IllegalStateException("Game with ID " + albumID + " is not found!");
    }



    //TODO COCE ADDED ERE
    @Override
    public Album findArtistByID(Integer albumID) {

        //
        //  solution 1: using Optional

//        Optional<Album> foundAlbum = albumList.stream()
//                .filter(g -> g.getAlbumId().equals(albumId)).findAny();
//        if (foundAlbum.isPresent()) {
//            return foundAlbum.get();
//        } else {
//            throw new IllegalStateException("Album with ID " + albumId + " is not found!");
//        }

        //
        //  Solution 2: using Google Guava
//        return albumList.stream().filter(g -> g.getAlbumID().equals(albumID)).collect(MoreCollectors.onlyElement());


        //
        //  PS6
        Optional<Album> album = jpaAlbumRepository.findById(albumID);
        if (album.isPresent()) {
            return album.get();
        }
        throw new IllegalStateException("Game with ID " + albumID + " is not found!");
    }



    @Override
//    public Album editAlbum(Integer albumID, String albumName, String albumDeveloper, String albumPrice) {
    public Album editAlbum(Integer albumID, String title, /*String*/ Artist artist, /*Date*/ /*String*/ /*dateReleased*/ LocalDate dateReleased, Genre genre, Integer noOfTracks, Double price) {

        //
        //  edit the existing album
        Album existing = findAlbumByID(albumID);

        //TODO MOD CODE HERE
//        Artist ex_art=findArtistByID();
        if (existing == null) {
            throw new IllegalStateException("album with ID not found");
        }

      if(!existing.getArtist().getArtistName().equals(artist.getArtistName())) {

          if ((jpaArtistRepository.findArtistByArtistName(artist.getArtistName()) != null) && jpaAlbumRepository.findByArtist(findAlbumByID(albumID).getArtist()).size() == 1) {
              jpaArtistRepository.delete(findAlbumByID(albumID).getArtist());
              existing.setArtist(jpaArtistRepository.findArtistByArtistName(artist.getArtistName()));
          } else {
//        if(jpaAlbumRepository.findByArtist(findAlbumByID(albumID).getArtist()).size()==1){
              if (jpaAlbumRepository.findByArtist(findAlbumByID(albumID).getArtist()).size() == 1) {
                  jpaArtistRepository.deleteById(findAlbumByID(albumID).getArtist().getArtistId());

                  jpaArtistRepository.save(artist);
              }
              existing.setArtist(jpaArtistRepository.findArtistByArtistName(artist.getArtistName()));
          }

      }


//        Date dateReleasedMod=new Date(dateReleased);

        existing.setTitle(title);
//        existing.setArtist(artist);
//        existing.setDateReleased(dateReleasedMod);
        existing.setDateReleased(dateReleased);
        existing.setGenre(genre);
        existing.setNoOfTracks(noOfTracks);
        existing.setPrice(Double.valueOf(price));
//        return existing;
        //todo foll line added to replce line above
        return jpaAlbumRepository.save(existing);
    }

    @Override
    public boolean deleteAlbumByID(Integer albumID) {
        /*albumList = albumList.stream().filter(g -> !g.getAlbumID().equals(albumID)).collect(Collectors.toList());
        return true;*/

        //
        //  PS6

        if(jpaAlbumRepository.findByArtist(findAlbumByID(albumID).getArtist()).size()==1){
            jpaArtistRepository.deleteById(findAlbumByID(albumID).getArtist().getArtistId());
        }


        jpaAlbumRepository.deleteById(albumID);

        return true;
    }
}
