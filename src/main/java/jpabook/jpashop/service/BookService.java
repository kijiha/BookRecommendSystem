package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookService {

    private final BookRepository itemRepository;

    @Transactional
    public void saveItem(Book item) {
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price) {
        Book item = itemRepository.findOne(itemId);
        item.setName(name);
        item.setPrice(price);
    }

    public List<Book> findItems() {
        return itemRepository.findAll();
    }

    public Book findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

}
