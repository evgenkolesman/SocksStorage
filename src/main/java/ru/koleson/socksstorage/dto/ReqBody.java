package ru.koleson.socksstorage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReqBody {
    private String color;

    private String operation;

    private String cottonPart;
}
