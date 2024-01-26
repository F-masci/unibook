package it.ispw.unibook.dao;

import it.ispw.unibook.entity.AccountEntity;
import it.ispw.unibook.entity.CourseEntity;
import it.ispw.unibook.entity.SellableBookEntity;

import java.util.List;

public interface SellableBookDao {

    public SellableBookEntity retrieveSellableBookByCode(int code);
    public List<SellableBookEntity> retrieveSellableBooksBySeller(AccountEntity seller);
    public List<SellableBookEntity> retrieveCourseSellableBooks(CourseEntity course);
    public void addSellableBookToCourse(CourseEntity course, SellableBookEntity sellableBook);
    public void removeSellableBookFromCourse(CourseEntity course, SellableBookEntity sellableBook);

}
