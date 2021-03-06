package com.example.chatroom.dao;

import com.example.chatroom.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


//CrudRepository：提供标准的创建，读取，更新和删除功能，
// 其中包含诸如findOne().findAll()，save()，delete()等方法。
// PagingAndSortingRepository：它扩展了CrudRepository并添加了findAll方法。
// 它能够以分页方式对数据进行排序和检索。
// JpaRepository扩展了存储库CrudRepository和PagingAndSortingRepository。
// 它添加了特定于JPA的方法，例如flush()，以在持久性上下文上触发刷新。
@Repository
public interface UserDao extends JpaRepository<User,Integer> {

    User findUserByUsernameAndPassword(String username,String password);
    User findUserByUsername(String username);
    User findUserByUserId(Integer userId);

    @Query("select new User(u.userId,u.username,u.avatar) from User u where u.userId=?1")
    User findUser(Integer userId);

    /**
     * 修改图片路径
     * @param path
     * @param userId
     * @return
     */
    @Transactional//不加这个注释就会报错
    @Query("update User set avatar=?1 where userId=?2")
    @Modifying
    int changeAvatar( String path,Integer userId);

    @Transactional // 自定义update、delete、insert 的 Dao 接口必备
    @Query("delete from User ")
    void deleteConnectionByUserId(Integer userId);






}
