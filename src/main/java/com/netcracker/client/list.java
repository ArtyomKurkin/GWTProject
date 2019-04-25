package com.netcracker.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SingleSelectionModel;

import java.util.List;

public class list extends Composite {

    @UiField(provided=true)
    CellTable<Library.Book> table;
    @UiField(provided=true)
    SimplePager pager;

    @UiTemplate("list.ui.xml")
    interface listUiBinder extends UiBinder<Widget, list> {
    }

    private static listUiBinder ourUiBinder = GWT.create(listUiBinder.class);
    private ListDataProvider<Library.Book> dataProvider;
    private SingleSelectionModel<Library.Book> selectionModel;


    public list(List<Library.Book> arr) {

        ProvidesKey<Library.Book> keyProvider = new ProvidesKey<Library.Book>() {
            public Object getKey(Library.Book t) {
                return t == null? null : t.getId();
            }
        };

        dataProvider = new ListDataProvider<>(keyProvider);
        table = new CellTable<>(5,keyProvider);
        table.setWidth("100%", true);

        table.setAutoHeaderRefreshDisabled(true);
        table.setAutoFooterRefreshDisabled(true);

        SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
        pager = new SimplePager(SimplePager.TextLocation.CENTER, pagerResources, false, 0, true);
        pager.setDisplay(table);

        initTableColumns();

        List<Library.Book> list = dataProvider.getList();
        list.addAll(arr);
        dataProvider.addDataDisplay(table);

        initWidget(ourUiBinder.createAndBindUi(this));
    }

    ListDataProvider<Library.Book> getList(){
        return dataProvider;
    }


    private void initTableColumns(){
        TextColumn<Library.Book> idColumn = new TextColumn<Library.Book>() {
            @Override
            public String getValue(Library.Book object) {
                return Integer.toString(object.getId());
            }
        };
        table.addColumn(idColumn,"Id");
        table.setColumnWidth(idColumn,5, Style.Unit.PX);

        TextColumn<Library.Book> authorColumn = new TextColumn<Library.Book>() {
            @Override
            public String getValue(Library.Book object) {
                return object.getAuthor();
            }
        };
        table.addColumn(authorColumn,"Author");
        table.setColumnWidth(authorColumn,16, Style.Unit.PX);

        TextColumn<Library.Book> titleColumn = new TextColumn<Library.Book>() {
            @Override
            public String getValue(Library.Book object) {

                return object.getTitle();
            }
        };
        table.addColumn(titleColumn,"Title");
        table.setColumnWidth(titleColumn,14, Style.Unit.PX);

        TextColumn<Library.Book> pageColumn = new TextColumn<Library.Book>() {
            @Override
            public String getValue(Library.Book object) {
                return Integer.toString(object.getPages());
            }
        };
        table.addColumn(pageColumn,"Pages");
        table.setColumnWidth(pageColumn,5, Style.Unit.PX);

        TextColumn<Library.Book> yearColumn = new TextColumn<Library.Book>() {
            @Override
            public String getValue(Library.Book object) {
                return Integer.toString(object.getYear());
            }
        };
        table.addColumn(yearColumn,"Year");
        table.setColumnWidth(yearColumn,5, Style.Unit.PX);

        TextColumn<Library.Book> dateColumn = new TextColumn<Library.Book>() {
            @Override
            public String getValue(Library.Book object) {
                return object.getDate().toString();
            }
        };
        table.addColumn(dateColumn,"Date added");
        table.setColumnWidth(dateColumn,17, Style.Unit.PX);
    }
    @UiField
    HorizontalPanel horPanel;
}