package com.exam.controller;


import com.exam.model.UserPayments;
import com.exam.model.exam.ImageModel;
import com.exam.model.exam.Question;
import com.exam.service.PaymentsService;
import com.exam.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/payments")
public class PaymentsController {

    @Autowired
    private PaymentsService paymentsService;

    @PostMapping(value = {"/"},  consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<UserPayments> add(@RequestPart UserPayments userPayments, @RequestPart("imageFile") MultipartFile[] file) {
        System.out.println("sdjfk");
        System.out.println(file.length);
        System.out.println(userPayments);

        try {
            Set<ImageModel> images = uploadImage(file);
            userPayments.setSlipImages(images);
            return ResponseEntity.ok(this.paymentsService.addPaymentsSlip(userPayments));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        Set<ImageModel> imageModels = new HashSet<>();

        for (MultipartFile file : multipartFiles) {
            ImageModel imageModel = new ImageModel(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            imageModels.add(imageModel);
        }
        return imageModels;
    }

}
