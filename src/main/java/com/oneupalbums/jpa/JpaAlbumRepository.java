package com.oneupalbums.jpa;

import com.oneupalbums.model.Album;
import com.oneupalbums.model.Artist;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JpaAlbumRepository extends CrudRepository<Album,Integer> {
    List<Album> findByArtist(Artist artist);
}
