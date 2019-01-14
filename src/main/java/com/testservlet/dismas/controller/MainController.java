package com.testservlet.dismas.controller;

import com.testservlet.dismas.domain.Message;
import com.testservlet.dismas.domain.User;
import com.testservlet.dismas.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static com.testservlet.dismas.controller.ControllerUtils.getErrors;

/**
 * Created by Dmitry on 13.11.2018.
 */
@Controller
public class MainController {
    @Autowired
    private MessageRepo repo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    //public ModelAndView startPage(){return new ModelAndView("index");}
    public String startPage(@RequestParam(name="name",required = false, defaultValue = "World") String name, Map<String, Object> model) {
        model.put("name", name);
        return "index";
    }

    @GetMapping ("/main")
    public  String main (@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Message> messages;

        if (filter!=null && !filter.isEmpty()){
            messages = repo.findByTag(filter);
        } else {
            messages = repo.findAll();
        }

        model.addAttribute("messages",messages);
        model.addAttribute("filter",filter);
        return "main";
    }

    @PostMapping ("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid Message message,
            BindingResult bindingResult,
            //@RequestParam String text,
            //@RequestParam String tag,
            //Map<String,Object> model,
            Model model,
            @RequestParam("file")MultipartFile file
    ) throws IOException {
        message.setAuthor(user);
        //Message message = new Message(text ,tag, user);
        if(bindingResult.hasErrors()) {
            Map<String, String> errorsMap = getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("message",message);
        }else {
            saveFile(message, file);

            model.addAttribute("message",null);
            repo.save(message);
        }

        Iterable<Message> messages = repo.findAll();
        model.addAttribute("messages",messages);
        return "main";
    }

    private void saveFile(@Valid Message message, @RequestParam("file") MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            message.setFilename(resultFilename);
        }
    }


    @GetMapping("/user-messages/{user}")
    public String userMessages(
        @AuthenticationPrincipal User currentUser,
        @PathVariable User user,
        Model model,
        @RequestParam(required = false) Message message
    ){
        Set<Message> messages = user.getMessages();

        model.addAttribute("userChannel",user);
        model.addAttribute("subscriptionsCount",user.getSubscriptions().size());
        model.addAttribute("subscribersCount",user.getSubscribers().size());
        model.addAttribute("isSubscriber", user.getSubscribers().contains(currentUser));
        model.addAttribute("messages", messages);
        model.addAttribute("message", message);
        model.addAttribute("isCurrentUser",currentUser.equals(user));
        return "userMessages";
    }

    @PostMapping("/user-messages/{user}")
    public String updateMessage(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long user,
            @RequestParam(value = "id") Message message,
            @RequestParam("text") String text,
            @RequestParam("tag") String tag,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
            if(message.getAuthor().equals(currentUser)){
                if(!StringUtils.isEmpty(text)){
                    message.setText(text);
                }
                if (!StringUtils.isEmpty(tag)){
                    message.setTag(tag);
                }
                saveFile(message,file);
                repo.save(message);
            }
            return "redirect:/user-messages/" + user;
    }
}
