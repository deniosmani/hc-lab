insert  into `table_exam_priority`(`value`) values 
('asap'),
('callback results'),
('emergency'),
('routine'),
('rush reporting'),
('timing critical');

insert  into `table_exam_status`(`value`) values 
('cancelled'),
('entered-in-error'),
('finished'),
('in-progress'),
('planned'),
('suspended'),
('triaged');

insert  into `table_service_type`(`value`) values 
('Acupuncture'),
('Aged Care Assessment'),
('Aged Residential Care'),
('Blood Donation'),
('Bown Therapy'),
('Dental'),
('Dermatology'),
('Emergency Medical'),
('Endodontic'),
('Family Planning'),
('Home Care/Housekeeping Assistance'),
('Immunizatio'),
('Optometry'),
('Oral Surgery'),
('Osteopathy'),
('Physiotherapy'),
('Podiatry'),
('Psychology');

insert  into `table_qualification`(`value`) values 
('Cerified Nurse Midwife'),
('Doctor of Medicine'),
('Doctor of Pharmacy'),
('Emergency Medical Technician'),
('Medical Assistant'),
('Nurse Practitioner');

insert  into `table_marital_status`(`value`) values 
('Annulled'),
('Divorced'),
('Married'),
('Never Married'),
('Polygamous'),
('Widowed');

insert  into `table_gender`(`value`) values 
('female'),
('male'),
('other'),
('unknown');

insert  into `table_organization_type`(`value`) values 
('Clinical Research'),
('Educational Institute'),
('Hospital'),
('Insurance Company'),
('Other');

insert  into `table_organization`(`id`,`active`,`address`,`email`,`name`,`phone`,`type`) values 
(1,'','Belgrade','bellab@gmail.com','Bellab','0643331323','Hospital'),
(2,'','Novi Sad','mediagroup@gmail.com','MediaGroup','065214124','Educational Institute'),
(3,'','Nis','telmed@gmail.com','TelMed','062341434','Clinical Research'),
(4,'','Zrenjanin','ivanovic@gmail.com','IvanovicMed','062442144','Insurance Company'),
(5,'','Rome','medical@gmail.com','Medical','039645823','Hospital'),
(6,'','Torino','englab@gmail.com','EngLab','039142453','Educational Institute'),
(7,'','Palermo','palermoinstitute@gmail.com','PalermoInstitute','039214424','Clinical Research'),
(8,'','Milano','milanohealth@gmail.com','MilanoHealth','0391283241','Insurance Company');

