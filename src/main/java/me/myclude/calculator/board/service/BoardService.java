package me.myclude.calculator.board.service;

import lombok.RequiredArgsConstructor;
import me.myclude.calculator.board.entity.Board;
import me.myclude.calculator.board.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository repository;

    public Optional<Board> findById(Long id) {
        return repository.findById(id);
    }

    public List<Board> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Board save(Board board) {
        return repository.save(board);
    }
}
