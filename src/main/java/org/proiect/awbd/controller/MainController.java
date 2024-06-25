package org.proiect.awbd.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.proiect.awbd.dtos.AuthorDTO;
import org.proiect.awbd.dtos.BookDTO;
import org.proiect.awbd.dtos.GenreDTO;
import org.proiect.awbd.dtos.MemberDTO;
import org.proiect.awbd.model.*;
import org.proiect.awbd.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MainController {

    private final AuthorService authorService;
    private final BookService bookService;
    private final LibraryService libraryService;
    private final MemberService memberService;
    private final PublisherService publisherService;
    private final GenreService genreService;


    public MainController(AuthorService authorService, BookService bookService,
                          LibraryService libraryService,
                          MemberService memberService,
                          PublisherService publisherService,
                          GenreService genreService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.libraryService = libraryService;
        this.memberService = memberService;
        this.publisherService = publisherService;
        this.genreService = genreService;

    }

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
            return "redirect:/home"; // Redirecționează către /home dacă utilizatorul este autentificat
        } else {
            return "redirect:/login"; // Redirecționează către /login dacă utilizatorul nu este autentificat
        }
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }

        return "login";
    }

    @PostMapping("/login")
    public String loginPost(HttpServletRequest request) {
        return "redirect:/home"; // După autentificare cu succes, redirecționează către /home
    }

    @GetMapping("/home")
    public String home(Model model) {
        List<AuthorDTO> authors = authorService.findAll();
        List<Book> books = bookService.getAllBooks();
        List<Library> libraries = libraryService.getAllLibraries();
        List<Member> members = memberService.getAllMembers();
        List<Publisher> publishers = publisherService.getAllPublishers();
        List<Genre> genres = genreService.getAllGenres();

        model.addAttribute("authors", authors);
        model.addAttribute("books", books);
        model.addAttribute("libraries", libraries);
        model.addAttribute("members", members);
        model.addAttribute("publishers", publishers);
        model.addAttribute("genres", genres);


        return "main";
    }

//    @GetMapping("/login")
//    public String showLogInForm(){ return "login"; }
//
//    @GetMapping("/access_denied")
//    public String accessDeniedPage(){ return "accessDenied"; }
}
