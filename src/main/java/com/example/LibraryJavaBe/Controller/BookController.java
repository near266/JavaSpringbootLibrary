package com.example.LibraryJavaBe.Controller;

import com.example.LibraryJavaBe.BookService.Entities.Book;
import com.example.LibraryJavaBe.BookService.Entities.Category;
import com.example.LibraryJavaBe.BookService.InterfaceSvc.IBookSvc;
import com.example.LibraryJavaBe.BookService.Service.BookService;
import com.example.LibraryJavaBe.BookService.Service.CateService;
import com.example.LibraryJavaBe.BookService.Service.UploadService;
import com.example.LibraryJavaBe.Response.UploadFileResponse;
import com.example.LibraryJavaBe.Response.bookResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;
@RestController
@RequiredArgsConstructor
@Builder
@Slf4j
@RequestMapping("/api/v1/book")
public class BookController {

    private final BookService bookService;
    private final UploadService uploadFileService;

    private  final CateService cateService;

    @GetMapping("test")
    public ResponseEntity<String> sayHello(){
        return  ResponseEntity.ok("Hello");
    }
    @GetMapping("/getBookByCategoryName/{Name}")
    public ResponseEntity<List<bookResponse>> getBookByCategoryName(@RequestParam String Name){
        var res = new ArrayList<bookResponse>();
        var rq = bookService.getAllBook();
        for (Book b : rq){

            var book = GetBookDetailMetod(b.getId());
            res.add(book);
        }
         var rlt= new ArrayList<bookResponse>();
           for (bookResponse bookres : res){
               if(bookres.getCategoryName().contains(Name)){
                   rlt.add(bookres);

               }
           }

//        var books = cateService.getBooksByCategoryName(Name);
//        var res = new ArrayList<bookResponse>();
////

        return  ResponseEntity.ok(rlt);
    }

    @PostMapping("/addbook")
    public ResponseEntity<bookResponse> AddBook(
            @RequestParam("title") String title,
            @RequestParam("name")  String name,
            @RequestParam("img")  MultipartFile img,
            @RequestParam("author")
            String author,
            @RequestParam("isbn")
            String isbn,
            @RequestParam("publisher")
            String publisher,
            @RequestParam("quantityTotal")
            Integer qquantityTotal,
            @RequestParam("quantityAvailabel")
            Integer quantityAvailabel,
            @RequestParam("Cateid")
            List<Long> Cateid
    ) throws SQLException, IOException {
       var link= UploadImg(img);

        Set<Category> categories = new HashSet<>();

        Cateid.forEach((e) -> {
            Category cate = cateService.GetByid(e);
            categories.add(cate);
      });
        Book book = Book.builder()
                .title(title)
                .name(name)
                .author(author)
                .img(link.getFileLink())
                .isbn(isbn)
                .quantityTotal(qquantityTotal)
                .quantityAvailabel(quantityAvailabel)
                .publisher(publisher)
                .build();
        book.setCategories(categories);
        Book saveBook= bookService.AddBook(book);
        var result = GetBookDetailMetod(saveBook.getId());
        return ResponseEntity.ok(result);
    }



    @PostMapping("/files/uploadfile")
    public UploadFileResponse uploadFile(MultipartFile file) {
        try {

            var uploadFile = uploadFileService.saveUploadFile(file);
            return UploadFileResponse.builder().isError(false).fileName(uploadFile.getFileName())
                    .fileLink(creteUploadFileLink(uploadFile.getFileName())).build();

        } catch (Exception e) {
            log.error("Upload File Failed.", e);
            return UploadFileResponse.builder().isError(true).fileName(file.getOriginalFilename()).build();
        }
    }
    private UploadFileResponse UploadImg(MultipartFile file){
        try {

            var uploadFile = uploadFileService.saveUploadFile(file);
            return UploadFileResponse.builder().isError(false).fileName(uploadFile.getFileName())
                    .fileLink(creteUploadFileLink(uploadFile.getFileName())).build();

        } catch (Exception e) {
            log.error("Upload File Failed.", e);
            return UploadFileResponse.builder().isError(true).fileName(file.getOriginalFilename()).build();
        }
    }

