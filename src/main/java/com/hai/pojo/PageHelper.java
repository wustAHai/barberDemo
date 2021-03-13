package com.hai.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenz at 14:53 on 2021/3/6
 */
public class PageHelper<T> {
    private static final int MAX_PAGE = 8;
    int itemCount;
    int currentPage;
    int itemsPerPage = 8;
    int pageCount;
    List<Integer> pages;
    List<T> items;

    public int getItemCount() {
        return itemCount ;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<Integer> getPages() {
        doPages();
        return pages;
    }

    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }

    private void doPages() {
        //仿京东分页
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (pageCount <= MAX_PAGE) {
            for (int i = 1; i <= pageCount; i++) {
                arrayList.add(i);
            }
        } else {
            arrayList.add(1);
            arrayList.add(2);
            if (currentPage > 5 && currentPage + 2 < pageCount) {
                arrayList.add(-1);
                arrayList.add(currentPage - 2);
                arrayList.add(currentPage - 1);
                arrayList.add(currentPage);
                arrayList.add(currentPage + 1);
                arrayList.add(currentPage + 2);
                arrayList.add(-1);
            } else if (currentPage > 5 && currentPage + 2 >= pageCount) {
                arrayList.add(-1);
                arrayList.add(pageCount - 4);
                arrayList.add(pageCount - 3);
                arrayList.add(pageCount - 2);
                arrayList.add(pageCount - 1);
                arrayList.add(pageCount);
            } else {
                arrayList.add(3);
                arrayList.add(4);
                arrayList.add(5);
                arrayList.add(6);
                arrayList.add(7);
                arrayList.add(-1);
            }
        }
        pages = arrayList;
    }
}
