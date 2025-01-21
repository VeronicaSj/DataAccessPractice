package com.example.selfWeb.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.selfWeb.Model.Post;
import com.example.selfWeb.Repository.BlogPostRepository;

@Service
public class PostService {
    @Autowired
    private BlogPostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Integer id) {
        return postRepository.findById(id).get();
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public void deletePost(Integer id) {
        postRepository.deleteById(id);
    }
}