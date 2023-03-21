package com.ecommerce.backend.controllers;

import java.util.List;

import com.ecommerce.backend.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
  @GetMapping("/all")
  public String allAccess() {
    return "Public Content.";
  }

  @GetMapping("/user")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public String userAccess() {
    return "User Content.";
  }

  @GetMapping("/usertest")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public String userAccessTest() {
    return "User Content Test.";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() {
    return "Admin Content.";
  }

  /*
  @Autowired
  BookRepository bookRepository;

  @PostMapping("/all/book")
  public ResponseEntity<?> insertNewBook(@Valid @RequestBody InsertBookRequest insertBookRequest) {
    /*
    if (bookRepository.existsByTitle(insertBookRequest.getTitle())) {
      System.out.println("esiste");
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: This Book is already present!"));
    }
    */
  /*
    else {
      //System.out.println("non esiste");
      Book book = new Book(insertBookRequest.getTitle(), insertBookRequest.getDescription());
      bookRepository.save(book);
    }
    return ResponseEntity.ok(new MessageResponse("Book correctly added!"));
  }

  @GetMapping("/user/books")
  @PreAuthorize("hasRole('USER')")
  public List<Book> getAllBooks(){
   return bookRepository.findAll();
  }
  */
}
