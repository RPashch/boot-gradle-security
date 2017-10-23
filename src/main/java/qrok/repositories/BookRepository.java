package qrok.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import qrok.entitties.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

//	@Query("select b from Book b where b.title like %:title%")
//	List<Book> findByName(@Param("title") String title);
	
	@Query("SELECT b FROM Book b WHERE author_id = ?1")
    List<Book> getBooksByAuthorId(long id);
	
}
