package uz.pdp.lesson11taks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson11taks.entity.AttachmentContent;

import javax.swing.*;

public interface AttachmentContentRepo extends JpaRepository<AttachmentContent,Integer> {
}
