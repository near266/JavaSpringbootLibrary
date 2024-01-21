package com.example.LibraryJavaBe.Controller;

import com.example.LibraryJavaBe.BookService.Entities.Category;
import com.example.LibraryJavaBe.BookService.Service.CateService;
import com.example.LibraryJavaBe.Request.CategoryRequest.AddCateRq;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Builder
@Slf4j
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CateService service;
    @PostMapping("addCategory")
    public ResponseEntity<Category> AddCate(@RequestBody AddCateRq rq){
        var cate = Category.builder()
                .name(rq.getName())
                .build();
        var res = service.AddCate(cate);
        return  ResponseEntity.ok(res);

    }
    @GetMapping("/getcateName")
    public ResponseEntity<List<AddCateRq>> getCate(){
        var cate = service.GetAllCate().orElse(null);
        var res = new ArrayList<AddCateRq>();
        for (Category c: cate) {
            AddCateRq a = new AddCateRq();
            a.setName(c.getName());
            a.setId(c.getId());
            res.add(a);
        }
        return ResponseEntity.ok(res);
    }
}
