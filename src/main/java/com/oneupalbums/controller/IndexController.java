package com.oneupalbums.controller;

import com.oneupalbums.model.Album;
import com.oneupalbums.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class IndexController {

    // field-injection

//    @Autowired
//    private AlbumService albumService;

    private final AlbumService albumService;

    //
    //  constructor injection!
    public IndexController(AlbumService albumService) {

        // managed instance by developer
//        this.albumService = new AlbumServiceImpl();

        // by spring via the constructor!
        this.albumService = albumService;
    }



    /*@GetMapping("/")
//    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        List<Album> albums = albumService.getAlbums();
        model.addAttribute("albums", albums);
        return "index";
    }*/


    @GetMapping("/")
//    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
//        List<Album> albums = albumService.getAlbums();
//        model.addAttribute("albums", albums);
        return "index";
    }

    @GetMapping("/search")
    public String indexFiltered(Model model, @RequestParam("term") String term) {
        List<Album> albums = albumService.findAllFilteredAlbums(term);
        model.addAttribute("albums", albums);

        //TODO CHANGES MADE index-> search
        return "search";
    }

    @GetMapping("/success")
    public String indexWithSuccess(Model model) {
        List<Album> albums = albumService.getAlbums();
        model.addAttribute("albums", albums);
        model.addAttribute("success", "Your changes were successfully saved.");

        //TODO CHANGES MADE index-> view
        return "view";
    }


//TODO CHANGES MADE next function added
/*

    @GetMapping("/error")
    public String indexWithError(Model model) {
        List<Album> albums = albumService.getAlbums();
        model.addAttribute("albums", albums);
        model.addAttribute("error", "ERROR! Your changes were not added.");

        //TODO CHANGES MADE index-> view
        return "view";
    }
*/




    @GetMapping("/albums/delete/{albumID}")
    public String deleteAlbum(@PathVariable Integer albumID) {
        albumService.deleteAlbumByID(albumID);
        return "redirect:/success";
    }

}
