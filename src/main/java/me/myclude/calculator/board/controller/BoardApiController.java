package me.myclude.calculator.board.controller;

import lombok.RequiredArgsConstructor;
import me.myclude.calculator.board.dto.BoardDto;
import me.myclude.calculator.board.entity.Board;
import me.myclude.calculator.board.service.BoardService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardApiController {

    private final BoardService boardService;
    private final ModelMapper modelMapper;

    @GetMapping("/boards")
    List<Board> all(@RequestParam(required = false, defaultValue = "") String title,
                    @RequestParam(required = false, defaultValue = "") String content) {

        if(StringUtils.isEmpty(title) && StringUtils.isEmpty(content)) {
            Page<Board> pageBoard = boardService.findAll(PageRequest.of(0, 20));
            return pageBoard.getContent();
        } else {
            return boardService.findByTitleOrContent(title, content);
        }
    }

    @PostMapping("/boards")
    Board newBoard(@RequestBody BoardDto boardDto) {

        Board board = modelMapper.map(boardDto, Board.class);
        return boardService.save(board);
    }

    @GetMapping("/boards/{id}")
    Board one(@PathVariable Long id) {
        //
        return boardService.findById(id)
                .orElse(null);
    }

    @PutMapping("/boards/{id}")
    Board replaceBoard(@RequestBody BoardDto boardDto, @PathVariable Long id) {

        Board board = modelMapper.map(boardDto, Board.class);

        return boardService.findById(id)
                .map(obj -> {
                    obj.setTitle(board.getTitle());
                    obj.setContent(board.getContent());
                    return boardService.save(obj);
                })
                .orElseGet(() -> boardService.save(board));
    }

    @DeleteMapping("/boards/{id}")
    void deleteBoard(@PathVariable Long id) {
        boardService.deleteById(id);
    }

}
