package com.bjtu.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjtu.mapper.OrderItemMapper;
import org.springframework.stereotype.Service;
import com.bjtu.bean.OrderItem;

/**
 * @Auther: jzhang
 * @Date: 2019/9/29 16:37
 * @Description:
 */
@Service
public class OrderItemService extends ServiceImpl<OrderItemMapper,OrderItem> {
}
