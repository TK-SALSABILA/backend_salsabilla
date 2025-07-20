package org.school.backend.adapters.dto;
import lombok.*;

@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private Meta meta;
    private T data;
}
