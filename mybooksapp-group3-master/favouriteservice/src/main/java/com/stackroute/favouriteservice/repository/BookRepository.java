package com.stackroute.favouriteservice.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.stackroute.favouriteservice.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
	public List<Book> findByUserId(String userId);
	public Optional<Book> findByUserIdAndTitle(String userId, String title);
}
