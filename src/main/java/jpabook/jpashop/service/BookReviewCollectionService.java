package jpabook.jpashop.service;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.repository.BookRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.BookReviewCollectionRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookReviewCollectionService {

    private final BookReviewCollectionRepository bookReviewCollectionRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Book item = bookRepository.findOne(itemId);

        //주문상품 생성
        BookReview bookReview = BookReview.createBookReview(item);

        //주문 생성
        jpabook.jpashop.domain.BookReviewCollection bookReviewCollection = jpabook.jpashop.domain.BookReviewCollection.createOrder(member,  bookReview);

        //주문 저장
        bookReviewCollectionRepository.save(bookReviewCollection);

        return bookReviewCollection.getId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        //주문 엔티티 조회
        jpabook.jpashop.domain.BookReviewCollection bookReviewCollection = bookReviewCollectionRepository.findOne(orderId);
        //주문 취소
    }

    //검색
    public List<jpabook.jpashop.domain.BookReviewCollection> findOrders(OrderSearch orderSearch) {
        return null;
        // return bookReviewCollectionRepository.findAllByString(orderSearch);
    }
}
