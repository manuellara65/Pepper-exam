# Pepper-exam
Customer Exam for Pepper

-user interface to show/add/update/delete customer <br>
[domain]/exam/

-show all customer<br>
[domain]/exam/customer/show-all

-delete customer<br>
/exam/customer/remove?customer_id=[value]

-add customer<br>
[domain]/exam/customer/add?firstname=value&lastname=value&address=[value]

-update customer<br>
[domain]/exam/customer/update?lastname=[value]&firstname=[value]&address=[value]&id_customer=[value]

To support the future resources such as order and products you may change the "customer" to "order/product" in the rest api.
