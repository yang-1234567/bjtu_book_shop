package com.bjtu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.bjtu.bean.*;
import com.bjtu.service.AddressService;
import com.bjtu.service.BookService;
import com.bjtu.service.CartService;
import com.bjtu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: jzhang
 * @Date: 2019/9/29 14:20
 * @Description: 订单控制器
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private CartService cartService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private BookService bookService;

    /**
     * 确认订单
     */
    @RequestMapping("/confirm")
    public String confirm(String ids, HttpSession session, Model model){
        //查询记录
        List<CartVo> cartVos = cartService.findCartByIds(ids);
        System.out.println(ids + "=================================");
        //获取当前用户的收货地址
        QueryWrapper<Address> addressQueryWrapper = new QueryWrapper<>();
        User user = (User) session.getAttribute("user");
        addressQueryWrapper.eq("user_id",user.getId());
        List<Address> addressList = addressService.list(addressQueryWrapper);

        //将购买的商品信息添加到session中
        session.setAttribute("cartVos",cartVos);

        model.addAttribute("list",cartVos);
        model.addAttribute("addressList",addressList);
        return "confirm_order";
    }

    @RequestMapping("/confirmlj")
    public String confirmlj(HttpSession session, Model model){
        //查询记录
        User user = (User) session.getAttribute("user");
        List<CartVo> cartVos = cartService.findCartByUser(user.getId());

        CartVo cartVo = cartVos.get(cartVos.size() - 1);
        cartVos.clear();
        cartVos.add(cartVo);
        System.out.println(cartVos.size() + "===================");

        //获取当前用户的收货地址
        QueryWrapper<Address> addressQueryWrapper = new QueryWrapper<>();

        addressQueryWrapper.eq("user_id",user.getId());
        List<Address> addressList = addressService.list(addressQueryWrapper);

        //将购买的商品信息添加到session中
        session.setAttribute("cartVos",cartVos);

        model.addAttribute("list",cartVos);
        model.addAttribute("addressList",addressList);
        return "confirm_order";
    }

    /**
     * 提交订单
     */
    @RequestMapping("/commitOrder")
    public String commitOrder(Integer addrId,HttpSession session){
        List<CartVo> cartVos = (List<CartVo>) session.getAttribute("cartVos");
        String flag = orderService.buy(cartVos,addrId,session);
        if(flag.equals("success")){
            //跳转至订单列表页
            return "redirect:/order/list";
        }else {
            return "redirect:/book/index";
        }
    }

    /**
     * 显示用户订单列表
     */
    @RequestMapping("/list")
    public String list(){
        return "order_list";
    }

    /**
     *
     * @param session
     * @param model
     * @return 获取用户订单信息
     */
    @RequestMapping("/getOrderListData")
    public String getOrderListData(HttpSession session, OrderQueryVo orderQueryVo, Model model){
        User user = (User) session.getAttribute("user");
        List<Order> orders = orderService.findUserOrder(user.getId(),orderQueryVo);
        model.addAttribute("orders",orders);
        model.addAttribute("pre",orderQueryVo.getPage() -1);
        model.addAttribute("next",orderQueryVo.getPage() + 1);
        model.addAttribute("cur",orderQueryVo.getPage());
        model.addAttribute("pages",orderService.findUserOrderPages(user.getId(),orderQueryVo));
        model.addAttribute("pageSize",orderQueryVo.getPageSize());
        return "orderData";
    }

}
