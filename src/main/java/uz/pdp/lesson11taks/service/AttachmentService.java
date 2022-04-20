package uz.pdp.lesson11taks.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.lesson11taks.entity.Attachment;
import uz.pdp.lesson11taks.entity.AttachmentContent;
import uz.pdp.lesson11taks.payload.Result;
import uz.pdp.lesson11taks.repository.AttachmentContentRepo;
import uz.pdp.lesson11taks.repository.AttachmentRepo;

import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Optional;

@Service
public class AttachmentService {
    @Autowired
    AttachmentRepo attachmentRepo;

    @Autowired
    AttachmentContentRepo attachmentContentRepo;

    @SneakyThrows
    public Result upload(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());

        if (file != null) {
            String originalFilename = file.getOriginalFilename();
            long size = file.getSize();
            String contentType = file.getContentType();

            Attachment attachment = new Attachment();
            attachment.setName(originalFilename);
            attachment.setSize(size);
            attachment.setContentType(contentType);
            Attachment saveAttachment = attachmentRepo.save(attachment);

            byte[] bytes = file.getBytes();

            AttachmentContent attachmentContent = new AttachmentContent();
            attachmentContent.setBytes(bytes);
            attachmentContent.setAttachment(saveAttachment);
            attachmentContentRepo.save(attachmentContent);
            return new Result("File saqlandi!", true);
        }
        return new Result("file saqlanmadi!", false);
    }


    @SneakyThrows
    public void download(Integer id, HttpServletResponse response) {
        Optional<Attachment> byId = attachmentRepo.findById(id);
        if (byId.isPresent()) {
            Attachment attachment = byId.get();
            Optional<AttachmentContent> byId1 = attachmentContentRepo.findById(id);
            if (byId1.isPresent()) {
                AttachmentContent attachmentContent = byId1.get();
                response.setHeader("Content-Disposition","attachment; filename=\""+attachment.getName()+"\"");
                response.setContentType(attachment.getContentType());
                FileCopyUtils.copy(attachmentContent.getBytes(),response.getOutputStream());
            }
        }
    }

    public Attachment getById(Integer id) {
        Optional<Attachment> byId = attachmentRepo.findById(id);
        return byId.orElseGet(Attachment::new);
    }

    public Result delete(Integer id) {
        attachmentContentRepo.deleteById(id);
        attachmentRepo.deleteById(id);
        return new Result("deleted attachment",true);
    }
}
