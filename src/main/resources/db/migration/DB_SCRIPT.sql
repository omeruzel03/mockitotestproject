CREATE TABLE ASSIGNMENT.DEPARTMENT (
    ID INTEGER PRIMARY KEY,
    NAME VARCHAR(50)
);

CREATE SEQUENCE ASSIGNMENT.DEPARTMENT_SEQ START WITH 1 INCREMENT BY 1 OWNED BY ASSIGNMENT.DEPARTMENT.ID;

INSERT INTO ASSIGNMENT.DEPARTMENT VALUES (NEXTVAL('ASSIGNMENT.DEPARTMENT_SEQ'),'Bilgisayar Mühendisliği');
INSERT INTO ASSIGNMENT.DEPARTMENT VALUES (NEXTVAL('ASSIGNMENT.DEPARTMENT_SEQ'),'Elektronik ve Haberleşme Mühendisliği');
INSERT INTO ASSIGNMENT.DEPARTMENT VALUES (NEXTVAL('ASSIGNMENT.DEPARTMENT_SEQ'),'Endüstri Mühendisliği');
INSERT INTO ASSIGNMENT.DEPARTMENT VALUES (NEXTVAL('ASSIGNMENT.DEPARTMENT_SEQ'),'Makine Mühendisliği');
INSERT INTO ASSIGNMENT.DEPARTMENT VALUES (NEXTVAL('ASSIGNMENT.DEPARTMENT_SEQ'),'Mekatronik Mühendisliği');

CREATE TABLE ASSIGNMENT.LECTURE (
    ID INTEGER PRIMARY KEY,
    CODE VARCHAR(8),
    NAME VARCHAR(50),
    QUOTA INTEGER CHECK ( QUOTA > 0 ) DEFAULT 1,
    PASS_GRADE INTEGER CHECK ( PASS_GRADE >= 0 AND PASS_GRADE <= 100) DEFAULT 50,
    CONSTRAINT u_code UNIQUE (CODE)
);

CREATE SEQUENCE ASSIGNMENT.LECTURE_SEQ START WITH 1 INCREMENT BY 1 OWNED BY ASSIGNMENT.LECTURE.ID;

INSERT INTO ASSIGNMENT.LECTURE VALUES (NEXTVAL('ASSIGNMENT.LECTURE_SEQ'),'CENG501','İleri işletim Sistemleri');
INSERT INTO ASSIGNMENT.LECTURE VALUES (NEXTVAL('ASSIGNMENT.LECTURE_SEQ'),'CENG502','İleri Bilgisayar Ağları ve Haberleşme');
INSERT INTO ASSIGNMENT.LECTURE VALUES (NEXTVAL('ASSIGNMENT.LECTURE_SEQ'),'CENG503','İleri Görüntü İşleme');
INSERT INTO ASSIGNMENT.LECTURE VALUES (NEXTVAL('ASSIGNMENT.LECTURE_SEQ'),'CENG504','Yapay Zeka');
INSERT INTO ASSIGNMENT.LECTURE VALUES (NEXTVAL('ASSIGNMENT.LECTURE_SEQ'),'CENG505','İleri Algoritmalar');
INSERT INTO ASSIGNMENT.LECTURE VALUES (NEXTVAL('ASSIGNMENT.LECTURE_SEQ'),'CENG506','Yazılım Kalite Güvencesi ve Testi');
INSERT INTO ASSIGNMENT.LECTURE VALUES (NEXTVAL('ASSIGNMENT.LECTURE_SEQ'),'CENG507','Veri Madenciliği');
INSERT INTO ASSIGNMENT.LECTURE VALUES (NEXTVAL('ASSIGNMENT.LECTURE_SEQ'),'CENG508','Coğrafi Bilgi Sistemlerine Giriş');
INSERT INTO ASSIGNMENT.LECTURE VALUES (NEXTVAL('ASSIGNMENT.LECTURE_SEQ'),'CENG509','Optimizasyon Uygulamalarında Sayısal Yöntemler');
INSERT INTO ASSIGNMENT.LECTURE VALUES (NEXTVAL('ASSIGNMENT.LECTURE_SEQ'),'CENG510','Nesne Yönelimli Analiz Ve Tasarım');
INSERT INTO ASSIGNMENT.LECTURE VALUES (NEXTVAL('ASSIGNMENT.LECTURE_SEQ'),'CENG511','İleri Bilgisayar Grafik Bilimi');
INSERT INTO ASSIGNMENT.LECTURE VALUES (NEXTVAL('ASSIGNMENT.LECTURE_SEQ'),'CENG512','İleri Nesne-Yönelimli Programlama');
INSERT INTO ASSIGNMENT.LECTURE VALUES (NEXTVAL('ASSIGNMENT.LECTURE_SEQ'),'CENG513','İleri Ağ Yönlendirme ve Anahtarlama');
INSERT INTO ASSIGNMENT.LECTURE VALUES (NEXTVAL('ASSIGNMENT.LECTURE_SEQ'),'CENG514','Uzaktan Algılama');

