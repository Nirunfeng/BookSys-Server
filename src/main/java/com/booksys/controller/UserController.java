package com.booksys.controller;

import com.booksys.mapper.ReaderMapper;
import com.booksys.pojo.Reader;
import com.booksys.util.DateTimeUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    //TODO
    @Autowired
    private ReaderMapper readerMapper;
    /**
     * 用户登录
     * @param account
     * @param password
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Map<String,Object> login(@RequestParam("account")String account, @RequestParam("password")String password){
        Map<String,Object> map=new HashMap<>();
        Reader reader=readerMapper.selectWholeByAccount(account);
        if(reader!=null){
            if(reader.getPassword().equals(password)){
                map.put("result","yes");
                map.put("loginUser",reader);
                if(reader.getCondi()==0){
                    map.put("condi",0);
                }else if(reader.getCondi()==1){
                    map.put("condi",1);
                }else{
                    map.put("condi",2);
                }
                return map;
            }
        }
        map.put("result","no");
        return map;
    }

    /**
     * 用户注册
     * @param account
     * @param name
     * @param password
     * @param sex
     * @param condi
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Map<String,Object> register(@RequestParam("account") String account, @RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("sex") String sex, @RequestParam("condi") int condi){
        Map<String,Object> map=new HashMap<>();
        /*插入用户*/
        try {
            readerMapper.insert(new Reader(account,password,name,sex, DateTimeUtil.getDate(),condi));
            map.put("result","yes");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     *权限修改
     * @param account
     * @param condi
     * @return
     */
    @RequestMapping(value = "/updateRole",method = RequestMethod.POST)
    public Map<String,Object> updaterole(@RequestParam("account") String account,@RequestParam("condi") int condi){
        Map<String,Object> map=new HashMap<>();
        Reader reader=readerMapper.selectWholeByAccount(account);
        if(reader==null){
            //不存在该用户
            map.put("status","failed");
        }
        //获取id，根据id修改权限

        try {
            //修改成功
            readerMapper.update(reader.getRid(),condi);
            map.put("status","ok");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 展示所有用户
     * @param account
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/getAllReaders",method = RequestMethod.POST)
    public Map<String,Object> getAllReaders(@RequestParam("account")String account,@RequestParam("currentPage")int currentPage){
        Map<String,Object> map=new HashMap<>();
        PageHelper.startPage(currentPage,10);
        List<Reader> list=readerMapper.selectByAccount(account);
        PageInfo<Reader> pageInfo=new PageInfo<>(list);
        map.put("readers",list);
        map.put("pageInfo",pageInfo);
        return map;
    }

    /**
     * 添加用户业务
     * @param account
     * @param name
     * @param sex
     * @param condi
     * @return
     */
    @RequestMapping("/addReader")
    public Map<String,Object> addUser(@RequestParam("account") String account,@RequestParam("name") String name,@RequestParam("sex") String sex,@RequestParam("condi") int condi){
        /*1.声明返回结果*/
        Map<String,Object> map=new HashMap<>();
        /*2.判断是否有重复*/
        if(readerMapper.selectByAccount(account).size()!=0){
            /*2.1.重复，返回失败*/
            map.put("status","no");
       }else{
            //默认密码，可更改密码生成逻辑
            String password="123456";
            //当前注册时间
            String time=DateTimeUtil.getDate();
            Reader reader=new Reader(account,password,name,sex,time,condi);
            readerMapper.insert(reader);
            //返回状态码
            map.put("status","ok");
        }

        //返回map
        return map;
    }
}
