package study.huhao.demo.adapters.api.controllers.blog;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import study.huhao.demo.application.services.BlogService;
import study.huhao.demo.domain.models.user.UserId;

@RestController
@RequestMapping(value = "/blogs", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BlogController {

    private final BlogService blogService;
    private final MapperFacade mapperFacade;

    @Autowired
    public BlogController(BlogService blogService, MapperFacade mapperFacade) {
        this.blogService = blogService;
        this.mapperFacade = mapperFacade;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public BlogRE createBlog(@RequestBody BlogCreateRequest data) {
        return mapperFacade.map(
                blogService.createBlog(data.title, data.body, UserId.valueOf(data.authorId)),
                BlogRE.class
        );
    }
}
