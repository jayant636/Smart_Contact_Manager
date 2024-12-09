package com.scm.controllers;

import com.scm.entity.Contact;
import com.scm.entity.UserEntity;
import com.scm.forms.ContactForm;
import com.scm.helper.AppConstants;
import com.scm.helper.Helper;
import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.services.ContactService;
import com.scm.services.ImageService;
import com.scm.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(path = "/user/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;
    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @RequestMapping(path = "/add")
    public String addContact(Model model){
        ContactForm contactForm = new ContactForm();
        model.addAttribute("contactForm",contactForm);
        contactForm.setName("Jayant2");
        return "user/add_contact";
    }

    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm , BindingResult bindingResult , Authentication authentication, HttpSession session){

        if(bindingResult.hasErrors()){
            session.setAttribute("message", Message.builder().content("Please correct the following errors").type(MessageType.red).build());
            return "user/add_contact";
        }

        String username = Helper.getEmailOfLoggedInUser(authentication);
        UserEntity userEntity = userService.getUserByEmail(username);
        String fileName = UUID.randomUUID().toString();
        String fileURL =  imageService.uploadImage(contactForm.getContactImage(),fileName);

        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());
        contact.setAddress(contactForm.getAddress());
        contact.setFavourite(contactForm.isFavourite());
        contact.setDescription(contactForm.getDescription());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setUser(userEntity);
        contact.setLinkedInLink(contactForm.getLinkedInLink());
        contact.setWebsiteLinks(contactForm.getWebsiteLinks());
        contact.setPicture(fileURL);
        contact.setCloudinaryImagePublicId(fileName);
        contactService.save(contact);
       System.out.println(contactForm);

       session.setAttribute("message",Message.builder().content("Please correct the following errors").type(MessageType.green).build());
      return "redirect:/user/contacts/add";
    }

//    view contacts
    @RequestMapping
    public String viewContacts(@RequestParam(value = "page",defaultValue = "0") int page ,
                               @RequestParam(value = "size" ,defaultValue = AppConstants.PAGE_SIZE+"") int size ,
                               @RequestParam(value = "sortBy" , defaultValue = "name") String sortBy ,
                               @RequestParam(value = "direction",defaultValue = "asc") String direction ,
                               Model model , Authentication authentication){
       String username =  Helper.getEmailOfLoggedInUser(authentication);
       UserEntity userEntity =  userService.getUserByEmail(username);
       Page<Contact> pageContact = contactService.getByUser(userEntity,page,size,sortBy,direction);
       model.addAttribute("pageContact",pageContact);
        model.addAttribute("pageSize", AppConstants.PAGE_SIZE);
      return "user/contacts";
    }

    @RequestMapping("/search")
    public String searchHandler(     @RequestParam("field") String field, @RequestParam("keyword") String value ,@RequestParam (value = "size",defaultValue = AppConstants.PAGE_SIZE+"") int size, @RequestParam (value = "page",defaultValue = "0") int page, @RequestParam(value = "sortBy",defaultValue = "name") String sortBy,@RequestParam(value = "direction",defaultValue = "asc") String direction , Model model ,Authentication authentication){
        var user = userService.getUserByEmail(Helper.getEmailOfLoggedInUser(authentication));
        Page<Contact> pageContact = null;
        if(field.equalsIgnoreCase("name")){
           pageContact =  contactService.searchByName(value,page,size,sortBy,direction,user);
        }else if(field.equalsIgnoreCase("email")){
            pageContact =  contactService.searchByEmail(value,page,size,sortBy,direction,user);
        }else if(field.equalsIgnoreCase("phone")){
            pageContact =  contactService.searchByPhoneNumber(value,page,size,sortBy,direction,user);
        }
        model.addAttribute("pageContact",pageContact);
        return "user/search";
    }
}

