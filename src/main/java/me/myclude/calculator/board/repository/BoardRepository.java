package me.myclude.calculator.board.repository;

import me.myclude.calculator.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
