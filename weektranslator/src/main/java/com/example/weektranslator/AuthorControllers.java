package com.example.weektranslator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Controller
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/authors")
    String getAuthorsView(Model model) {
        model.addAttribute("authors", authorService.getAllAuthors());
        return "authors";
    }
}
