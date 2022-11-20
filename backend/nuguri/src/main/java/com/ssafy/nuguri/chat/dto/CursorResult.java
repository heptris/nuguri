package com.ssafy.nuguri.chat.dto;


import lombok.AllArgsConstructor;

import lombok.Data;

import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class CursorResult<T> {


    private T values;
    private Boolean hasNext;
    private Long cursorId;

}
