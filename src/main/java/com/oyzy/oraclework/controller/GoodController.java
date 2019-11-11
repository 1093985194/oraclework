package com.oyzy.oraclework.controller;

import com.oyzy.oraclework.bean.Goods;
import com.oyzy.oraclework.mapper.GoodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodController {

    @Autowired
    GoodMapper goodMapper;

    //显示所有
    @RequestMapping("/list")
    public String findAll(Model model){
        List<Goods> list = goodMapper.findAll();
        model.addAttribute("goods", list);
        return "goodview";
    }
    //添加
    @RequestMapping("/edit")
    public String editGoods(ModelMap map, @RequestParam(defaultValue = "0") Integer id) {
        if (id > 0) {
            map.addAttribute("isAdd", false);
            map.addAttribute("good", goodMapper.getGoodsById(id));
        } else {
            map.addAttribute("isAdd", true);
            map.addAttribute("good", new Goods());
        }
        return "Gupdate";
    }
    @RequestMapping("/save")
    public String save(@ModelAttribute Goods good) {
        if (good == null) {
            return null;
        }
        if (good.getGid() != null && good.getGid() > 0) {
            goodMapper.updateGoods(good);
        } else {
            goodMapper.insertGoods(good);
        }
        return "Gsuccess";
    }

    //删除
    @RequestMapping("/delete/{gid}")
    public String deleteGoods(@PathVariable("gid") Integer id, Model model) {
        goodMapper.deleteGoodsById(id);
        List<Goods> list = goodMapper.findAll();
        model.addAttribute("goods", list);
        return "Gsuccess";
    }

    //单个查询
    @RequestMapping("/find")
    public String getGoods(ModelMap map, @RequestParam(defaultValue = "0") Integer id) {
        if (id != null) {
            System.out.println("success");
            System.out.println(id);
            map.addAttribute("good", goodMapper.getGoodsById(id));
        }else{
            System.out.println("fail");
        }
        return "Gfind";
    }
}
