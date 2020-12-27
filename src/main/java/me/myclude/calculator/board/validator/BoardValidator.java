package me.myclude.calculator.board.validator;

import me.myclude.calculator.board.dto.BoardDto;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BoardValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return BoardDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        //ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
        BoardDto dto = (BoardDto) target;

        if(StringUtils.isEmpty(dto.getContent())) {
            errors.rejectValue("content", "key", "내용을 입력하세요.");
        }
    }
}
