package com.example.springgraphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.collect.ImmutableMap;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {

  private static Map<String, Author> authors = ImmutableMap.of(
      "author-1", new Author("author-1", "FirstName1", "LastName1"),
      "author-2", new Author("author-2", "FirstName2", "LastName2"),
      "author-3", new Author("author-3", "FirstName3", "LastName3")
  );

  private static List<Book> books = Arrays.asList(
      new Book("book-1", "Harry Potter and the Philosopher's Stone", 223, ZonedDateTime.now().plusHours(1), authors.get("author-1")),
      new Book("book-2", "Moby Dick", 635, ZonedDateTime.now().plusHours(2), authors.get("author-2")),
      new Book("book-3", "Interview with the vampire", 371, ZonedDateTime.now().plusHours(3), authors.get("author-3")));

  public Book getBookById(String id) {
    return books
        .stream()
        .filter(book -> book.getId().equals(id))
        .findFirst()
        .orElse(null);
  }

  public Book getBookByFilter(Filter filter) {
    return books
        .stream()
        .filter(book -> book.getId().equals(filter.getId()) || book.getAuthor().getId().equals(filter.getAuthorId()))
        .findFirst()
        .orElse(null);
  }
}