    private String creteUploadFileLink(String fileName) {
        return ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/api/v1/book/files/" + fileName).toUriString();
    }

    @GetMapping("/files/{fileName}")
    public ResponseEntity<Resource> getFile(@PathVariable String fileName) {

        var fileUpload = uploadFileService.getFileUpload(fileName);
        var grpData = new ByteArrayResource(fileUpload.getGrpData());

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, fileUpload.getFileType())
                /*
                 * Alternative:
                 * .cntentType(MediaType
                 * .valueOf(fileUpload.
                 * getFileType()))
                 */
                .cacheControl(CacheControl.maxAge(Duration.ofSeconds(60)).cachePrivate().mustRevalidate())
                .body(grpData);

    }

    @PostMapping("/files/multi_uploadfile")
    public List<UploadFileResponse> uploadMultiFiles(@RequestPart List<MultipartFile> files) {
        return files.stream().map(this::uploadFile).collect(Collectors.toList());
    }
    @GetMapping ("/getdetail")
        public ResponseEntity<bookResponse> GetDetailBook(Long id){

        var res = GetBookDetailMetod(id);
        return  new ResponseEntity<>(res, HttpStatus.OK);

        }
        private bookResponse GetBookDetailMetod(Long id){
            var book = bookService.getBookById(id);
            var cate = getCategoriesByBookId(id);


            bookResponse res = bookResponse
                    .builder()
                    .Id(book.getId())
                    .title(book.getTitle())
                    .name(book.getName())
                    .img(book.getImg())
                    .author(book.getAuthor())
                    .updatedAt(book.getUpdatedAt())
                    .createdAt((book.getCreatedAt()))
                    .quantityTotal(book.getQuantityTotal())
                    .quantityAvailabel(book.getQuantityAvailabel())
                    .publisher(book.getPublisher())
                    .isbn(book.getIsbn())

                    .categoryName(cate)


                    .build();
            return  res;
        }
//    @GetMapping("/{bookId}/categories")
    private List<String> getCategoriesByBookId( Long bookId) {

        Book b = bookService.getBookById(bookId);
        var categories = b.getCategories();
        var lisst = new ArrayList<String>();
        for (Category a : categories ){
            lisst.add(a.getName());

        }
        return lisst;
    }
@GetMapping("/getall")
    public ResponseEntity<List<bookResponse>> getAll() throws SQLException
{
    var res = new ArrayList<bookResponse>();
     var rq = bookService.getAllBook();
     for (Book b : rq){
         var book = GetBookDetailMetod(b.getId());
         res.add(book);
     }

             return ResponseEntity.ok(res);
    }
@PostMapping("/update")
public ResponseEntity<bookResponse> Update(
        @RequestParam("id") Long id,
        @RequestParam("title") String title,
        @RequestParam("name")  String name,
        @RequestParam("img")  MultipartFile img,
        @RequestParam("author")
        String author,
        @RequestParam("isbn")
        String isbn,
        @RequestParam("publisher")
        String publisher,
        @RequestParam("quantityTotal")
        Integer qquantityTotal,
        @RequestParam("quantityAvailabel")
        Integer quantityAvailabel,
         @RequestParam("Cateid")
        List<Long> Cateid
) throws SQLException, IOException {
    var link= UploadImg(img);
    Set<Category> categories = new HashSet<>();

    Cateid.forEach((e) -> {
        Category cate = cateService.GetByid(e);
        categories.add(cate);
    });
    Book rq = Book.builder()

            .title(title)
            .name(name)
            .author(author)
            .img(link.getFileLink())
            .isbn(isbn)
            .quantityTotal(qquantityTotal)
            .quantityAvailabel(quantityAvailabel)
            .publisher(publisher)
            .build();
   rq.setCategories(categories);
    Book up = bookService.UpdateBook(id,rq );
    var res = GetBookDetailMetod(up.getId());
    return ResponseEntity.ok(res);
}
@PostMapping("deleteBook")
    public  ResponseEntity<Boolean> DeleteBook(@RequestParam Long id){
        bookService.deleteBook(id);
        return ResponseEntity.ok(true);

    }

}
