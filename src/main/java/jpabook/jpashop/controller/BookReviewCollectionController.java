package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.service.BookService;
import jpabook.jpashop.service.MemberService;
import jpabook.jpashop.service.BookReviewCollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookReviewCollectionController {

    private final BookReviewCollectionService bookReviewCollectionService;
    private final MemberService memberService;
    private final BookService bookService;

    @GetMapping("/order")
    public String createForm(Model model) {

        List<Member> members = memberService.findMembers();
        List<Book> items = bookService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count) {

        bookReviewCollectionService.order(memberId, itemId, count);
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
        List<jpabook.jpashop.domain.BookReviewCollection> bookReviewCollections = bookReviewCollectionService.findOrders(orderSearch);
        model.addAttribute("orders", bookReviewCollections);

        return "order/orderList";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId) {
        bookReviewCollectionService.cancelOrder(orderId);
        return "redirect:/orders";
    }
}
