package com.example.contactsalpine.repository;


import com.example.contactsalpine.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    // 按姓名或手机模糊查询
    List<Contact> findByNameContainingOrPhoneContaining(String name, String phone);
}
