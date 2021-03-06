package com.springboot.contact.web;

import com.springboot.contact.dao.ContactDao;
import com.springboot.contact.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 *
 * @Author: chudelong
 * @Date: 2018/11/2 12:13
 */

@Controller
@RequestMapping("/")
public class ContactController {

    private ContactDao contactRepo;

    @Autowired
    public ContactController(ContactDao contactRepo) {
        this.contactRepo = contactRepo;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String home(Map<String, Object> model){
        List<Contact> contacts = contactRepo.findAll();
        model.put("contacts", contacts);
        return "home";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submit(Contact contact){
        contactRepo.save(contact);
        return "redirect:/";
    }
}
