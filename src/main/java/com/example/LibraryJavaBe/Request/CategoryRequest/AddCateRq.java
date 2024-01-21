package com.example.LibraryJavaBe.Request.CategoryRequest;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddCateRq {
    String name;
    Long id;
}
