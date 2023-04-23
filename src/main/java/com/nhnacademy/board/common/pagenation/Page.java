package com.nhnacademy.board.common.pagenation;

import java.util.List;

public interface Page<T> {
    int getPageNumber();            // 현재 페이지 번호
    int getPageSize();              // 한 페이지에 보여줄 게시물 갯수
    int getTotalPageCount();        // 총 페이지 수

    long getTotalCount();           // 총 게시물 수
    List<T> getList();              // 게시물 목록
}
