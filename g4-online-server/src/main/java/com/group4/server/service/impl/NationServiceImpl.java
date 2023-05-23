package com.group4.server.service.impl;

import com.group4.server.mapper.NationMapper;
import com.group4.server.pojo.Nation;
import com.group4.server.service.INationService;
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
public class NationServiceImpl extends ServiceImpl<NationMapper, Nation> implements INationService {

}
