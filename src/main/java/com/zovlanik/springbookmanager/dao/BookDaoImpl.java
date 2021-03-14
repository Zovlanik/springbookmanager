package com.zovlanik.springbookmanager.dao;

import com.zovlanik.springbookmanager.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class BookDaoImpl implements BookDao{

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(book);
        log.info("Book successfully saved. " + book);
    }

    @Override
    public void updateBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.update(book);
        log.info("Book successfully updated. " + book);
    }

    @Override
    public void removeBook(int id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = (Book) session.load(Book.class, id);
        if(book != null) {
            session.delete(book);
            log.info("Book successfully deleted. " + book);
        }

    }

    @Override
    public Book getBookById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = (Book) session.load(Book.class, id);
        log.info("Book successfully loaded. Book details: " + book);
        return book;
    }

    @Override
    public List<Book> listBooks() {
        Session session = sessionFactory.getCurrentSession();
        List<Book> bookList = session.createQuery("FROM Books").list();
        for(Book book : bookList){
            log.info("Book list: " + book);
        }
        return bookList;
    }
}
