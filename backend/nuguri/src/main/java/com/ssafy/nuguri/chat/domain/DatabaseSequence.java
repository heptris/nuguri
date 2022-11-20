package com.ssafy.nuguri.chat.domain;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@Document(collection = "database_sequences")
public class DatabaseSequence {

    @Id
    private String id;
    private Long seq;
}
