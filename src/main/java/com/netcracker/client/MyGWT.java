package com.netcracker.client;

import com.google.gwt.user.client.ui.*;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.ibm.icu.impl.CalendarAstronomer;

import java.util.ArrayList;
import java.util.Date;

public class MyGWT implements EntryPoint {

  //private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

    list myList;
    Options myOptions;
    Sorting mySorting;

  public void onModuleLoad() {

      HorizontalPanel mainPanel=new HorizontalPanel();
      VerticalPanel panel1= new VerticalPanel();




      AsyncCallback<ArrayList<Library.Book>> asyncCallback = new AsyncCallback<ArrayList<Library.Book>>() {
          @Override
          public void onFailure(Throwable caught) {
              System.out.println(caught.toString());
          }
          @Override
          public void onSuccess(ArrayList<Library.Book> res) {
              myList = new list(res);
              myOptions=new Options();
              mySorting = new Sorting();
              myOptions.setDataProvider(myList.getList());
              mySorting.setDataProvider(myList.getList());
              mainPanel.add(myOptions);
             // mainPanel.add(mySorting);
             // mainPanel.add(myList);
              panel1.add(mySorting);
              panel1.add(myList);

              mainPanel.add(panel1);
              RootPanel.get().add(mainPanel);
          }
      };
      GreetingService.App.getInstance().getLibrary("Success!!", asyncCallback);
  }
}
