package com.oneupalbums.controller;

import com.oneupalbums.model.Album;
import com.oneupalbums.model.Genre;
import com.oneupalbums.service.AlbumService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class AlbumController {

    //
    // auto-wired
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }



//TODO CHANGE MADE HERE added material

    @GetMapping("/albums/view")
//    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        List<Album> albums = albumService.getAlbums();
        model.addAttribute("albums", albums);


        return "view";
    }





    /*
        Add album
     */

    //TODO CHANGE MADE HERE album-> albums

    @GetMapping("/albums/add")
    public String addAlbumForm(Model model) {
        return "addalbum";
    }


    //TODO CHANGE MADE HERE album-> albums

    @PostMapping("/albums/add")
    public String addAlbumSubmit(Model model,@RequestParam("title") String title,@RequestParam("artist") String artist,@RequestParam("dateReleased") /*Date*/String  dateReleased, @RequestParam("genre") Genre genre,@RequestParam("noOfTracks") Integer noOfTracks,@RequestParam("price") Double price) {
//    public String addAlbumSubmit(Model model, @RequestParam("albumName") String albumName, @RequestParam("albumDeveloper") String albumDeveloper, @RequestParam("albumPrice") String albumPrice) {

        //
        //  Add the album to the database
        try {
            this.albumService.addAlbum(title, artist,LocalDate.parse(dateReleased)/*dateReleased*/,genre,noOfTracks, price);
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
            return "addalbum";
        }

        model.addAttribute("success", "Your album was successfully added");
        return "redirect:/success";
    }

    /*
        Edit album
     */
    @GetMapping("/albums/edit/{albumID}")
    public String editAlbumPage(@PathVariable Integer albumID, Model model) {

        //
        //  find the album
        Album album = albumService.findAlbumByID(albumID);

        //
        //  set the model
        model.addAttribute("albumID", album.getAlbumId());
        model.addAttribute("title", album.getTitle());
        model.addAttribute("artist", album.getArtist());
        model.addAttribute("dateReleased", album.getDateReleased());
        model.addAttribute("genre", album.getGenre());
        model.addAttribute("noOfTracks", album.getNoOfTracks());
        model.addAttribute("price", album.getPrice());
        return "editalbum";
    }

    @PostMapping("/albums/edit")
    public String editAlbumSubmit(Model model, @RequestParam Integer albumID, @RequestParam String title, @RequestParam String artist, @RequestParam /*Date dateReleased*/ String dateReleased, @RequestParam Genre genre, @RequestParam Integer noOfTracks, @RequestParam Double price) {
//    public String editAlbumSubmit(@RequestParam Integer albumID, @RequestParam String albumName, @RequestParam String albumDeveloper, @RequestParam String albumPrice, Model model) {

        //
        //  submit the form

//        Date dateReleasedMod=new Date(dateReleased);
        try {
            this.albumService.editAlbum(albumID, title,artist, LocalDate.parse(dateReleased) /*dateReleasedMod.toString()*/,genre,noOfTracks, price);
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
            //TODO CHANGE MADE , added next line
            return "editalbum";
        }

        //
        //  success
        model.addAttribute("success", "Your album was successfully modified");
        return "redirect:/success";
    }

}
