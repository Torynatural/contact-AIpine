package com.example.contactsalpine.service;

import com.example.contactsalpine.entity.Contact;
import com.example.contactsalpine.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Contact getById(Long id) {
        return contactRepository.findById(id).orElse(null);
    }

    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    public void deleteById(Long id) {
        contactRepository.deleteById(id);
    }

    public List<Contact> search(String keyword) {
        return contactRepository.findByNameContainingOrPhoneContaining(keyword, keyword);
    }
}
