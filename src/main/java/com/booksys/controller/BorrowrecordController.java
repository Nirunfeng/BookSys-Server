package com.booksys.controller;

import com.booksys.mapper.BorrowrecordMapper;
import com.booksys.mapper.SubalbumMapper;
import com.booksys.pojo.Borrowrecord;
import com.booksys.pojo.Subalbum;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/borrowrecord")
public class BorrowrecordController {
    @Autowired
    private BorrowrecordMapper borrowrecordMapper;

    @Autowired
    private SubalbumMapper subalbumMapper;
    /**
     *获取全部借阅记录
     * @param raccount
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/getAllBorrowRecords",method = RequestMethod.POST)
    public Map<String,Object> getAllBorrowRecords(@RequestParam("raccount")String raccount, @RequestParam("currentPage")int currentPage){
        Map<String,Object> map=new HashMap<>();
        PageHelper.startPage(currentPage,10);
        List<Borrowrecord> list=borrowrecordMapper.selectAllInfoByRaccount(raccount);
        PageInfo<Borrowrecord> pageInfo=new PageInfo<>(list);
        map.put("pageInfo",pageInfo);
        map.put("borrowrecords",list);
        return map;
    }

    @RequestMapping(value = "/reback",method = RequestMethod.POST)
    public Map<String,Object> reback(@RequestParam("bid")int bid,@RequestParam("sid")int sid){
        Map<String,Object> map=new HashMap<>();
        if(bid!=0&&sid!=0){
            borrowrecordMapper.delete(borrowrecordMapper.selectByPrimaryKey(bid));
            Subalbum subalbum=subalbumMapper.selectByPrimaryKey(sid);
            subalbum.setCondi(0);
            subalbumMapper.updateByPrimaryKey(subalbum);
            map.put("status","yes");
        }else{
            map.put("status","no");
        }

        return map;
    }
}
