package com.group4.server.service.impl;

import com.group4.server.mapper.PositionMapper;
import com.group4.server.pojo.Position;
import com.group4.server.service.IPositionService;
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
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements IPositionService {

}
