package com.netcracker.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.ArrayList;
import java.util.Date;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
    ArrayList<Library.Book> getLibrary(String msg);
    ArrayList<Library.Book> sortLibrary(String mode);
    ArrayList<Library.Book> addNewBook(String name, String title, int pages, int year, Date date);
    ArrayList<Library.Book> addEditBook(int id,String name, String title, int pages, int year, Date date);
    ArrayList<Library.Book> deleteBook(int id);

    public static class App {
        private static GreetingServiceAsync ourInstance = GWT.create(GreetingService.class);
        public static synchronized GreetingServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
