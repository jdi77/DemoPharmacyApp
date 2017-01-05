insert into pharmacy values ("1", "1", "NH");
insert into pharmacy values ("2", "2", "CO");
insert into pharmacy values ("3", "3", "CO");
insert into pharmacy values ("4", "4", "NH");
commit;
select * from pharmacy;

insert into pharmacist values ("1", "1", "NH", "xyz_c", "1");
insert into pharmacist values ("2", "2", "NH", "abc", "1");
insert into pharmacist values ("3", "3", "CO", "xyz_c", "2");
insert into pharmacist values ("4", "4", "CO", "abc", "2");
insert into pharmacist values ("5", "5", "CO", "ghs", "3");
insert into pharmacist values ("6", "6", "CO", "abc", "3");
insert into pharmacist values ("7", "7", "NH", "pog", "4");
insert into pharmacist values ("8", "8", "NH", "xyz_c", "4");
commit;
select * from pharmacist;