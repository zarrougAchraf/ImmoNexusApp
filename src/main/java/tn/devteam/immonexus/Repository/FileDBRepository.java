package tn.devteam.immonexus.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.devteam.immonexus.Entities.FileDB;




public interface FileDBRepository extends JpaRepository<FileDB, Long> {
	

}
