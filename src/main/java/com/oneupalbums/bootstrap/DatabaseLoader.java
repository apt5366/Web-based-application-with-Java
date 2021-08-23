package com.oneupalbums.bootstrap;

import com.oneupalbums.model.Album;
import com.oneupalbums.model.ApplicationUser;
import com.oneupalbums.model.Artist;
import com.oneupalbums.model.Genre;
import com.oneupalbums.repository.AlbumRepository;
import com.oneupalbums.repository.ApplicationUserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DatabaseLoader implements ApplicationListener<ContextRefreshedEvent> {

    //
    //  instance data
    // instance managed by spring!
    private final AlbumRepository albumRepository;
    private final ApplicationUserRepository applicationUserRepository;

    public DatabaseLoader(AlbumRepository albumRepository, ApplicationUserRepository applicationUserRepository) {

        // instance managed by the developer
//        this.albumRepository = new AlbumRepositoryImpl();

        // injected by constructor!
        this.albumRepository = albumRepository;
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("This will run when Spring starts!");

        //
        //  Initialize the database

       /*
       // DONOT USE this
       this.albumRepository.addAlbum(new Album(1, "Backstreet's Back", "Backstreet Boys", new Date (97,8,12), Genre.POP,8,50.0));
        this.albumRepository.addAlbum(new Album(2, "Let's Dance", "David Bowie", new Date(83,4,14),Genre.POST_DISCO,8,65.0));
        this.albumRepository.addAlbum(new Album(3, "Bad", "Michael Jackson", new Date( 87,8,31),Genre.POP,8,100.0));
        this.albumRepository.addAlbum(new Album(4, "21t Century Breakdown", "Green Day", new Date(109,5,15),Genre.PUNK,23,75.0));
        this.albumRepository.addAlbum(new Album(5, "A Head Full of Dreams", "Coldplay",new Date(115,12,4),Genre.INDIE,8, 59.99));*/


      /* //this is the one used
        this.albumRepository.addAlbum(new Album(1, "Backstreet's Back", "Backstreet Boys", LocalDate.parse("1997-08-12")*//*(97,8,12)*//*, Genre.POP,8,50.0));
        this.albumRepository.addAlbum(new Album(2, "Let's Dance", "David Bowie", LocalDate.parse("1983-04-14")*//*(83,4,14)*//*,Genre.POST_DISCO,8,65.0));
        this.albumRepository.addAlbum(new Album(3, "Bad", "Michael Jackson", LocalDate.parse("1987-08-31")*//*( 87,8,31)*//*,Genre.POP,8,100.0));
        this.albumRepository.addAlbum(new Album(4, "21t Century Breakdown", "Green Day", LocalDate.parse("2009-05-15")*//*(109,5,15)*//*,Genre.PUNK,23,75.0));
        this.albumRepository.addAlbum(new Album(5, "A Head Full of Dreams", "Coldplay",LocalDate.parse("2015-12-04")*//*(115,12,4)*//*,Genre.INDIE,8, 59.99));
*/

        this.albumRepository.addAlbum(new Album( "Backstreet's Back", new Artist("Backstreet Boys"), LocalDate.parse("1997-08-12")/*(97,8,12)*/, Genre.POP,8,50.0));
        this.albumRepository.addAlbum(new Album( "Let's Dance", new Artist("David Bowie"), LocalDate.parse("1983-04-14")/*(83,4,14)*/,Genre.POST_DISCO,8,65.0));
        this.albumRepository.addAlbum(new Album( "Bad", new Artist("Michael Jackson"), LocalDate.parse("1987-08-31")/*( 87,8,31)*/,Genre.POP,8,100.0));
        this.albumRepository.addAlbum(new Album( "21t Century Breakdown", new Artist("Green Day"), LocalDate.parse("2009-05-15")/*(109,5,15)*/,Genre.PUNK,23,75.0));
        this.albumRepository.addAlbum(new Album( "A Head Full of Dreams", new Artist("Coldplay"),LocalDate.parse("2015-12-04")/*(115,12,4)*/,Genre.INDIE,8, 59.99));


        // (ADD USERS)
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        ApplicationUser user1 = new ApplicationUser("admin", encoder.encode("secretpassword"), true);
        ApplicationUser user2 = new ApplicationUser("fred", encoder.encode("123456"), false);
        this.applicationUserRepository.addUser(user1);
        this.applicationUserRepository.addUser(user2);

    }
}
