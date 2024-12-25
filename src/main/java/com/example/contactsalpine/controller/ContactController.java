package com.example.contactsalpine.controller;

import com.example.contactsalpine.entity.Contact;
import com.example.contactsalpine.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ContactController {

    @Autowired
    private ContactService contactService;

    // 1. 获取全部联系人
    @GetMapping("/contacts")
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }

    // 2. 根据ID获取联系人
    @GetMapping("/contacts/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
        Contact contact = contactService.getById(id);
        return contact != null ? ResponseEntity.ok(contact) : ResponseEntity.notFound().build();
    }

    // 3. 新增联系人
    @PostMapping("/contacts")
    public Contact createContact(@RequestBody Contact contact) {
        // 这里也可以做一些校验逻辑
        return contactService.save(contact);
    }

    // 4. 更新联系人
    @PutMapping("/contacts/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Long id, @RequestBody Contact updated) {
        Contact contact = contactService.getById(id);
        if (contact == null) {
            return ResponseEntity.notFound().build();
        }
        // 更新字段
        contact.setName(updated.getName());
        contact.setPhone(updated.getPhone());
        contact.setEmail(updated.getEmail());
        contact.setAvatar(updated.getAvatar());

        Contact saved = contactService.save(contact);
        return ResponseEntity.ok(saved);
    }

    // 5. 删除联系人
    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        Contact contact = contactService.getById(id);
        if (contact == null) {
            return ResponseEntity.notFound().build();
        }
        contactService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // 6. 按姓名或手机号模糊查询
    @GetMapping("/contacts/search")
    public List<Contact> searchContact(@RequestParam String keyword) {
        return contactService.search(keyword);
    }
}