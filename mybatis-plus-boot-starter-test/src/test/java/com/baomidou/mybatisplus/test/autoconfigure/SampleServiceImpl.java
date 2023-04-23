package com.baomidou.mybatisplus.test.autoconfigure;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * This class is for xxxx.
 *
 * @author kk37005
 */
@Slf4j
@Service
public class SampleServiceImpl extends ServiceImpl<SampleMapper, Sample> implements SampleService {
}
