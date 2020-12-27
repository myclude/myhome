package me.myclude.calculator.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class BoardDto {

    private Long id;

    @NotNull
    @Size(min = 2, max = 30, message = "제목은 2자이상 30자 이하입니다.")
    private String title;

    private String content;

}
