package de.randsbergerhof.carta.repository;

import de.randsbergerhof.carta.dto.DBFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, Long> {

}
