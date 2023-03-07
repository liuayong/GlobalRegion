package com.littlefox.area.service.imps;

import com.littlefox.area.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    public Long save() {
        System.out.println("book service save ...  bookDao.save() ");
        return (long) (Math.random() * 1000000);
    }


}
