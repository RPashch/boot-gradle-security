package qrok.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import qrok.entitties.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
	@Override
    @Query("select a from Author a where author_id = ?1")
    Author getOne(Long id);
	
	
	@Query("SELECT b FROM Author b WHERE book_id = ?1")
    List<Author> getAuthorsByBookId(long id);
	
}
