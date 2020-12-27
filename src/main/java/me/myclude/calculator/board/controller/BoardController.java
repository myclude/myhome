package me.myclude.calculator.board.controller;

import lombok.RequiredArgsConstructor;
import me.myclude.calculator.board.dto.BoardDto;
import me.myclude.calculator.board.entity.Board;
import me.myclude.calculator.board.service.BoardService;
import me.myclude.calculator.board.validator.BoardValidator;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final ModelMapper modelMapper;
    private final BoardValidator validator;

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size = 5) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText) {

        //변수 선언
        Page<Board> boardList;

        if(StringUtils.isEmpty(searchText)) {
            boardList = boardService.findAll(pageable);
        } else {
            boardList = boardService.findByKeyword(searchText, searchText, pageable);
        }

        model.addAttribute("boards", boardList);
        return "board/list";
    }

    @GetMapping("/form")
    public String modify(Model model, @RequestParam(required = false) Long id) {

        if(id == null) {
            model.addAttribute("board", new BoardDto());
        } else {

            Optional<Board> optionalBoard = boardService.findById(id);
            Board board = optionalBoard.orElse(null);
            BoardDto boardDto = modelMapper.map(board, BoardDto.class);
            model.addAttribute("board", boardDto);
        }

        return "board/form";
    }

    @PostMapping("/form")
    public String saveSubmit(@ModelAttribute("board") @Valid BoardDto boardDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "board/form";
        }

        validator.validate(boardDto, bindingResult);

        if (bindingResult.hasErrors()) {
            return "board/form";
        }

        Board board = modelMapper.map(boardDto, Board.class);
        boardService.save(board);

        return "redirect:/board/list";
    }
}
