package xyz.flysium.dao.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.JdbcType;
import xyz.flysium.dao.entity.UserAccountBookDO;
import xyz.flysium.dao.entity.UserAccountBookDOExample;

@Mapper
public interface UserAccountBookDOMapper {
    @SelectProvider(type=UserAccountBookDOSqlProvider.class, method="countByExample")
    long countByExample(UserAccountBookDOExample example);

    @Insert({
        "insert into user_account_book (uid, name, ",
        "type, creator, updater, ",
        "create_time, update_time, ",
        "remark, is_deleted)",
        "values (#{uid,jdbcType=BIGINT}, #{name,jdbcType=VARCHAR}, ",
        "#{type,jdbcType=TINYINT}, #{creator,jdbcType=BIGINT}, #{updater,jdbcType=BIGINT}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{remark,jdbcType=VARCHAR}, #{isDeleted,jdbcType=TINYINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(UserAccountBookDO record);

    @InsertProvider(type=UserAccountBookDOSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(UserAccountBookDO record);

    @SelectProvider(type=UserAccountBookDOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="uid", property="uid", jdbcType=JdbcType.BIGINT),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.TINYINT),
        @Result(column="creator", property="creator", jdbcType=JdbcType.BIGINT),
        @Result(column="updater", property="updater", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_deleted", property="isDeleted", jdbcType=JdbcType.TINYINT)
    })
    List<UserAccountBookDO> selectByExampleWithRowbounds(UserAccountBookDOExample example, RowBounds rowBounds);

    @SelectProvider(type=UserAccountBookDOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="uid", property="uid", jdbcType=JdbcType.BIGINT),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.TINYINT),
        @Result(column="creator", property="creator", jdbcType=JdbcType.BIGINT),
        @Result(column="updater", property="updater", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_deleted", property="isDeleted", jdbcType=JdbcType.TINYINT)
    })
    List<UserAccountBookDO> selectByExample(UserAccountBookDOExample example);

    @Select({
        "select",
        "id, uid, name, type, creator, updater, create_time, update_time, remark, is_deleted",
        "from user_account_book",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="uid", property="uid", jdbcType=JdbcType.BIGINT),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.TINYINT),
        @Result(column="creator", property="creator", jdbcType=JdbcType.BIGINT),
        @Result(column="updater", property="updater", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_deleted", property="isDeleted", jdbcType=JdbcType.TINYINT)
    })
    UserAccountBookDO selectByPrimaryKey(Long id);

    @UpdateProvider(type=UserAccountBookDOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") UserAccountBookDO record, @Param("example") UserAccountBookDOExample example);

    @UpdateProvider(type=UserAccountBookDOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") UserAccountBookDO record, @Param("example") UserAccountBookDOExample example);

    @UpdateProvider(type=UserAccountBookDOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserAccountBookDO record);

    @Update({
        "update user_account_book",
        "set uid = #{uid,jdbcType=BIGINT},",
          "name = #{name,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=TINYINT},",
          "creator = #{creator,jdbcType=BIGINT},",
          "updater = #{updater,jdbcType=BIGINT},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "is_deleted = #{isDeleted,jdbcType=TINYINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(UserAccountBookDO record);
}