CREATE TABLE ASSIGNMENT.TEACHER (
    ID INTEGER PRIMARY KEY,
    NUMBER VARCHAR(9) UNIQUE,
    NAME VARCHAR(50),
    SURNAME VARCHAR(50),
    ACTIVE BOOLEAN DEFAULT FALSE
);

CREATE SEQUENCE ASSIGNMENT.TEACHER_SEQ START WITH 1 INCREMENT BY 1 OWNED BY ASSIGNMENT.TEACHER.ID;

INSERT INTO ASSIGNMENT.TEACHER VALUES (NEXTVAL('ASSIGNMENT.TEACHER_SEQ'),'900100001','Ahmet','Taner',true);
INSERT INTO ASSIGNMENT.TEACHER VALUES (NEXTVAL('ASSIGNMENT.TEACHER_SEQ'),'900100002','Can','Yürek',true);
INSERT INTO ASSIGNMENT.TEACHER VALUES (NEXTVAL('ASSIGNMENT.TEACHER_SEQ'),'900100003','Meral','Demir Acar',true);
INSERT INTO ASSIGNMENT.TEACHER VALUES (NEXTVAL('ASSIGNMENT.TEACHER_SEQ'),'900100004','Sezer','Can',true);
INSERT INTO ASSIGNMENT.TEACHER VALUES (NEXTVAL('ASSIGNMENT.TEACHER_SEQ'),'900100005','Mehmet','Demir',false);
INSERT INTO ASSIGNMENT.TEACHER VALUES (NEXTVAL('ASSIGNMENT.TEACHER_SEQ'),'900100006','Kemal','Ata',true);
INSERT INTO ASSIGNMENT.TEACHER VALUES (NEXTVAL('ASSIGNMENT.TEACHER_SEQ'),'900100007','Eda','Sezgiler',true);
INSERT INTO ASSIGNMENT.TEACHER VALUES (NEXTVAL('ASSIGNMENT.TEACHER_SEQ'),'900100008','Sevda','Sever',false);
INSERT INTO ASSIGNMENT.TEACHER VALUES (NEXTVAL('ASSIGNMENT.TEACHER_SEQ'),'900100009','Demir','Fes',true);
INSERT INTO ASSIGNMENT.TEACHER VALUES (NEXTVAL('ASSIGNMENT.TEACHER_SEQ'),'900100010','Abdullah','berden',true);

CREATE TABLE ASSIGNMENT.TEACHER_LECTURE (
    ID INTEGER PRIMARY KEY,
    TEACHER_ID INTEGER REFERENCES ASSIGNMENT.TEACHER(ID),
    LECTURE_ID INTEGER REFERENCES ASSIGNMENT.LECTURE(ID)
);

CREATE SEQUENCE ASSIGNMENT.TEACHER_LECTURE_SEQ START WITH 1 INCREMENT BY 1 OWNED BY ASSIGNMENT.TEACHER_LECTURE.ID;

