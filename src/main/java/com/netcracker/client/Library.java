package com.netcracker.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Library {

    public static class Book implements Comparable<Book>, Serializable{
         int id=1;
        String author;
        String title;
        int pages;
        int year;
        Date date;

        public Book(){
            id++;
            this.author=null;
            this.title= null;
            this.pages = 0;
            this.year=0;
            this.date=new Date();
        }

        public Book(String author, String title, int pages, int year, Date date) {
            id++;
            this.author = author;
            this.title = title;
            this.pages = pages;
            this.year = year;
            this.date = date;
        }

        public Book(int id, String author, String title, int pages, int year, Date date) {
            this.id = id;
            this.author = author;
            this.title = title;
            this.pages = pages;
            this.year = year;
            this.date = date;
        }
        @Override
        public int compareTo(Book book){
            return 0;
        }

        @Override
        public boolean equals(Object obj){
            if(obj instanceof Book){
                return this.id == ((Book)obj).id;
            }
            return false;
        }

        public int hashCode(){
            int result=17;
            result=37*result+id;
            result=37*result+author.hashCode();
            result=37*result+title.hashCode();
            result=37*result+pages;
            result=37*result+year;
            result=37*result+date.hashCode();
            return result;
        }

        public int getId() {
            return id;
        }

        public String getAuthor() {
            return author;
        }

        public String getTitle() {
            return title;
        }

        public int getPages() {
            return pages;
        }

        public int getYear() {
            return year;
        }

        public Date getDate() {
            return date;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public void setDate(Date date) {
            this.date = date;
        }
    }

    private static class authorComparator implements Comparator<Book>{
        public int compare(Book b1,Book b2){
            return b1.getAuthor().compareTo(b2.getAuthor());
        }
    }

    private static class titleComparator implements Comparator<Book>{
        public int compare(Book b1,Book b2){
            return b1.getTitle().compareTo(b2.getTitle());
        }
    }


    private static class dateComparator implements Comparator<Book>{
        public int compare(Book b1,Book b2) {
            return b1.getDate().compareTo(b2.getDate());
        }
    }

    private static Library single;
    public static Library get(){
        if(single == null){
            single =new Library();
        }
        return single;
    }

    private List<Book> dataProvider = new ArrayList<>();
    private List<Book> respProvider = new ArrayList<>();


    private Library(){

        //generateBooks(35);
    }



    public void addBook(Book book){
        respProvider.add(book);
    }
    public  void removeBook(Book book){
        respProvider.remove(book);
    }

    public void removeBook(int id){
        respProvider.remove(id);
    }

    public List<Book> getDataProvider() {
        return respProvider;
    }

    private void generateBooks(int count){
        for(int i = 0; i < count; i++){
            dataProvider.add(generateBook(i));
        }
    }

    private Book generateBook(int key){
        String str = "AAAAAAAAAAAA";
        int year = 1900+key;
        Book b =new Book(key+1,str.substring(key%10),"Title"+key, 800-key,year, new Date());
        return b;
    }

    public void createResponse(){
        respProvider.addAll(dataProvider);
    }

    private Comparator<Book> comparator;

    public void sortById(){
        comparator=new Comparator<Book>(){
            public int compare(Book b1,Book b2){
                return 0;
            }
        };
        respProvider.sort(comparator.thenComparingInt(Book::getId));
    }



    public void sortByAuthor(){
        comparator=new authorComparator();
        respProvider.sort(comparator);
    }
    public void sortByTitle(){
        comparator=new titleComparator();
        respProvider.sort(comparator);
    }

    public void sortByPages(){
        comparator=new Comparator<Book>(){
            public int compare(Book b1,Book b2){
                return 0;
            }
        };
        respProvider.sort(comparator.thenComparingInt(Book::getPages));
    }

    public void sortByYear(){
        comparator=new Comparator<Book>(){
            public int compare(Book b1,Book b2){
                return 0;
            }
        };
        respProvider.sort(comparator.thenComparingInt(Book::getYear));
    }

    public void sortByDate(){
        comparator=new dateComparator();
        respProvider.sort(comparator);
    }



}
