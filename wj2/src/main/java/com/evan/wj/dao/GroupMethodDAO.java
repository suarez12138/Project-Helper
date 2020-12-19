package com.evan.wj.dao;

import com.evan.wj.bean.GG_AllGroup;

import com.evan.wj.bean.GG_AllPeopleNotGrouped;
import com.evan.wj.bean.GG_AllTag;
import com.evan.wj.bean.GG_MinMaxOfGroup;
import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupMethodDAO extends JpaRepository<User,Integer> {
    @Query("select new com.evan.wj.bean.GG_AllTag(t.id, t.tag) from Tag t where t.project = ?1")
    List<GG_AllTag> getAllTag(int project_id);

    @Query("select new com.evan.wj.bean.GG_AllPeopleNotGrouped(wp.person, pc.class1 , p.domitory , pt.tag) from WantPerson wp join People p on p.id = wp.person join PersonClass pc on wp.person = pc.person left join PeopleTag pt on pt.person = wp.person and pt.tag in (select t.id from Tag t where t.project = ?1)" +
            "where wp.gro_status = '未组队' and wp.project = ?1 and pc.class1 in (select c.id from Class c join Project p on p.course = c.course where p.id = ?1)")
    List<GG_AllPeopleNotGrouped> getAllPeopleNotGrouped(int project_id);

    @Query("select new com.evan.wj.bean.GG_MinMaxOfGroup(p.min_people,p.max_people) from Project p where p.id = ?1")
    List<GG_MinMaxOfGroup> getMinMax(int project_id);

    @Query("select new com.evan.wj.bean.GG_AllGroup(g.id, pg.person, pc.class1, p.domitory, pt.tag) from Gro g join PersonGroup pg on g.id = pg.gro join People p on p.id = pg.person " +
            "join PersonClass pc on pg.person = pc.person left join PeopleTag pt on pc.person = pt.person " +
            "where g.project = ?1 and g.id in (select g.id from Gro g join PersonGroup pg on g.id = pg.gro " +
            "where g.project = ?1 group by g.id having count(g.id) < ?2) and pc.class1 in (" +
            "select c.id from Class c join Project p on c.course = p.course where p.id = ?1)" +
            "and (pt.tag in (select t.id from Tag t where t.project = ?1 ) or pt.tag is null)")
    List<GG_AllGroup> getAllGroupWithLessPeople(int project_id, int min_people);

    @Query("select new com.evan.wj.bean.GG_AllGroup(g.id, pg.person, pc.class1, p.domitory, pt.tag) from Gro g join PersonGroup pg on g.id = pg.gro join People p on p.id = pg.person " +
            "join PersonClass pc on pg.person = pc.person left join PeopleTag pt on pc.person = pt.person " +
            "where g.project = ?1 and g.id in (select g.id from Gro g join PersonGroup pg on g.id = pg.gro " +
            "where g.project = ?1 group by g.id " +
            "having count(g.id) >= ?2) and count(g.id) <?3 " +
            "and pc.class1 in (" +
            "select c.id from Class c join Project p on c.course = p.course where p.id = ?1)" +
            "and (pt.tag in (select t.id from Tag t where t.project = ?1 ) or pt.tag is null)")
    List<GG_AllGroup> getAllGroupWithMinPeople(int project_id, int min_people, int max_perople);






}
