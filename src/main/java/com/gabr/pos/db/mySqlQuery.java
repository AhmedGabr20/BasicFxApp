package com.gabr.pos.db;

public class mySqlQuery {

    // USER QUERY
    // ------------------------------------------------------------------------------------------

    // SQL query to fetch user data by userId
    public static final String SELECT_USER_BY_ID =
            "SELECT user.name, user.role FROM user WHERE user.id = ?";

    // SQL to retrieve user sales in user profile
    public static final String RETRIEVE_USER_PROFILE_DATA = "select sales.id , sales.date , sales.price ,user.name as username from sales "
            + ", user where sales.user_id= user.id AND user.id=? AND date between ? AND ? order by date";

    public static final String CHECK_USER_EXIST = "select id , password  from user "
            + "where phone =? ";

    public static final String INSERT_USER = "INSERT INTO `user`(`name`, `phone`, `password`, `role`) VALUES (?,?,?,?)";

    public static final String UPDATE_USER =  "UPDATE `user` SET "
            + "`name`=?,"
            + "`phone`=?,"
            + "`password`=?,"
            + "`role`= ? WHERE id = '" ;

    // ITEM QUERY
    // ------------------------------------------------------------------------------------------
    public static final String INSERT_PRODUCT = "INSERT INTO `items`(`name`, `code`, `amount`, `price`, `priceforcustomer` ,`storeCode`) VALUES (?,?,?,?,?,?)";

    public static final String UPDATE_PRODUCT = "UPDATE `items` SET "
            + "`name`=?,"
            + "`code`=?,"
            + "`amount`=?,"
            + "`price`= ?,"
            + "`priceforcustomer`= ?,"
            + "`storeCode`= ? WHERE id = '" ;

    public static final String RETRIEVE_STORE_NAMES = "SELECT storeName FROM `store`";

    public static final String RETRIEVE_STORE_BY_STORENAME = "SELECT id FROM `store` where storeName =?";
}
