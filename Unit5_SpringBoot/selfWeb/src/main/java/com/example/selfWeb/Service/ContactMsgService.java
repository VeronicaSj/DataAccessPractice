package com.example.selfWeb.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.selfWeb.Model.ContactMsg;
import com.example.selfWeb.Repository.ContactMsgRepository;

@Service
public class ContactMsgService {
    @Autowired
    private ContactMsgRepository contactRepo;

    public List<ContactMsg> getAllMsg() {
        return contactRepo.findAll();
    }

    public ContactMsg getMsgById(Integer id) {
        return contactRepo.findById(id).get();
    }

    public ContactMsg createMsg(ContactMsg contactMsg) {
        return contactRepo.save(contactMsg);
    }

    public void deleteMsg(Integer id) {
        contactRepo.deleteById(id);
    }
}