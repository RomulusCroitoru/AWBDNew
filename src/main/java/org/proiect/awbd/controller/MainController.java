package org.proiect.awbd.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.proiect.awbd.dtos.AuthorDTO;
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
    private final GenreService genreService;
    private final LibraryService libraryService;
    private final PublisherService publisherService;
    private final MemberService memberService;
    private final LibraryCardService libraryCardService; // Adăugăm serviciul LibraryCardService

    public MainController(AuthorService authorService, BookService bookService,
                          GenreService genreService, LibraryService libraryService,
                          PublisherService publisherService, MemberService memberService,
                          LibraryCardService libraryCardService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.genreService = genreService;
        this.libraryService = libraryService;
        this.publisherService = publisherService;
        this.memberService = memberService;
        this.libraryCardService = libraryCardService; // Injectăm LibraryCardService
    }

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
            return "redirect:/home";  // Redirecționează către /home dacă utilizatorul este autentificat
        } else {
            return "redirect:/login";  // Redirecționează către /login dacă utilizatorul nu este autentificat
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
        List<Genre> genres = genreService.getAllGenres();
        List<Library> libraries = libraryService.getAllLibraries();
        List<Publisher> publishers = publisherService.getAllPublishers();
        List<String> memberNames = memberService.getAllMemberNames();

        model.addAttribute("authors", authors);
        model.addAttribute("books", books);
        model.addAttribute("genres", genres);
        model.addAttribute("libraries", libraries);
        model.addAttribute("publishers", publishers);
        model.addAttribute("memberNames", memberNames);

        // Adăugăm cardurile de bibliotecă doar dacă utilizatorul este ADMIN
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() &&
                authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            List<LibraryCard> libraryCards = libraryCardService.getAllLibraryCards();
            model.addAttribute("libraryCards", libraryCards);
        }

        return "main";
    }
}