INSERT INTO ASSIGNMENT.TEACHER_LECTURE VALUES (NEXTVAL('ASSIGNMENT.TEACHER_LECTURE_SEQ'),1,1);
INSERT INTO ASSIGNMENT.TEACHER_LECTURE VALUES (NEXTVAL('ASSIGNMENT.TEACHER_LECTURE_SEQ'),2,2);
INSERT INTO ASSIGNMENT.TEACHER_LECTURE VALUES (NEXTVAL('ASSIGNMENT.TEACHER_LECTURE_SEQ'),3,3);
INSERT INTO ASSIGNMENT.TEACHER_LECTURE VALUES (NEXTVAL('ASSIGNMENT.TEACHER_LECTURE_SEQ'),3,4);
INSERT INTO ASSIGNMENT.TEACHER_LECTURE VALUES (NEXTVAL('ASSIGNMENT.TEACHER_LECTURE_SEQ'),4,5);
INSERT INTO ASSIGNMENT.TEACHER_LECTURE VALUES (NEXTVAL('ASSIGNMENT.TEACHER_LECTURE_SEQ'),6,6);
INSERT INTO ASSIGNMENT.TEACHER_LECTURE VALUES (NEXTVAL('ASSIGNMENT.TEACHER_LECTURE_SEQ'),6,7);
INSERT INTO ASSIGNMENT.TEACHER_LECTURE VALUES (NEXTVAL('ASSIGNMENT.TEACHER_LECTURE_SEQ'),7,8);
INSERT INTO ASSIGNMENT.TEACHER_LECTURE VALUES (NEXTVAL('ASSIGNMENT.TEACHER_LECTURE_SEQ'),9,9);
INSERT INTO ASSIGNMENT.TEACHER_LECTURE VALUES (NEXTVAL('ASSIGNMENT.TEACHER_LECTURE_SEQ'),9,10);
INSERT INTO ASSIGNMENT.TEACHER_LECTURE VALUES (NEXTVAL('ASSIGNMENT.TEACHER_LECTURE_SEQ'),9,11);
INSERT INTO ASSIGNMENT.TEACHER_LECTURE VALUES (NEXTVAL('ASSIGNMENT.TEACHER_LECTURE_SEQ'),10,12);
INSERT INTO ASSIGNMENT.TEACHER_LECTURE VALUES (NEXTVAL('ASSIGNMENT.TEACHER_LECTURE_SEQ'),10,13);
INSERT INTO ASSIGNMENT.TEACHER_LECTURE VALUES (NEXTVAL('ASSIGNMENT.TEACHER_LECTURE_SEQ'),10,14);

CREATE TABLE ASSIGNMENT.STUDENT (
    ID INTEGER PRIMARY KEY,
    NUMBER VARCHAR(9),
    NAME VARCHAR(50),
    SURNAME VARCHAR(50),
    GRADUATED BOOLEAN DEFAULT FALSE,
    CONSTRAINT u_student_number UNIQUE(NUMBER)
);

CREATE SEQUENCE ASSIGNMENT.STUDENT_SEQ START WITH 1 INCREMENT BY 1 OWNED BY ASSIGNMENT.STUDENT.ID;


CREATE TABLE ASSIGNMENT.STUDENT_LECTURE (
    ID INTEGER PRIMARY KEY,
    STUDENT_ID INTEGER REFERENCES ASSIGNMENT.STUDENT(ID),
    LECTURE_ID INTEGER REFERENCES ASSIGNMENT.LECTURE(ID),
    GRADE INTEGER,
    CONSTRAINT SID_LID_UNIQUE UNIQUE(STUDENT_ID, LECTURE_ID)
);

CREATE SEQUENCE ASSIGNMENT.STUDENT_LECTURE_SEQ START WITH 1 INCREMENT BY 1 OWNED BY ASSIGNMENT.STUDENT_LECTURE.ID;

INSERT INTO ASSIGNMENT.STUDENT VALUES (NEXTVAL('ASSIGNMENT.STUDENT_LECTURE_SEQ'), '202271001', 'Atalay', 'Özcan', FALSE);
INSERT INTO ASSIGNMENT.STUDENT VALUES (NEXTVAL('ASSIGNMENT.STUDENT_LECTURE_SEQ'), '202271002', 'Filiz', 'Aytekin', FALSE);
INSERT INTO ASSIGNMENT.STUDENT VALUES (NEXTVAL('ASSIGNMENT.STUDENT_LECTURE_SEQ'), '202271003', 'Ahmet', 'Can', TRUE);
INSERT INTO ASSIGNMENT.STUDENT VALUES (NEXTVAL('ASSIGNMENT.STUDENT_LECTURE_SEQ'), '202271004', 'Veli', 'Orhan', TRUE);


CREATE TABLE ASSIGNMENT.STUDENT_INFORMATION (
    ID INTEGER PRIMARY KEY,
    DEPARTMENT_ID INTEGER REFERENCES ASSIGNMENT.DEPARTMENT(ID),
    Student_ID INTEGER REFERENCES ASSIGNMENT.STUDENT(ID),
    RECORD_DATE TIMESTAMP
);

CREATE SEQUENCE ASSIGNMENT.STUDENT_INFORMATION_SEQ START WITH 1 INCREMENT BY 1 OWNED BY ASSIGNMENT.STUDENT_INFORMATION.ID;
