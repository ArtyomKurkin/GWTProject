package com.netcracker.server;

import com.netcracker.client.GreetingService;
import com.netcracker.client.Library;
import com.netcracker.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.ArrayList;
import java.util.Date;


public class GreetingServiceImpl extends RemoteServiceServlet implements
    GreetingService {
    private Library library;
    //Date date1 = new Date(1212121212121L);
    int num=1;
    public ArrayList<Library.Book> addNewBook(String name, String title, int pages, int year, Date date){
        Library.Book myBook = new Library.Book(num,name,title,pages,year,date);
        library.addBook(myBook);
        num++;
        return new ArrayList<>(library.getDataProvider());
    }
    public ArrayList<Library.Book> addEditBook(int id,String name, String title, int pages, int year, Date date){
        Library.Book myBook = new Library.Book(id,name,title,pages,year,date);
        library.addBook(myBook);
        return new ArrayList<>(library.getDataProvider());
    }

    public ArrayList<Library.Book> deleteBook(int id){
        for(Library.Book item:library.getDataProvider())
            if(item.getId()==id){
                library.removeBook(item);
                break;
            }

        return new ArrayList<>(library.getDataProvider());
    }

    public ArrayList<Library.Book> sortLibrary(String mode){
        if ("Id".equals(mode)){
            library.sortById();
        }
        if ("Author".equals(mode)){
            library.sortByAuthor();
        }
        if ("Title".equals(mode)){
            library.sortByTitle();
        }
        if ("Pages".equals(mode)){
            library.sortByPages();
        }
        if ("Year".equals(mode)){
            library.sortByYear();
        }
        if ("Date".equals(mode)){
            library.sortByDate();
        }
        return new ArrayList<>(library.getDataProvider());
    }

    public ArrayList<Library.Book> getLibrary(String msg) {
        library = Library.get();
        library.createResponse();
        return new ArrayList<>(library.getDataProvider());
    }


}
