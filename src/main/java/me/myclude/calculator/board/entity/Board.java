package me.myclude.calculator.board.entity;

import lombok.*;
import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder @Entity
@Table(name = "board")
@SequenceGenerator(
        name = "BOARD_SEQ_GENERATOR",
        sequenceName = "BOARD_SEQ"
)
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_SEQ_GENERATOR")
    private long id;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "content", length = 4000)
    private String content;

}
