package com.group4.server.service.impl;

import com.group4.server.mapper.MailLogMapper;
import com.group4.server.pojo.MailLog;
import com.group4.server.service.IMailLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author awei
 * @since 2022-03-13
 */
@Service
public class MailLogServiceImpl extends ServiceImpl<MailLogMapper, MailLog> implements IMailLogService {

}
