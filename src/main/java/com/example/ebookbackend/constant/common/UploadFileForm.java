package com.example.ebookbackend.constant.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadFileForm {
    private String book_id;
    private MultipartFile file;
}
