package org.sid.electromenager.dao;

import org.sid.electromenager.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long>{

}
