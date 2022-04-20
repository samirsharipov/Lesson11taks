package uz.pdp.lesson11taks.controller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.lesson11taks.entity.Attachment;
import uz.pdp.lesson11taks.payload.Result;
import uz.pdp.lesson11taks.repository.AttachmentContentRepo;
import uz.pdp.lesson11taks.repository.AttachmentRepo;
import uz.pdp.lesson11taks.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {
    @Autowired
    AttachmentRepo attachmentRepo;

    @Autowired
    AttachmentContentRepo attachmentContentRepo;

    @Autowired
    AttachmentService service;

    @SneakyThrows
    @PostMapping("/upload")
    public Result uploadFile(MultipartHttpServletRequest request) {
        return service.upload(request);
    }

    @GetMapping("/download/{id}")
    public void download(@PathVariable Integer id, HttpServletResponse response){
        service.download(id,response);
    }

    @GetMapping("/info")
    public List<Attachment> getInfo(){
        return attachmentRepo.findAll();
    }

    @GetMapping("/info/{id}")
    public Attachment getById(@PathVariable Integer id){
        return service.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        return service.delete(id);
    }
}

