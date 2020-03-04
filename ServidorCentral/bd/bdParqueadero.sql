/*==============================================================*/
/* DBMS name:      PostgreSQL 9.x                               */
/* Created on:     23/10/2019 10:06:06 a. m.                    */
/*==============================================================*/


drop index ASIGNA_FK;

drop index PERTENECE_FK;

drop index BAHIA_PK;

drop table BAHIA;

drop index INGRESO3_FK;

drop index INGRESO2_FK;

drop index INGRESO_PK;

drop table INGRESO;

drop index REGISTRAR_FK;

drop index MULTA_PK;

drop table MULTA;

drop index PERSONA_PK;

drop table PERSONA;

drop index TIENE_FK;

drop index ROL_PK;

drop table ROL;

drop index VEHICULO_PK;

drop table VEHICULO;

drop index VIGILANTE_PK;

drop table VIGILANTE;

drop index ZONA_PK;

drop table ZONA;

drop domain ROLTIPO;

drop domain SEXO;

drop domain VEHTIPO;

/*==============================================================*/
/* Domain: ROLTIPO                                              */
/*==============================================================*/
create domain ROLTIPO as VARCHAR(12);

/*==============================================================*/
/* Domain: SEXO                                                 */
/*==============================================================*/
create domain SEXO as VARCHAR(10);

/*==============================================================*/
/* Domain: VEHTIPO                                              */
/*==============================================================*/
create domain VEHTIPO as VARCHAR(30);

/*==============================================================*/
/* Table: BAHIA                                                 */
/*==============================================================*/
create table BAHIA (
   BAHID                INT4                 not null,
   VIGIDENTIFICACION    INT4                 not null,
   ZONID                INT4                 not null,
   BAHESTADO            VARCHAR(1)           not null,
   constraint PK_BAHIA primary key (BAHID)
);

/*==============================================================*/
/* Index: BAHIA_PK                                              */
/*==============================================================*/
create unique index BAHIA_PK on BAHIA (
BAHID
);

/*==============================================================*/
/* Index: PERTENECE_FK                                          */
/*==============================================================*/
create  index PERTENECE_FK on BAHIA (
ZONID
);

/*==============================================================*/
/* Index: ASIGNA_FK                                             */
/*==============================================================*/
create  index ASIGNA_FK on BAHIA (
VIGIDENTIFICACION
);

/*==============================================================*/
/* Table: INGRESO                                               */
/*==============================================================*/
create table INGRESO (
   PERIDENTIFICACION    INT4                 not null,
   VEHPLACA             VARCHAR(7)           not null,
   BAHID                INT4                 not null,
   INGFECHAINGRESO      VARCHAR(11)          not null,
   INGFECHASALIDA       VARCHAR(11)          null,
   constraint PK_INGRESO primary key (PERIDENTIFICACION, VEHPLACA, BAHID)
);

/*==============================================================*/
/* Index: INGRESO_PK                                            */
/*==============================================================*/
create unique index INGRESO_PK on INGRESO (
PERIDENTIFICACION,
VEHPLACA,
BAHID
);

/*==============================================================*/
/* Index: INGRESO2_FK                                           */
/*==============================================================*/
create  index INGRESO2_FK on INGRESO (
VEHPLACA
);

/*==============================================================*/
/* Index: INGRESO3_FK                                           */
/*==============================================================*/
create  index INGRESO3_FK on INGRESO (
BAHID
);

/*==============================================================*/
/* Table: MULTA                                                 */
/*==============================================================*/
create table MULTA (
   MULID                INT4                 not null,
   VIGIDENTIFICACION    INT4                 not null,
   MULDESCRIPCION       VARCHAR(70)          not null,
   MULFECHA             VARCHAR(20)          not null,
   MULFOTO              VARCHAR(100)         not null,
   constraint PK_MULTA primary key (MULID)
);

/*==============================================================*/
/* Index: MULTA_PK                                              */
/*==============================================================*/
create unique index MULTA_PK on MULTA (
MULID
);

/*==============================================================*/
/* Index: REGISTRAR_FK                                          */
/*==============================================================*/
create  index REGISTRAR_FK on MULTA (
VIGIDENTIFICACION
);

/*==============================================================*/
/* Table: PERSONA                                               */
/*==============================================================*/
create table PERSONA (
   PERIDENTIFICACION    INT4                 not null,
   PERNOMBRE            VARCHAR(30)          not null,
   PERAPELLIDO          VARCHAR(30)          not null,
   PERGENERO            VARCHAR(30)          not null,
   PERFECHANAC          VARCHAR(30)          not null,
   constraint PK_PERSONA primary key (PERIDENTIFICACION)
);

