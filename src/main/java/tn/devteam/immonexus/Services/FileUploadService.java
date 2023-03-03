package tn.devteam.immonexus.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.devteam.immonexus.Interfaces.IFileUploadService;

import java.io.File;
import java.io.IOException;

@Service
public class FileUploadService  implements IFileUploadService {
    @Override
    public void uploadfile(MultipartFile file) throws IllegalStateException, IOException {
        file.transferTo(new File("C:\\PiDevFiles\\"+file.getOriginalFilename()));
    }
}
