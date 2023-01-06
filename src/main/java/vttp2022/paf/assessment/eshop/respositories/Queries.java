package vttp2022.paf.assessment.eshop.respositories;


public class Queries {

    public static final String SQL_SELECT_CUSTOMER_BY_NAME = "select * from customers where name = ?";

    public static final String SQL_INSERT_LINE_ITEM = "insert into line_item(item_name, quantity, order_id) values (?, ?, ?)";

    public static final String SQL_INSERT_ORDER = 
        "insert into orders(order_id, delivery_id, name, address, email, status) values (?,?,?,?,?,?)";

    public static final String SQL_SELECT_CUSTOMER_BY_NAME_IN_ORDERS = "select * from orders where name = ?";
    
}
