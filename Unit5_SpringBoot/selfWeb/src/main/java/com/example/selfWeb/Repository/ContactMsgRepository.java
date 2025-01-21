package com.example.selfWeb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.selfWeb.Model.ContactMsg;

@Repository
public interface ContactMsgRepository extends JpaRepository<ContactMsg, Integer> {}