package com.example.selfWeb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.selfWeb.Model.Post;

@Repository
public interface BlogPostRepository extends JpaRepository<Post, Long> {}