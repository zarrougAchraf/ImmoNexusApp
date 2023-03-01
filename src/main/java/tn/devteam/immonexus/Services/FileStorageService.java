package tn.devteam.immonexus.Services;

import java.io.IOException;
import java.util.List;

import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import tn.devteam.immonexus.Entities.Advertising;
import tn.devteam.immonexus.Entities.FileDB;
import tn.devteam.immonexus.Repository.AdevertisingRepository;
import tn.devteam.immonexus.Repository.FileDBRepository;



@Service
public class FileStorageService {
	Long idf;
  @Autowired
  private FileDBRepository fileDBRepo;

  @Autowired
  AdevertisingRepository adevertisingRepository;
  public FileDB store(MultipartFile file) throws IOException {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
    return fileDBRepo.save(FileDB);
  }

  public Long store1(MultipartFile file) throws IOException {
	    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	    FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
	    fileDBRepo.save(FileDB);
	    return FileDB.getId();
	  }
  public void deletefile(Long idfile) {
	  FileDB f =fileDBRepo.findById(idfile).orElse(null);
	  fileDBRepo.delete(f);
  }
  public FileDB getFile(Long id) {
    return fileDBRepo.findById(id).orElse(null);
  }
  
  
  public Stream<FileDB> getAllFiles() {
    return fileDBRepo.findAll().stream();
  }

  public FileDB getFileByAdvertising(Long id) {
	  Advertising t =adevertisingRepository.findById(id).orElse(null);
	    return t.getFileDB();
	  }
  public void affecterFileToAdvertising(Long idFiles, Long idAticle) {
		Advertising t=adevertisingRepository.findById(idAticle).orElse(null);
		
		FileDB f=fileDBRepo.findById(idFiles).orElse(null);
		t.setFileDB(f);
	  adevertisingRepository.save(t);
	}


 
}