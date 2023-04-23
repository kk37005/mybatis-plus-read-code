package com.baomidou.mybatisplus.test.autoconfigure;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author miemie
 * @since 2020-05-27
 */
@Slf4j
//@MybatisPlusTest(properties = "spring.datasource.schema=classpath:schema.sql")
@MybatisPlusTest
@ComponentScan("com.baomidou.mybatisplus.test.autoconfigure")
class MybatisPlusSampleTest {

    @Autowired
    private SampleMapper sampleMapper;

    @Autowired
    private SampleService sampleService;

    @BeforeEach
    void initData(){
        int size = 10;
        String[] nameStr = {"张三","李四","王五","八神庵","不知火舞","大蛇","景天","唐雪见","李逍遥","赵灵儿"};
        List<Sample> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(Sample.builder().name(nameStr[i]).build());
        }
        sampleService.saveBatch(list);
    }

    @Test
    void testInsert() {
        Sample sample = new Sample();
        sampleMapper.insert(sample);
        // 由于会自动生产ID
        assertThat(sample.getId()).isNotNull();
    }

    @Test
    void test1() {
        Sample sample = new Sample();
        sampleMapper.insert(sample);
        assertThat(sample.getId()).isNotNull();

        List<Sample> samples = sampleMapper.selectList(Wrappers.emptyWrapper());
        log.info(samples.toString());
    }

    @Test
    void test2() {
        Sample sample = new Sample();
        sample.setName("张三");
        sampleService.save(sample);
        assertThat(sample.getId()).isNotNull();

        List<Sample> samples = sampleService.list();
        log.info(samples.toString());
    }

    @Test
    void testWarrper(){
        LambdaQueryWrapper<Sample> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Sample::getName, "张三");
        Sample one = sampleService.getOne(queryWrapper,false);
        log.info(JSONUtil.parseObj(one, false).toString());
    }

}
