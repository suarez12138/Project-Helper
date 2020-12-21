package com.evan.wj.dao;

import com.evan.wj.bean.*;
import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GroupMethod2DAO extends JpaRepository<User,Integer> {



    // 获取这个 project 下所有的 check point 后面会随意分配
    @Query("select cp.id from CheckPoint cp where cp.project = ?1")
    List<Integer> getCheckPoint(int project_id);


    // 获取这个 project 下所有的 check point 后面会随意分配
    @Query("select p.bool_cross_class from Project p where p.id = ?1")
    List<String> get_can_cross_class(int project_id);

    // 的到小于人数要求的 所有的 组号， 就地解散
    @Query("select pg.gro from PersonGroup pg where pg.gro in (select g.id from Gro g where g.project = ?1) group by pg.gro having count(pg.gro) < ?2")
    List<Integer> getGroupIdWithLessPeople(int project_id, long Min);

    // 原小组成员  person_id, group_id
    @Query("select new com.evan.wj.bean.GG_personGroup(pg2.person,pg2.gro ) from PersonGroup pg2 where pg2.gro in(select pg.gro from PersonGroup pg where pg.gro in (select g.id from Gro g where g.project = ?1) group by pg.gro having count(pg.gro) < ?2)")
    List<GG_personGroup> getPersonGroup(int project_id, long Min);


    // 得到这个project的限制人数 最小和最大
    @Query("select new com.evan.wj.bean.GG_MinMaxOfGroup(p.min_people,p.max_people) from Project p where p.id = ?1")
    List<GG_MinMaxOfGroup> getMinMax(int project_id);

    // 获取所有未组队的人的信息
    @Query("select new com.evan.wj.bean.GG_People2(p.id, c.id, p.domitory, p.gender) from WantPerson wp join People p on p.id = wp.person join PersonClass pc on pc.person = p.id join Class c on pc.class1 = c.id join Project pro on pro.course = c.course where pro.id = ?1 and wp.project = ?1 and wp.gro_status = '未组队'")
    List<GG_People2> getPeople2(int project_id);

    // 获取所有的 tag 信息
    @Query("select new com.evan.wj.bean.GG_PersonIDTag(pt.person, t.id) from Tag t join PeopleTag pt on pt.tag = t.id where t.project = ?1")
    List<GG_PersonIDTag> getPersonTag(int project_id);

    // 通过 groupName 获取 group_id
    @Query("select g.id from Gro g where g.group_name = ?1 ")
    List<Integer> getGroupId(String GroupName);





    //////////////////////////////////////delete a group
    @Transactional
    @Modifying
    @Query(value = "delete from gro g where g.id = ?1", nativeQuery = true)
    void deleteFromGro(int group_id);

    //  从 Person_group 里删除 一个组里所有的人
    @Transactional
    @Modifying
    @Query(value = "delete from person_group pg where pg.gro = ?1", nativeQuery = true)
    void deleteFromPersonGroup(int group_id);

    //  删了一整个小组，小组里所有人 在 want_person 里变成 ‘未组队’
    @Transactional
    @Modifying
    @Query(value = "update want_person wp set wp.gro_status = '未组队' where wp.person in (select pg.person from person_group pg where pg.gro = ?1) and wp.project=?2", nativeQuery = true)
    void update_all_weizudui(int gro_id, int project_id);


    ////////////////////////////////////   create a group
    @Transactional
    @Modifying
    @Query(value = "insert into gro (check_point_id, group_name, group_status, index_in_pre_day, pre_day, project, text) values (?1,?2,'可加入',0,0,?3,'')", nativeQuery = true)
    void insert_gro(int checkPoint, String group_name, int project_id);

    @Transactional
    @Modifying
    @Query(value = "insert into person_group (grade, gro, person, pg_status, text) VALUES (0,?1,?2,'notGraded','')", nativeQuery = true)
    void insert_PersonGro(int group_id, int person_id);

    @Transactional
    @Modifying
    @Query(value = "update want_person set gro_status = '已组队' where person = ?1 and project = ?2", nativeQuery = true)
    void update_single_yizudui(int person_id, int project_id);


}
