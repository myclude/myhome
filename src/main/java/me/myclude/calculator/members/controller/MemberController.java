package me.myclude.calculator.members.controller;

import lombok.RequiredArgsConstructor;
import me.myclude.calculator.members.entity.Member;
import me.myclude.calculator.members.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    //
    private final MemberService memberService;

    @GetMapping("/list")
    public String getAll(Model model, @PageableDefault(size = 10) Pageable pageable) {

        Page<Member> pageMembers = memberService.findAll(pageable);
        model.addAttribute("members", pageMembers);
        return "board/members";
    }
}
