package com.example.populationcensus.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationResponse {
    private boolean valid;
    private List<String> errors;
}