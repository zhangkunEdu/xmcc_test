package com.xmcc;

import com.xmcc.entity.Student;
import com.xmcc.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WechatPayApplicationTests {

    @Resource
    private StudentRepository studentRepository;

    @Test
    public void contextLoads() {
        /*List<Student> all = studentRepository.findAll();
        all.stream().forEach(System.out::println);*/

        //studentRepository.save(new Student(null,"何永欢1","23","女","重庆"));

        //测试关键字定义方法
        /*List<Integer> ids = Lists.newArrayList(88, 89, 90);
        List<Student> list = studentRepository.findAllByIdIn(ids);
        list.stream().forEach(System.out::println);*/


        //测试自定义方法一
        //基于sql查询
        Student student = studentRepository.queryStudentsByStudentId(92);
        System.out.println(student.toString());

        //测试自定义方法二
        //基于实体类查询
        Student student1 = studentRepository.getStudentsByStudentId(93);
        System.out.println(student1);


    }

}
