package com.example.weektranslator;

@Override
public List<AuthorDto> getAllAuthors() {
    List<Author> authors = authorRepository.findAll();
    return authors.stream().map(this::convertEntityToDto).collect(Collectors.toList());
}
