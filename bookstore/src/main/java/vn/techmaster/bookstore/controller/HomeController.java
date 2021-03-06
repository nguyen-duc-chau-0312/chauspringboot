package vn.techmaster.bookstore.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import vn.techmaster.bookstore.model.Book;
import vn.techmaster.bookstore.model.Car;

@Controller
public class HomeController {
    // Đọc giá trị từ application.properties
    @Value("${spring.application.name}")
    private String appName;
    static final String APP_NAME = "appName";
    static final String BOOKS = "books";
    static final String CARS = "cars";

    @GetMapping(value = "/")
    public String getHome(Model model) {
        model.addAttribute(APP_NAME, appName);
        return "home";
    }

    @GetMapping(value = "/about")
    public String getAbout(Model model) {
        model.addAttribute(APP_NAME, appName);
        return "about";
    }


    // Trả về books là mảng của String
    @GetMapping(value = "/book")
    public String getBook(Model model) {
        String[] bookCollection = {"Deep Work", "Nhà Giả Kim ", "Cafe cùng Tony", "Tôi đi code dạo"};
        model.addAttribute(BOOKS, bookCollection);
        model.addAttribute(APP_NAME, appName);
        return "book";
    }

    // Trả về books là mảng của đối tượng kiểu Book
    @GetMapping(value = "/book2")
    public String getBook2(Model model) {
        Book[] bookCollection = {
                new Book(1, "Cafe cùng Tony", "Tony"),
                new Book(2, "Dế Mèn Phiêu Lưu Ký", "Tô Hoài")
        };

        model.addAttribute(BOOKS, bookCollection);
        model.addAttribute(APP_NAME, appName);
        return "book2";
    }

    // Trả về books là mảng của đối tượng kiểu Book, thêm ảnh cho từng sách
    @GetMapping(value = "/book3")
    public String getBook3(Model model) {
        Book[] bookCollection = {
                new Book(1, "Cafe cùng Tony", "Tony"),
                new Book(2, "Dế Mèn Phiêu Lưu Ký", "Tô Hoài")
        };

        model.addAttribute(BOOKS, bookCollection);
        model.addAttribute(APP_NAME, appName);
        return "book3";
    }

    // Trả về books là mảng của đối tượng kiểu Book, thêm ảnh cho từng sách, CSS đẹp
    @GetMapping(value = "/book4")
    public String getBook4(Model model) {
        Book[] bookCollection = {
                new Book(1, "Cafe cùng Tony", "Tony"),
                new Book(2, "Dế Mèn Phiêu Lưu Ký", "Tô Hoài")
        };

        model.addAttribute(BOOKS, bookCollection);
        model.addAttribute(APP_NAME, appName);
        return "book4";
    }

    @GetMapping(value = "/car")
    public String getBook5(Model model) {
        Car[] carCollection = {
                new Car(1, "Vios", "Toyota", 479000000),
                new Car(2, "Accent", "Hyundai", 426000000),
                new Car(3, "Fadil", "Vinfast", 360000000),
                new Car(4, "Grand i10", "Hyundai", 344000000),
                new Car(5, "Xpander", "Mitsubishi", 547000000),
                new Car(6, "CX5", "Mazda", 899000000),
                new Car(7, "Ranger", "Ford", 612000000),
                new Car(8, "Corolla Cross", "Toyota", 720000000),
                new Car(9, "Cerato", "Kia", 529000000),
                new Car(10, "Selto", "Kia", 599000000)
        };
        model.addAttribute(CARS, carCollection);
        model.addAttribute(APP_NAME, appName);
        return "car";
    }
}