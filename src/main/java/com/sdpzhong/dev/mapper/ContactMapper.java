package com.sdpzhong.dev.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sdpzhong.dev.entity.po.Contact;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhongqing
 * @description 针对表【contact】的数据库操作Mapper
 * @createDate 2024-06-12 18:09:08
 * @Entity com.sdpzhong.dev.entity.po.Contact
 */
@Mapper
public interface ContactMapper extends BaseMapper<Contact> {

}




