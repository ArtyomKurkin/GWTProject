package com.netcracker.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.view.client.ListDataProvider;

import java.util.ArrayList;

public class Sorting extends Composite {
    interface filterUiBinder extends UiBinder<HTMLPanel, Sorting> {
    }

    private static filterUiBinder ourUiBinder = GWT.create(filterUiBinder.class);
    private ListDataProvider<Library.Book> dataProvider;
    public Sorting() {
        initWidget(ourUiBinder.createAndBindUi(this));
        Combobox.addItem("Id");
        Combobox.addItem("Author");
        Combobox.addItem("Title");
        Combobox.addItem("Pages");
        Combobox.addItem("Year");
        Combobox.addItem("Date");

    }

    public void setDataProvider(ListDataProvider<Library.Book> dataProvider) {
        this.dataProvider = dataProvider;
    }

    @UiField
    ListBox Combobox;
    @UiField
    Button sortBut;


    @UiHandler("sortBut")
    void doClickSort(ClickEvent event) {
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
        String choice = Combobox.getSelectedItemText();
        GreetingService.App.getInstance().sortLibrary(choice,asyncCallback);
    }





}