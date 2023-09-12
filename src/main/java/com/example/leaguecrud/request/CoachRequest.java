package com.example.leaguecrud.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CoachRequest {
    private String name;
    private int teamId;
}
