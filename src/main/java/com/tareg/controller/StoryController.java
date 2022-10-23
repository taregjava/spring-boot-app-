package com.tareg.controller;

import java.util.List;

import com.tareg.entity.Story;
import com.tareg.repo.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/story")
public class StoryController {
    @Autowired
    StoryRepository storyRepository;
    @ResponseBody
    @RequestMapping(value = "/stories")
    public List<Story> getBookDetails() {
        List<Story> storyresponse = (List<Story>) storyRepository.findAll();
        return storyresponse;
    }
}