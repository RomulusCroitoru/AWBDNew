package org.proiect.awbd.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.proiect.awbd.dtos.AuthorDTO;
import org.proiect.awbd.model.Author;
import org.proiect.awbd.model.Book;
import org.proiect.awbd.service.AuthorService;
import org.proiect.awbd.service.BookService;
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

    public MainController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
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

        model.addAttribute("authors", authors);
        model.addAttribute("books", books);

        return "main";
    }
}
