package org.sample.springboot.hibernate.easynotes.repository;

import org.sample.springboot.hibernate.easynotes.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}
