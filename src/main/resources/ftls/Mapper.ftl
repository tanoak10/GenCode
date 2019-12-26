<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${BasePackageName}${DaoPackageName}.${ClassName}Dao">

    <resultMap id="${EntityName}ResultMap" type="${BasePackageName}${EntityPackageName}.${ClassName}">
        ${ResultMap}
    </resultMap>

    <sql id="${EntityName}Columns">
        ${ColumnMap}
    </sql>

    <select id="selectByPrimaryKey" resultMap="${EntityName}ResultMap">
        SELECT
        <include refid="${EntityName}Columns" />
        FROM ${TableName}
        <where>
        ${TableName}.${PrimaryKey} = ${Id}
        </where>
    </select>

    <select id="selectList" resultMap="${EntityName}ResultMap">
        SELECT
        <include refid="${EntityName}Columns" />
        FROM ${TableName}
        <where>
            <#-- AND ${TableName}.name LIKE concat('%',#{name},'%')-->
<#--            limit ${startPage},${size}-->
        </where>
    </select>

    <insert id="insert">
        INSERT INTO ${TableName}(
            ${InsertProperties}
        )
        VALUES (
            ${InsertValues}
        )
    </insert>

    <insert id="insertBatch">
        INSERT INTO ${TableName}(
            ${InsertProperties}
        )
        VALUES
        <foreach collection ="list" item="${EntityName}" separator =",">
        (
            ${InsertBatchValues}
        )
        </foreach>
    </insert>

    <update id="updateByPrimaryKey">
        UPDATE ${TableName} SET
        ${UpdateProperties}
        WHERE ${PrimaryKey} = ${WhereId}
    </update>

    <update id="deleteByPrimaryKey">
        DELETE FROM ${TableName}
        WHERE ${PrimaryKey} = ${Id}
    </update>

</mapper>