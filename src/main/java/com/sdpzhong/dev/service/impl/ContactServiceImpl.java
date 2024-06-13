package com.sdpzhong.dev.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdpzhong.dev.entity.po.Contact;
import com.sdpzhong.dev.mapper.ContactMapper;
import com.sdpzhong.dev.service.ContactService;
import org.springframework.stereotype.Service;

/**
 * @author zhongqing
 * @description 针对表【contact】的数据库操作Service实现
 * @createDate 2024-06-12 18:09:08
 */
@Service
public class ContactServiceImpl extends ServiceImpl<ContactMapper, Contact>
        implements ContactService {

}




