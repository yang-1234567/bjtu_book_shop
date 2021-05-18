package com.bjtu.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.bjtu.bean.Address;
import com.bjtu.mapper.AddressMapper;
import org.springframework.stereotype.Service;

/**
 * @Auther: jzhang
 * @Date: 2019/9/29 15:04
 * @Description:
 */
@Service
public class AddressService extends ServiceImpl<AddressMapper, Address> {
}
