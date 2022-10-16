package com.littlefox.area.enums;

/**
 * 角色类型/用户类型/菜单类型/用户群体
 */
public enum RoleType {

    /**
     * 超级管理员(系统管理员)
     */
    admin,
    /**
     * 领导
     */
    leader,
    /**
     * 学校管理员
     */
    school,
    /**
     * 教师
     */
    teacher,
    /**
     * 家长
     */
    parent,
    /**
     * 学生
     */
    student;


    public static RoleType resolve(String name) {
        for (RoleType enumElem : RoleType.values()) {
            if (enumElem.name().equals(name)) {
                return enumElem;
            }
        }
        return null;

    }
}
