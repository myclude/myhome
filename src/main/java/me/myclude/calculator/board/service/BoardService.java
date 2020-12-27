package me.myclude.calculator.board.service;

import lombok.RequiredArgsConstructor;
import me.myclude.calculator.board.entity.Board;
import me.myclude.calculator.board.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Board> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<Board> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Board save(Board board) {
        return repository.save(board);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Board> findByTitleOrContent(String title, String content) {
        return repository.findByTitleOrContent(title, content);
    }

    public Page<Board> findByKeyword(String title, String content, Pageable pageable) {
        return repository.findByTitleContainingOrContentContaining(title, content, pageable);
    }
}
