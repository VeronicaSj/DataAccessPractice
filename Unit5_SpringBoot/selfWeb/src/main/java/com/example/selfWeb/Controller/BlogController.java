package com.example.selfWeb.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.selfWeb.Model.Post;
import com.example.selfWeb.Service.PostService;

@Controller
public class BlogController {
    @Autowired
    private PostService postService;

    @GetMapping("/blog")
    public String viewBlogPage(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "blog";
    }

    @GetMapping("/post/{id}")
    public String viewPost(@PathVariable Integer id, Model model) {
        model.addAttribute("post", postService.getPostById(id));
        return "post";
    }

    @GetMapping("/postForm")
    public String viewPost() {
        return "postForm";
    }

    @PostMapping("/newPost")
    public String savePost(@RequestParam String title, @RequestParam String content) {
        Post post = new Post(title, content);
        postService.createPost(post);
        return "redirect:/blog";
    }
}