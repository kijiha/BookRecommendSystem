package jpabook.jpashop;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

/**
 * 종 주문 2개
 * * userA
 * 	 * JPA1 BOOK
 * 	 * JPA2 BOOK
 * * userB
 * 	 * SPRING1 BOOK
 * 	 * SPRING2 BOOK
 */

@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            System.out.println("Init1" + this.getClass());
            Member member = createMember("userA");
            em.persist(member);

            Book book1 = createBook("JPA1 BOOK", 10000, 100);
            em.persist(book1);

            Book book2 = createBook("JPA2 BOOK", 20000, 100);
            em.persist(book2);

            BookReview orderItem1 = BookReview.createBookReview(book1);
            BookReview orderItem2 = BookReview.createBookReview(book2);

            BookReviewCollection bookReviewCollection = BookReviewCollection.createOrder(member, orderItem1, orderItem2);
            em.persist(bookReviewCollection);
        }

        public void dbInit2() {
            Member member = createMember("userB");
            em.persist(member);

            Book book1 = createBook("SPRING1 BOOK", 20000, 200);
            em.persist(book1);

            Book book2 = createBook("SPRING2 BOOK", 40000, 300);
            em.persist(book2);

            BookReview orderItem1 = BookReview.createBookReview(book1);
            BookReview orderItem2 = BookReview.createBookReview(book2);

            BookReviewCollection bookReviewCollection = BookReviewCollection.createOrder(member,  orderItem1, orderItem2);
            em.persist(bookReviewCollection);
        }

        private Member createMember(String name) {
            Member member = new Member();
            member.setName(name);
            return member;
        }

        private Book createBook(String name, int price, int stockQuantity) {
            Book book1 = new Book();
            book1.setName(name);

            return book1;
        }


    }
}

