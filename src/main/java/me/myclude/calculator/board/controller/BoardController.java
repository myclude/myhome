package me.myclude.calculator.board.controller;

import lombok.RequiredArgsConstructor;
import me.myclude.calculator.board.dto.BoardDto;
import me.myclude.calculator.board.entity.Board;
import me.myclude.calculator.board.service.BoardService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final ModelMapper modelMapper;

    @GetMapping("/list")
    public String list(Model model) {

        List<Board> boardList = boardService.findAll();

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

        Board board = modelMapper.map(boardDto, Board.class);
        boardService.save(board);

        return "redirect:/board/list";
    }


}
