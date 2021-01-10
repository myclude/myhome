package me.myclude.calculator.members.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.myclude.calculator.members.dto.Enabled;
import me.myclude.calculator.members.dto.MemberDto;
import me.myclude.calculator.members.entity.Member;
import me.myclude.calculator.members.entity.MemberRole;
import me.myclude.calculator.members.entity.Role;
import me.myclude.calculator.members.service.MemberService;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    //
    private final MemberService memberService;

    @GetMapping("/list")
    public String getAll(Model model, @PageableDefault(size = 10) Pageable pageable) {

        Page<Member> pageMembers = memberService.findAll(pageable);
        model.addAttribute("members", pageMembers);
        return "board/members";
    }

    @GetMapping("/memberform")
    public String memberForm(Model model, @RequestParam(required = false) Long id) {
    	
    	MemberDto memberDto = new MemberDto();
    	
    	List<Enabled> enableds = List.of(Enabled.Y, Enabled.N);
    			
    	if(id != null) {
	    
	    	Optional<Member> optionalMember = memberService.searchById(id);
	    	Member findMember = optionalMember.orElse(null);
	    	
	    	String enableString;
	    	
	    	if(findMember.isEnabled()) {
	    		enableString = "Y";
	    	} else {
	    		enableString = "N";
	    	}
	    	
	    	List<MemberRole> roles = findMember.getRoles();
	    	
	    	boolean adminRole = false;
	    	boolean userRole = false;
	    	
	    	if(!roles.isEmpty()) {
	    		
	    		for(MemberRole role : roles) {
	    			
	    			Role role2 = role.getRole();
	    			
	    			if(role2.getName().equals("ADMIN")) {
	    				adminRole = true;
	    			}
	    			
	    			if(role2.getName().equals("USER")) {
	    				userRole = true;
	    			}
	    		}
	    	}
	    	
	    	memberDto = MemberDto.builder()
	    			.memberId(findMember.getMemberId())
	    			.employeeNumber(findMember.getEmployeeNumber())
	    			.password(findMember.getPassword())
	    			.username(findMember.getUsername())
	    			.deptCode(findMember.getDeptCode())
	    			.email(findMember.getEmail())
	    			.language(findMember.getEmail())
	    			.phoneNumber(findMember.getPhoneNumber())
	    			.enableString(enableString)
	    			.adminChecked(adminRole)
	    			.userChecked(userRole)
	    			.build();
	    	
	    	System.out.println(memberDto.toString());

    	}
    	
    	memberDto.setEnabledCombo(enableds);
    	model.addAttribute("member", memberDto);
    	
    	return "board/memberform";
    	
    }
    
    @PostMapping("/memberform")
    public String memberSave(@ModelAttribute("member") @Valid MemberDto memberDto, BindingResult bindingResult) {
    	
    	log.debug(memberDto.toString());
    	
    	if (bindingResult.hasErrors()) {
            return "board/memberform";
        }
    	
    	memberService.save(memberDto);
    	
    	
    	return "redirect:/member/list";
    }
    
}
