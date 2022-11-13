package com.tovito.backend.controller;

import com.tovito.backend.exception.InvalidPhotoType;
import com.tovito.backend.exception.ProductNotFound;
import com.tovito.backend.model.PhotoModel;
import com.tovito.backend.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/photo")
public class PhotoController {
    @Autowired
    public PhotoService photoService;

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity createPhoto(@RequestBody PhotoModel photoModel) {
        try {
            photoService.createPhoto(photoModel);
            return ResponseEntity.ok("Фото успешно добавлено!");
        }
        catch (ProductNotFound e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (InvalidPhotoType e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAllPhotos() {
        try {
            return ResponseEntity.ok(photoService.getAllPhotos());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
