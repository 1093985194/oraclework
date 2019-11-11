package com.oyzy.oraclework.controller;

import com.oyzy.oraclework.bean.Custom;
import com.oyzy.oraclework.bean.Goods;
import com.oyzy.oraclework.bean.Lorder;
import com.oyzy.oraclework.mapper.CustMapper;
import com.oyzy.oraclework.mapper.GoodMapper;
import com.oyzy.oraclework.mapper.ListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/custs")
public class CustController {

    @Autowired
    CustMapper custMapper;
    @Autowired
    ListMapper listMapper;
    @Autowired
    GoodMapper goodMapper;

    //显示所有
    @RequestMapping("/list")
    public String findAll(Model model){
        List<Custom> list = custMapper.findAll();
        model.addAttribute("custs", list);
        return "custom";
    }
    //添加
    @RequestMapping("/edit")
    public String editCustom(Model model,ModelMap map, @RequestParam(defaultValue = "0") Integer id) {
        if (id > 0) {
            map.addAttribute("isAdd", false);
            map.addAttribute("cust", custMapper.getCustomById(id));
            map.addAttribute("goods",goodMapper.findAll());
            System.out.println(id);

            List<Goods> glist = listMapper.getGidByCId(id);
            System.out.println(glist);
            List<Goods> goodsList = new ArrayList<Goods>();
            if (glist.size()>0){
                for (int i=0;i<glist.size();i++){
                    Goods goods = glist.get(i);
                    Integer code = goods.getGid();
                    System.out.println(code);
                    goodsList.add(listMapper.getGoodByGId(code));
                    System.out.println("goodsList="+goodsList);
                }
            }
            model.addAttribute("lgoods",goodsList);

        } else {
            map.addAttribute("isAdd", true);
            map.addAttribute("cust", new Custom());
        }
        return "update";
    }
    @RequestMapping("/save")
    public String save(@ModelAttribute Custom cust, @ModelAttribute Lorder lorder) {
        if (cust == null) {
            return null;
        }
        if (cust.getCid() != null && cust.getCid() > 0) {
            custMapper.updateCustom(cust);
        } else {
            custMapper.insertCustom(cust);
        }
        return "success";
    }

    //删除用户
    @RequestMapping("/delete/{cid}")
    public String deleteCustom(@PathVariable("cid") Integer id, Model model) {
        custMapper.deleteCustomById(id);
        List<Custom> list = custMapper.findAll();
        model.addAttribute("custs", list);
        return "success";
    }
    //删除订单
    @RequestMapping("/delist")
    public String deletelorder(@RequestParam(defaultValue = "0") Integer gid, Model model,ModelMap map) {
        map.addAttribute("isAdd", false);
        listMapper.deletelorderByGId(gid);
        return "addsuccess";
    }

    //单个查询
    @RequestMapping("/find")
    public String getCustom(Model model,ModelMap map, @RequestParam(defaultValue = "0") Integer id) {
        if (id != null) {
            System.out.println("success");
            System.out.println(id);
            map.addAttribute("cust", custMapper.getCustomById(id));

            List<Goods> glist = listMapper.getGidByCId(id);
            System.out.println(glist);
            List<Goods> goodsList = new ArrayList<Goods>();
            if (glist.size()>0){
                for (int i=0;i<glist.size();i++){
                    Goods goods = glist.get(i);
                    Integer code = goods.getGid();
                    System.out.println(code);
                    goodsList.add(listMapper.getGoodByGId(code));
                    System.out.println("goodsList="+goodsList);
                }
            }
            model.addAttribute("goods",goodsList);
            System.out.println("model="+model);

        }else{
            System.out.println("fail");
        }
        return "find";
    }
    @ResponseBody
    @GetMapping("/test/{cid}")
    List<Goods> test(@PathVariable("cid") Integer cid){
        List<Goods> glist = listMapper.getGidByCId(cid);
        System.out.println(glist);
        List<Goods> goodsList = new ArrayList<Goods>();
        if (glist.size()>0){
            for (int i=0;i<glist.size();i++){
                Goods goods = glist.get(i);
                Integer code = goods.getGid();
                System.out.println(code);
                goodsList.add(listMapper.getGoodByGId(code));
            }
        }
        return goodsList;
    }

    //删除用户
//    @ResponseBody
    @RequestMapping("/add")
    public String addCustom(@RequestParam(defaultValue = "0") Integer cid,@RequestParam(defaultValue = "0") Integer gid, Model model) {
        System.out.println("cid="+cid+"gid="+gid);
        listMapper.insertCustom(cid,gid);
        List<Goods> glist = listMapper.getGidByCId(cid);
        System.out.println(glist);
        List<Goods> goodsList = new ArrayList<Goods>();
        if (glist.size()>0){
            for (int i=0;i<glist.size();i++){
                Goods goods = glist.get(i);
                Integer code = goods.getGid();
                System.out.println(code);
                goodsList.add(listMapper.getGoodByGId(code));
                System.out.println("goodsList="+goodsList);
            }
        }
        model.addAttribute("goods",goodsList);
        return "addsuccess";
    }
}
