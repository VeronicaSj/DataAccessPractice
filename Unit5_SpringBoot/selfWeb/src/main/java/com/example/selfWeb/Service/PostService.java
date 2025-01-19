package com.example.selfWeb.Service;

import java.util.Date;
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

    public Post getPostById(Long id) {
        return postRepository.findById(id).get();
    }

    public Post createPost(Post post) {
        post.setLastUpdate(new Date());
        return postRepository.save(post);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}