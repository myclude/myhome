package me.myclude.calculator.members.controller;

import lombok.RequiredArgsConstructor;
import me.myclude.calculator.members.entity.Member;
import me.myclude.calculator.members.service.MemberService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberRestController {

    private final MemberService memberService;
    private final ModelMapper modelMapper;

    @GetMapping("/members")
    Page<Member> getAllMembers(@RequestParam(defaultValue = "10") int offset,
                               @RequestParam(defaultValue = "10") int limit) {

        Pageable pageable = PageRequest.of(offset, limit);  //paging 처리
        return memberService.findAll(pageable);
    }
}
