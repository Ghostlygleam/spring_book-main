package com.example.weektranslator;

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
