package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jpabook.jpashop.domain.item.Book;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookReview {

    @Id @GeneratedValue
    @Column(name = "book_review_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "book_review_collection_id")
    private BookReviewCollection bookReviewCollection;




    //==생성 메서드==//
    public static BookReview createBookReview(Book item) {

        return null;
    }


    //==비즈니스 로직==//

    //==조회 로직==//

}
