package uz.pdp.lesson11taks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson11taks.entity.Attachment;

public interface AttachmentRepo extends JpaRepository<Attachment,Integer> {
}
