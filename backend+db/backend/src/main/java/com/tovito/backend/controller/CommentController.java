package com.tovito.backend.controller;

import com.tovito.backend.exception.InvalidRating;
import com.tovito.backend.exception.ProductNotFound;
import com.tovito.backend.exception.UserNotFound;
import com.tovito.backend.model.CommentModel;
import com.tovito.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    public CommentService commentService;

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity createComment(@RequestBody CommentModel comment) {
        try {
            commentService.createComment(comment);
            return ResponseEntity.ok("Комментарий успешно создан!");
        }
        catch (InvalidRating e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (UserNotFound e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (ProductNotFound e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAllComments() {
        try {
            return ResponseEntity.ok(commentService.getAllComments());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
