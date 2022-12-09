package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "BookReviewCollection")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookReviewCollection {

    @Id @GeneratedValue
    @Column(name = "book_review_collection_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @JsonIgnore
    @OneToMany(mappedBy = "bookReviewCollection", cascade = CascadeType.ALL)
    private List<BookReview> bookReviews = new ArrayList<>();

    private LocalDateTime orderDate; //주문시간

    @Enumerated(EnumType.STRING)

    //==연관관계 메서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getBookReviewCollections().add(this);
    }

    public void addBookReview(BookReview bookReview) {
        bookReviews.add(bookReview);
        bookReview.setBookReviewCollection(this);
    }


    //==생성 메서드==//
    public static BookReviewCollection createOrder(Member member, BookReview... BookReviews) {
        BookReviewCollection bookReviewCollection = new BookReviewCollection();
        bookReviewCollection.setMember(member);
        for (BookReview bookReview : BookReviews) {
            bookReviewCollection.addBookReview(bookReview);
        }
        bookReviewCollection.setOrderDate(LocalDateTime.now());
        return bookReviewCollection;
    }

    //==비즈니스 로직==//

    //==조회 로직==//


}
