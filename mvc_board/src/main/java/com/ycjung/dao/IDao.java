package com.ycjung.dao;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.ycjung.dto.ContentDto;

public interface IDao {
    
    public ArrayList<ContentDto> listDao(); // 모든 List 조회
    public void writeDao(String bTitle, String bAuthor, String bContent); // 글쓰기
    public ContentDto viewDao(int bCd); // 특정 콘텐츠 검색
    public void deleteDao(String bCd); // 특정 콘텐츠 삭제
    public void dummyDataInsert(String bTitle, String bAuthor, String bContent); // 더미데이터 100개
    public void deleteAll(); // 모두 삭제
    public void upHit(int bCd); // 조회수 증가
    public void modify(String bAuthor, String bTitle, String bContent, Timestamp bDate, int bCd);
}
