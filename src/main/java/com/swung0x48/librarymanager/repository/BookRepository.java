package com.swung0x48.librarymanager.repository;

import com.swung0x48.librarymanager.domain.Book;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BookRepository {
    @Select("Select * from books where bookID=#{id}")
    Book selectBookByID(Integer id);

    @Select("Select * from books")
    List<Book> selectAllBook();

    @Update("Update books set lendCount=#{lendCount},nowCount=#{nowCount} where bookID=#{bookID}")
    void updateBook(Book book);

    @Insert("insert into books(bookName,author,publishing,price,totalCount,lendCount,nowCount) " +
            "values(#{bookName},#{author},#{publishing},#{price},#{totalCount},#{lendCount},#{nowCount})")
    void insertBook(Book book);

    @Delete("delete from books where bookID=#{bookID}")
    Integer deleteBook(Integer bookID);

    @Update("Update books set nowCount = -1000 where bookID=#{bookID}")
    void banBook(Integer bookID);

    @Update("Update books set nowCount=#{nowCount}  where bookID=#{bookID}")
    void unbanBook(Book book);
}
