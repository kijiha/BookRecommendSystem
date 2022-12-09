package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "book_id")
    private Long id;

    private String name;
    private int price;


    @ManyToMany(mappedBy = "books")
    private List<Category> categories = new ArrayList<>();

    //==비즈니스 로직==//


}
