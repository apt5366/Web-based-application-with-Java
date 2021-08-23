package com.oneupalbums.jpa;

import com.oneupalbums.model.Album;
import com.oneupalbums.model.Artist;
import org.springframework.data.repository.CrudRepository;

public interface JpaArtistRepository extends CrudRepository<Artist,Integer> {
     Artist findArtistByArtistName(String name);
}
