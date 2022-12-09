package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepository {

    private final EntityManager em;

    public void save(Book item) {
        if (item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item);
        }
    }

    public Book findOne(Long id) {
        return em.find(Book.class, id);
    }

    public List<Book> findAll() {
        return em.createQuery("select i from Book i", Book.class)
                .getResultList();
    }
}
