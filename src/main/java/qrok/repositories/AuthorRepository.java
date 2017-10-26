package qrok.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import qrok.entitties.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
	@Override
    @Query("select a from Author a where author_id = ?1")
    Author getOne(Long id);
	
	//@Query("select u.userName from User u inner join u.area ar where ar.idArea = :idArea")
	//List<User> findByIdarea(@Param("idArea") Long idArea);
	//@Query("SELECT b FROM Author b WHERE book_id = ?1")
    //List<Author> getAuthorsByBookId(long id);
	
}
