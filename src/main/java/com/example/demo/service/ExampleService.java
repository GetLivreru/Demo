package com.example.demo.service;

import com.example.demo.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ExampleService {

    public void performAction(boolean condition) {
        if (!condition) {
            throw new CustomException("Некорректное состояние!", HttpStatus.BAD_REQUEST);
        }
    }
}
