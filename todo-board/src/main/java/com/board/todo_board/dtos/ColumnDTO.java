package com.board.todo_board.dtos;

import com.board.todo_board.enums.ColumTypesEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColumnDTO {
    public ColumnDTO(String name, int column_order, ColumTypesEnum type) {
        this.name = name;
        this.column_order = column_order;
        this.type = type;
    }

    private String name;
    private int column_order;
    ColumTypesEnum type;
    private Long boardId;
}
