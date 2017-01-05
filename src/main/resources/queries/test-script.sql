select * from workitem where orderID = "2";
select * from rph_workitem_mapping where orderId = "2";


delete from rph_workitem_mapping;
delete from lineitems;
delete from workitem;
commit;