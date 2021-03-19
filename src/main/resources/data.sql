-- INSERT GUESTS
insert into guest 	(id, 	checkindate, 	name, 			details, 				ischeckedin)
values 				(1L, 	now(), 			'owner01', 		'Owner Details', 		true);

insert into guest 	(id, 	checkindate, 	name, 			details, 				ischeckedin)
values 				(2L, 	now(), 			'owner02', 		'Owner Details', 		true);

-- INSERT PARCELS FOR GUESTS WITH OWNER <= GUEST NAME

-- GUEST 1 PARCELS
insert into parcel (id, 	receivedate, 	code, 			ispicked,	owner) 
values 				(101L, 	now()-1, 		'CODE111111', 	false,		'owner01'		);

insert into parcel (id, 	receivedate, 	code, 			ispicked,	owner) 
values 				(102L, 	now()-1, 		'CODE121212', 	false,		'owner01'		);

insert into parcel (id, 	receivedate, 	code, 			ispicked,	owner) 
values 				(103L, 	now()-1, 		'CODE332211', 	false,		'owner01'		);

-- GUEST 2 PARCELS
insert into parcel (id, 	receivedate, 	code, 			ispicked,	owner) 
values 				(201L, 	now()-1, 		'CODE111201', 	false,		'owner02'		);

insert into parcel (id, 	receivedate, 	code, 			ispicked,	owner) 
values 				(202L, 	now()-1, 		'CODE121202', 	false,		'owner02'		);

insert into parcel (id, 	receivedate, 	code, 			ispicked,	owner) 
values 				(203L, 	now()-1, 		'CODE332203', 	false,		'owner02'		);
 




--UPDATE FOR PARCELS WITH GUESTS

--update parcel set owner = 'owner01' where id = 	101;
 
--update parcel set owner = 'owner01' where id =  102;
 
--update parcel set owner = 'owner01' where id =  103;