insert  into `table_practitioner`(`id`,`active`,`address`,`birth_date`,`email`,`name`,`phone`,`surname`,`gender_value`,`organization_id`,`qualification_value`) values 
(1,'','Belgrade','2022-06-01','marko@gmail.com','Marko','0634321421','Markovic','male',1,'Doctor of Medicine'),
(2,'','Belgrade','2022-06-01','jovana@gmail.com','Jovana','064512552','Jovanovic','female',1,'Cerified Nurse Midwife'),
(3,'','Belgrade','2022-06-01','jovan@gmail.com','Jovan','061431242','Jovic','male',NULL,'Doctor of Medicine'),
(4,'','Novi Sad','2022-06-01','zvonko@gmail.com','Zvonko','061242143','Bogdan','male',2,'Doctor of Medicine'),
(5,'','Novi Sad','2022-06-01','nikola@gmail.com','Nikola','062421842','Nikolic','male',2,'Emergency Medical Technician'),
(6,'','Novi Sad','2022-06-01','emilija@gmail.com','Emilija','061253142','Prodanov','female',2,'Medical Assistant'),
(7,'','Nis','2022-06-01','denis@gmail.com','Denis','061234242','Mihajlovic','male',3,'Doctor of Medicine'),
(8,'','Nis','2022-06-05','andrijana@gmail.com','Andrijana','061242042','Ristic','female',3,'Nurse Practitioner'),
(9,'','Nis','2022-06-01','jelena@gmail.com','Jelena','061242432','Saponjic','female',3,'Doctor of Medicine'),
(10,'','Zrenjanin','2022-06-01','nemanja@gmail.com','Nemanja','06341342','Janic','male',4,'Doctor of Medicine'),
(11,'','Zrenjanin','2022-06-01','marica@gmail.com','Marica','061535131','Maric','female',4,'Cerified Nurse Midwife'),
(12,'','Zrenjanin','2022-06-01','jovica@gmail.com','Jovica','062312311','Djurisic','male',4,'Doctor of Medicine'),
(13,'','Rome','2022-06-13','allesandro@gmail.com','Alessandro','0394654263','Rossi','male',5,'Doctor of Medicine'),
(14,'','Rome','2022-06-01','lorenzo@gmail.com','Lorenzo','0397854852','Russo','male',5,'Doctor of Medicine'),
(15,'','Rome','2022-06-01','diego@gmail.com','Diego','039742857','Ferrari','male',5,'Doctor of Medicine'),
(16,'','Torino','2022-06-01','tommaso@gmail.com','Tommaso','0393453453','Esposito','male',6,'Cerified Nurse Midwife'),
(17,'','Torino','2022-06-01','ricardo@gmail.com','Ricardo','0394534234','Bianchi','male',6,'Medical Assistant'),
(18,'','Torino','2022-06-01','matteo@gmail.com','Matteo','039745456','Romano','male',6,'Doctor of Medicine'),
(19,'','Palermo','2022-06-01','lorenzo@gmail.com','Lorenzo','0398756733','Colombo','male',7,'Doctor of Pharmacy'),
(20,'','Palermo','2022-06-01','leonardo@gmail.com','Leonardo','0397527522','Ricci','male',7,'Emergency Medical Technician'),
(21,'','Palermo','2022-06-01','gabriele@gmail.com','Gabriele','0395727222','Marino','male',7,'Doctor of Medicine'),
(22,'','Milano','2022-06-01','edoardo@gmail.com','Edoardo','0397527820','Greco','male',8,'Medical Assistant'),
(23,'','Milano','2022-06-01','adriano@gmail.com','Adriano','0397532720','Bruno','male',8,'Nurse Practitioner'),
(24,'','Milano','2022-06-01','fabrizio@gmail.com','Fabrizio','0397275520','Gallo','male',8,'Doctor of Medicine'),
(26,'','Rome','2022-06-01','leonardo@gmail.com','Leonardo','03912412431','Gallo','male',NULL,'Doctor of Pharmacy'),
(27,'','Belgrade','2022-06-01','djordje@gmail.com','Djordje','06321384512','Jovanovic','male',NULL,'Doctor of Medicine'),
(28,'','Zrenjanin','2022-06-01','milica@gmail.com','Milica','06521342131','Milic','female',NULL,'Nurse Practitioner');

insert  into `table_patient`(`id`,`active`,`address`,`birth_date`,`deceased`,`email`,`name`,`phone`,`surname`,`gender_value`,`martial_status_value`,`organization`,`primary_care_provider`) values 
(1,'','Belgrade','2022-06-01','\0','nemanjanikolic@gmail.com','Nemanja','0642313233','Nikolic','male','Annulled',1,1),
(2,'','Belgrade','2022-06-01','\0','nikolanemanjic@gmail.com','Nikola','065123331','Nemanjic','male','Never Married',1,3),
(3,'','Belgrade','2022-06-01','\0','jovanajeremic@gmail.com','Jovana','063419421','Jeremic','female','Married',1,1),
(4,'','Novi Sad','2022-06-01','\0','milosmilosevic@gmail.com','Milos','0631414112','Milosevic','male','Widowed',2,4),
(5,'','Novi Sad','2022-06-01','\0','zeljkajeremic@gmail.com','Zeljka','0612340421','Jeremic','female','Divorced',2,4),
(6,'','Novi Sad','2022-06-01','\0','daliborjovanovic@gmail.com','Dalibor','0661243124','Jovanovic','male','Polygamous',2,4),
(7,'','Nis','2022-06-01','\0','zarkozarkovic@gmail.com','Zarko','063124243','Zarkovic','male','Annulled',3,9),
(8,'','Nis','2022-06-01','\0','jelicajelic@gmail.com','Jelica','0642442312','Jelic','female','Never Married',3,9),
(9,'','Nis','2022-06-01','\0','zeljkozeljkovic@gmail.com','Zeljko','061242332','Zeljkovic','male','Married',3,9),
(10,'','Zrenjanin','2022-06-01','\0','markomarkovic@gmail.com','Marko','063322344','Markovic','male','Annulled',4,10),
(11,'','Zrenjanin','2022-06-03','\0','aljovanovic@gmail.com','Aleksandra','062444231','Jovanovic','female','Never Married',3,9),
(12,'','Zrenjanin','2022-06-01','\0','fifilpovic@gmail.com','Filip','063431242','Filipovic','male','Widowed',4,10),
(13,'','Rome','2022-06-01','\0','carlorossi@gmail.com','Carlo','0394214232','Rossi','male','Divorced',5,14),
(14,'','Rome','2022-06-01','\0','corradobonucci@gmail.com','Corrado','039213823','Bonucci','male','Polygamous',5,14),
(15,'','Rome','2022-06-01','\0','cristianmax@gmail.com','Cristian','039128328','Maximilliano','male','Never Married',5,15),
(16,'','Palermo','2022-06-01','\0','giovanniabb@gmail.com','Giovani','039123123','Abbiati','male','Polygamous',7,21),
(17,'','Palermo','2022-06-01','\0','brunogallo@gmail.com','Bruno','039128323','Gallo','male','Married',7,21),
(18,'','Palermo','2022-06-01','\0','domenicogreco@gmail.com','Domenico','03921323','Greco','male','Married',7,21),
(19,'','Torino','2022-06-01','\0','felicemarino@gmail.com','Felice','03921323','Marino','male','Never Married',6,18),
(20,'','Torino','2022-06-01','\0','ernestodeluca@gmail.com','Ernesto','039213832','De Luca','male','Never Married',6,18),
(21,'','Torino','2022-06-01','\0','emilioromano@gmail.com','Emilio','039213123','Romano','male','Divorced',6,18),
(22,'','Milano','2022-06-01','\0','lucatoni@gmail.com','Luca','039312383','Toni','male','Polygamous',8,24),
(23,'','Milano','2022-06-01','\0','andreapirlo@gmail.com','Andrea ','0391233321','Pirlo','male','Married',8,24),
(24,'','Milano','2022-06-01','\0','niccolocosta@gmail.com','Niccolo','039123832','Costa','male','Annulled',8,24);

