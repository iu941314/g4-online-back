package com.group4.server.service.impl;

import com.group4.server.mapper.OplogMapper;
import com.group4.server.pojo.Oplog;
import com.group4.server.service.IOplogService;
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
public class OplogServiceImpl extends ServiceImpl<OplogMapper, Oplog> implements IOplogService {

}
