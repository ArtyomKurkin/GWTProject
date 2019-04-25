package com.netcracker.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;
import java.util.ArrayList;
import java.util.Date;


public class Options extends Composite {
    interface optionsUiBinder extends UiBinder<Widget, Options> {
    }

    private static optionsUiBinder ourUiBinder = GWT.create(optionsUiBinder.class);
    private ListDataProvider<Library.Book> dataProvider;
    //private SingleSelectionModel<Library.Book> selectionModel;
    String myAuthor="";
    String myTitle="";
    String myPagesString="";
    String myYearString="";
    String corIndexString="";

    int myPages=0;
    int myYear=0;
    Date myDate=new Date();
    int editId;
    int corIndex;
    boolean checkEdit=false;

    Validator valid = new Validator();


    public Options() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    public void setDataProvider(ListDataProvider<Library.Book> dataProvider) {
        this.dataProvider = dataProvider;
    }

   /* public void setSelectionModel(SingleSelectionModel<Library.Book> selectionModel) {
        this.selectionModel = selectionModel;
    }*/

    @UiField
    Label statusInfo;
    @UiField
    TextBox author;

    @UiHandler("author")
    void handleAuthor(ValueChangeEvent<String> event){
        myAuthor=event.getValue();
    }

    @UiField
    TextBox title;

    @UiHandler("title")
    void handletitle(ValueChangeEvent<String> event){
        myTitle=event.getValue();
    }

    @UiField
    TextBox pages;

    @UiHandler("pages")
    void handlePages(ValueChangeEvent<String> event){
        myPagesString=event.getValue();
        //myPages=Integer.parseInt(event.getValue());
    }
    @UiField
    TextBox year;

    @UiHandler("year")
    void handleYear(ValueChangeEvent<String> event){
        myYearString=event.getValue();
        //myYear=Integer.parseInt(event.getValue());
    }
    @UiField
    DatePicker date;

    @UiHandler("date")
    void handleDate(ValueChangeEvent<Date> event){
        myDate=event.getValue();
    }

    @UiField
    TextBox num_cor;

    @UiHandler("num_cor")
    void handleIndex(ValueChangeEvent<String> event){
        corIndexString=event.getValue();
        //corIndex=Integer.parseInt(event.getValue());
    }

    @UiField
    Button delete;
    @UiHandler("delete")
    void doClickDelete(ClickEvent event) {
        AsyncCallback<ArrayList<Library.Book>> asyncCallback = new AsyncCallback<ArrayList<Library.Book>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println(throwable.toString());
            }
            @Override
            public void onSuccess(ArrayList<Library.Book> books) {
                dataProvider.setList(books);
            }
        };
        if (corIndexString.equals(""))
            statusInfo.setText("Enter correct id!");
        else {
            if(!valid.isValidId(corIndexString))
                statusInfo.setText("Enter correct id!");
            else {
                corIndex = Integer.parseInt(corIndexString);
                GreetingService.App.getInstance().deleteBook(corIndex, asyncCallback);
                num_cor.setValue("");
                statusInfo.setText("The book with id=" + corIndex + " was successfully deleted");
            }
        }
    }

    @UiField
    Button add;
    @UiHandler("add")
    void doClickAdd(ClickEvent event) {
        AsyncCallback<ArrayList<Library.Book>> asyncCallback = new AsyncCallback<ArrayList<Library.Book>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println(throwable.toString());
            }
            @Override
            public void onSuccess(ArrayList<Library.Book> books) {
                dataProvider.setList(books);
            }
        };

        if (myAuthor.equals("")||myTitle.equals("")||myPagesString.equals("")||myYearString.equals(""))
            statusInfo.setText("Error! All fields must be entered!");
        else {
            if (!valid.isValidPages(myPagesString))
                statusInfo.setText("Error! Enter correct number of pages!");
            else {
                if (!valid.isValidYear(myYearString))
                    statusInfo.setText("Error! Enter correct year!");
                else {
                    myPages = Integer.parseInt(myPagesString);
                    myYear = Integer.parseInt(myYearString);
                    if (checkEdit) {
                        GreetingService.App.getInstance().addEditBook(editId, myAuthor, myTitle, myPages, myYear, myDate, asyncCallback);
                        statusInfo.setText("The book was successfully editted");
                    } else {
                        GreetingService.App.getInstance().addNewBook(myAuthor, myTitle, myPages, myYear, myDate, asyncCallback);
                        statusInfo.setText("The book was successfully aded");
                    }
                    myAuthor="";
                    myTitle="";
                    myYearString="";
                    myPagesString="";
                    author.setValue("");
                    title.setValue("");
                    pages.setValue("");
                    year.setValue("");
                    checkEdit = false;

                }
            }
        }
    }

    @UiField
    Button edit;
    @UiHandler("edit")
    void doClickEdit(ClickEvent event) {
        AsyncCallback<ArrayList<Library.Book>> asyncCallback = new AsyncCallback<ArrayList<Library.Book>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println(throwable.toString());
            }
            @Override
            public void onSuccess(ArrayList<Library.Book> books) {
                dataProvider.setList(books);
            }
        };
        if (corIndexString.equals(""))
            statusInfo.setText("Enter correct id!");
        else {
            corIndex=Integer.parseInt(corIndexString);
            statusInfo.setText("");
            Library.Book myBook = new Library.Book();
            for (Library.Book item : dataProvider.getList()) {
                if (corIndex == item.getId()) {
                    myBook = item;
                    break;
                }
            }
            myAuthor = myBook.getAuthor();
            myTitle = myBook.getTitle();
            myPages = myBook.getPages();
            myYear = myBook.getYear();
            myDate = myBook.getDate();
            myYearString=Integer.toString(myBook.getYear());
            myPagesString=Integer.toString(myBook.getPages());



            editId = corIndex;

            author.setValue(myBook.getAuthor());
            title.setValue(myBook.getTitle());
            pages.setValue(Integer.toString(myBook.getPages()));
            year.setValue(Integer.toString(myBook.getYear()));
            date.setValue(myBook.getDate());

            checkEdit = true;
            GreetingService.App.getInstance().deleteBook(corIndex, asyncCallback);
            num_cor.setValue("");
        }
    }
}
