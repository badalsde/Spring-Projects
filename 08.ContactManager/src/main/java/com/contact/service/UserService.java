package com.contact.service;

import com.contact.entity.User;
import com.contact.repository.UserRepository;
import com.contact.utility.Message;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service("userService")
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void save(User user, Model model, boolean agreement, HttpSession session) {
        try {
            if (!agreement) {
                throw new Exception("Please agree to the terms and conditions");
            }

            // Additional validation can be added here if necessary
            user.setRole("USER_ROLE");
            user.setEnabled(true);
            // Save the user to the repository
            userRepository.save(user);

            // Clear the user object to avoid form repopulation after successful submission
            model.addAttribute("user", new User());
            session.setAttribute("message", new Message("Successfully Registered !!", "alert-success"));

        } catch (Exception e) {
            // Log the error for debugging purposes
            e.printStackTrace();

            // Add the user object back to the model to repopulate the form
            model.addAttribute("user", user);

            // Set an error message in the session to be displayed on the form
            session.setAttribute("message", new Message("Something Went Wrong !! " + e.getMessage(), "alert-danger"));
        }
    }
}

