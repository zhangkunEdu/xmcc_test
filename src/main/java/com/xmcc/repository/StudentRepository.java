package com.xmcc.repository;

import com.xmcc.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


//泛型一 表示实体类类型  泛型二 表示主键类型
public interface StudentRepository extends JpaRepository<Student,Integer> {

    //关键字定义sql（已有的）
    List<Student> findAllByIdIn(List<Integer> ids);


    //自定义sql
    //jap底层实现是 hibernate  hibernate有一个贼他吗叼的地方：hql（hibernate query language） ->基于实体类查询
    //                                         所以jpa有个：jpql->基于实体类查询   使用nativeQuery属性->基于sql的查询

    /**
     * 基于sql查询
     * @param id
     * @return
     */
    @Query(value = "select * from student where id=?1",nativeQuery = true)
    Student queryStudentsByStudentId(Integer id);


    /**
     * 基于实体类查询
     */
    @Query(value = "select s from Student s where id=?1")
    Student getStudentsByStudentId(Integer id);


    // List<Student> findAllByIdInLimit(List<Integer> ids);
}