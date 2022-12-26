package com.yyh.wubida.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyh.wubida.entity.AddressBook;
import com.yyh.wubida.mapper.AddressBookMapper;
import com.yyh.wubida.service.IAddressBookService;
import org.springframework.stereotype.Service;

/**
 * 地址簿服务类实现
 */
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements IAddressBookService {

}