/*==============================================================*/
/* Index: PERSONA_PK                                            */
/*==============================================================*/
create unique index PERSONA_PK on PERSONA (
PERIDENTIFICACION
);

/*==============================================================*/
/* Table: ROL                                                   */
/*==============================================================*/
create table ROL (
   ROLID                INT4                 not null,
   PERIDENTIFICACION    INT4                 not null,
   ROLNOMBRE            ROLTIPO              not null,
   constraint PK_ROL primary key (ROLID)
);

/*==============================================================*/
/* Index: ROL_PK                                                */
/*==============================================================*/
create unique index ROL_PK on ROL (
ROLID
);

/*==============================================================*/
/* Index: TIENE_FK                                              */
/*==============================================================*/
create  index TIENE_FK on ROL (
PERIDENTIFICACION
);

/*==============================================================*/
/* Table: VEHICULO                                              */
/*==============================================================*/
create table VEHICULO (
   VEHPLACA             VARCHAR(7)           not null,
   VEHMARCA             VARCHAR(30)          not null,
   VEHTIPO              VEHTIPO              not null,
   constraint PK_VEHICULO primary key (VEHPLACA)
);

/*==============================================================*/
/* Index: VEHICULO_PK                                           */
/*==============================================================*/
create unique index VEHICULO_PK on VEHICULO (
VEHPLACA
);

/*==============================================================*/
/* Table: VIGILANTE                                             */
/*==============================================================*/
create table VIGILANTE (
   VIGIDENTIFICACION    INT4                 not null,
   VIGNOMBRES           VARCHAR(30)          not null,
   VIGAPELLIDOS         VARCHAR(30)          not null,
   VIGGENERO            SEXO                 not null,
   VIGFECHANAC          VARCHAR(12)          not null,
   VIGEMPRESA           VARCHAR(50)          not null,
   VIGUSUARIO           VARCHAR(30)          not null,
   VIGCONTRASENIA       VARCHAR(15)          not null,
   constraint PK_VIGILANTE primary key (VIGIDENTIFICACION)
);

/*==============================================================*/
/* Index: VIGILANTE_PK                                          */
/*==============================================================*/
create unique index VIGILANTE_PK on VIGILANTE (
VIGIDENTIFICACION
);

/*==============================================================*/
/* Table: ZONA                                                  */
/*==============================================================*/
create table ZONA (
   ZONID                INT4                 not null,
   ZONNOMBRE            VARCHAR(20)          not null,
   ZONCANTPUESTOS       VARCHAR(2)           not null,
   constraint PK_ZONA primary key (ZONID)
);

/*==============================================================*/
/* Index: ZONA_PK                                               */
/*==============================================================*/
create unique index ZONA_PK on ZONA (
ZONID
);

alter table BAHIA
   add constraint FK_BAHIA_ASIGNA_VIGILANT foreign key (VIGIDENTIFICACION)
      references VIGILANTE (VIGIDENTIFICACION)
      on delete restrict on update restrict;

alter table BAHIA
   add constraint FK_BAHIA_PERTENECE_ZONA foreign key (ZONID)
      references ZONA (ZONID)
      on delete restrict on update restrict;

alter table INGRESO
   add constraint FK_INGRESO_INGRESO_PERSONA foreign key (PERIDENTIFICACION)
      references PERSONA (PERIDENTIFICACION)
      on delete restrict on update restrict;

alter table INGRESO
   add constraint FK_INGRESO_INGRESO2_VEHICULO foreign key (VEHPLACA)
      references VEHICULO (VEHPLACA)
      on delete restrict on update restrict;

alter table INGRESO
   add constraint FK_INGRESO_INGRESO3_BAHIA foreign key (BAHID)
      references BAHIA (BAHID)
      on delete restrict on update restrict;

alter table MULTA
   add constraint FK_MULTA_REGISTRAR_VIGILANT foreign key (VIGIDENTIFICACION)
      references VIGILANTE (VIGIDENTIFICACION)
      on delete restrict on update restrict;

alter table ROL
   add constraint FK_ROL_TIENE_PERSONA foreign key (PERIDENTIFICACION)
      references PERSONA (PERIDENTIFICACION)
      on delete restrict on update restrict;

