package com.nhnacademy.board.board.controller;

import com.nhnacademy.board.entity.Post;
import com.nhnacademy.board.board.domain.PostRequest;
import com.nhnacademy.board.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@SessionAttributes("user")
@RequestMapping("/board")
@AllArgsConstructor
@Slf4j
public class BoardController {
    private final BoardService boardService;

    @GetMapping(value = {"/list", "/", ""})
    public String posts(Model model,
                        @RequestParam(name = "page", defaultValue = "1") int page,
                        @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<Post> postPage = boardService.getPostList(page, size);
        model.addAttribute("postPage", postPage);

        for (Post post : postPage) {
            log.info(post.toString());
        }

        return "post/list";
    }

    @GetMapping("/view")
    public String posts(Model model,
                        @RequestParam(name = "page", defaultValue = "1") int page,
                        @RequestParam(name = "size", defaultValue = "10") int size,
                        String id) {
        Post post = boardService.getPost(Long.valueOf(id));
        PostRequest postRequest = new PostRequest(post.getPk().getId(), post.getTitle(), post.getContent()
                , post.getPk().getWriterUserId(), post.getWriteTime(), post.getViewCount());
        model.addAttribute("postRequest", postRequest);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        return "post/view";
    }

    @GetMapping("/modify")
    public String modify(Model model,
                         @RequestParam(name = "page", defaultValue = "1") int page,
                         @RequestParam(name = "size", defaultValue = "10")int size ,
                         String id) {
        Post post = boardService.getPost(Long.valueOf(id));
        PostRequest postRequest = new PostRequest(post.getPk().getId(), post.getTitle(), post.getContent()
                , post.getPk().getWriterUserId(), post.getWriteTime(), post.getViewCount());
        model.addAttribute("postRequest", postRequest);
        model.addAttribute("action", "/board/modify");
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        return "post/modifyForm";
    }

    @PostMapping("/modify")
    public String modifyAction(@Valid @ModelAttribute PostRequest postRequest,
                               BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "post/modifyForm";
        }
        boardService.update(postRequest);
        return "redirect:/board/view?id=" + postRequest.getId();
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("postRequest", new PostRequest());
        model.addAttribute("action","/board/register");
        return "post/registerForm";
    }

    @PostMapping(value = "/register")
    public String registerAction(@Valid @ModelAttribute PostRequest postRequest,
                                 BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return "post/registerForm";
        }
        boardService.register(postRequest);
        return "redirect:/board/";
    }

    @DeleteMapping(value="/")
    public String deleteStudent(Long id){
        boardService.delete(id);
        return "redirect:/board/";
    }
}