insert  into `table_examination`(`id`,`diagnosis`,`end_date`,`start_date`,`organization`,`patient`,`priority_value`,`service_type`,`status_value`) values 
(20,'Diagnosis1','2022-06-06','2022-06-01',1,13,'asap','Endodontic','entered-in-error'),
(21,'Diagnosis2','2022-05-22','2022-05-12',2,5,'emergency','Optometry','in-progress'),
(22,'Diagnosis3','2022-06-25','2022-06-16',3,11,'callback results','Family Planning','in-progress'),
(23,'Diagnosis4','2022-06-14','2022-06-01',4,17,'routine','Oral Surgery','entered-in-error'),
(24,'Diagnosis5','2022-06-24','2022-05-12',5,15,'routine','Optometry','in-progress'),
(25,'Diagnosis6','2022-06-13','2022-06-02',6,12,'rush reporting','Optometry','planned'),
(26,'Diagnosis7','2022-06-11','2022-06-02',7,18,'routine','Osteopathy','finished'),
(27,'Diagnosis8','2022-06-16','2022-06-02',8,12,'routine','Podiatry','suspended'),
(28,'Diagnosis9','2022-06-17','2022-06-01',1,15,'emergency','Physiotherapy','entered-in-error'),
(29,'Diagnosis10','2022-06-09','2022-06-01',2,16,'routine','Home Care/Housekeeping Assistance','planned'),
(30,'Diagnosis11','2022-06-10','2022-06-01',3,16,'emergency','Osteopathy','in-progress'),
(31,'Diagnosis12','2022-06-29','2022-06-18',4,10,'routine','Osteopathy','planned'),
(32,'Diagnosis13','2022-07-01','2022-06-18',5,15,'rush reporting','Physiotherapy','planned'),
(33,'Diagnosis14','2022-06-14','2022-06-09',6,14,'emergency','Home Care/Housekeeping Assistance','planned'),
(34,'Diagnosis15','2022-06-16','2022-06-01',7,14,'emergency','Physiotherapy','planned'),
(35,'Diagnosis16','2022-06-17','2022-06-08',8,7,'rush reporting','Oral Surgery','in-progress');

insert  into `table_examination_practitioners`(`examination_entity_id`,`practitioners_id`) values 
(21,4),
(21,5),
(22,8),
(23,11),
(23,10),
(24,13),
(24,14),
(26,19),
(26,20),
(27,23),
(27,22),
(20,1),
(20,2),
(20,3),
(28,1),
(28,2),
(29,4),
(30,8),
(30,9),
(31,10),
(31,11),
(32,14),
(32,13),
(33,16),
(33,17),
(34,19),
(34,20),
(35,22),
(35,23),
(25,16),
(25,17);

insert into `table_user`(`id`,`firstname`,`lastname`,`password`,`username`) values 
(1,'Mika','Mikic','mika','mika');