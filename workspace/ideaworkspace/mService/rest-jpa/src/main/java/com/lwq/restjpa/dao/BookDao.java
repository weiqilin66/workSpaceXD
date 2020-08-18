package com.lwq.restjpa.dao;

import com.lwq.restjpa.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Description: JpaRepository<表名, ID类型>
 * @query() 自定义查询 nativeQuery=true 原生sql
 * @author: LinWeiQi
 */
public interface BookDao extends JpaRepository<Book, Integer> {
    //rest风格暴露接口
    @RestResource()
    List<Book> findBooksByAuthorAndBookName(String author, String bookName);

    @Query(value = "select * from book", nativeQuery = true)
    List<Book> getMaxBookID();

    //自定义新增修改删除
    @Query(value = "insert into book(author,book_name) values(?1,?2)", nativeQuery = true)
    @Modifying  //是修改操作
    @Transactional
    //修改删除必须有事务
    void updateCustomize(String author, String bookName);
}
