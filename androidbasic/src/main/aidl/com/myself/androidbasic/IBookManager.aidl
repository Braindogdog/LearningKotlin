// IBookManager.aidl
package com.myself.androidbasic;
import com.myself.androidbasic.Book;

// Declare any non-default types here with import statements

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
}
