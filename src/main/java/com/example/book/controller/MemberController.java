package com.example.book.controller;

import com.example.book.dto.MemberDTO;
import com.example.book.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/save")
    public String memberSave() {
        return "memberSave";
    }

    @PostMapping("/save")
    public String saveParam(@ModelAttribute MemberDTO memberDTO) {
        memberService.save(memberDTO);
        return "redirect:/";
    }
}
