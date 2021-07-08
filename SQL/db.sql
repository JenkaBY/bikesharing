CREATE TABLE "equipment_group" (
                                   "id" serial NOT NULL,
                                   "code" character varying NOT NULL UNIQUE,
                                   "name" character varying NOT NULL,
                                   CONSTRAINT "equipment_group_pk" PRIMARY KEY ("id")
) WITH (
      OIDS=FALSE
      );



CREATE TABLE "equipment_status" (
                                    "id" serial NOT NULL,
                                    "code" character varying NOT NULL UNIQUE,
                                    "name" character varying NOT NULL,
                                    CONSTRAINT "equipment_status_pk" PRIMARY KEY ("id")
) WITH (
      OIDS=FALSE
      );



CREATE TABLE "equipment_item" (
                                  "id" serial NOT NULL,
                                  "registration_number" character varying NOT NULL UNIQUE,
                                  "name" character varying NOT NULL,
                                  "equipment_group_id" int NOT NULL,
                                  "equipment_status_id" int NOT NULL,
                                  "factory_number" character varying NOT NULL,
                                  "purchase_date" DATE NOT NULL,
                                  "comments" character varying,
                                  "updated" TIMESTAMP NOT NULL,
                                  "created" TIMESTAMP NOT NULL,
                                  CONSTRAINT "equipment_item_pk" PRIMARY KEY ("id")
) WITH (
      OIDS=FALSE
      );



CREATE TABLE "service_type" (
                                "id" serial NOT NULL,
                                "code" character varying NOT NULL UNIQUE,
                                "name" character varying NOT NULL,
                                CONSTRAINT "service_type_pk" PRIMARY KEY ("id")
) WITH (
      OIDS=FALSE
      );



CREATE TABLE "service_operation" (
                                     "id" serial NOT NULL,
                                     "updated" TIMESTAMP NOT NULL,
                                     "created" TIMESTAMP NOT NULL,
                                     "service_type_id" int NOT NULL,
                                     "issue_description" character varying NOT NULL,
                                     "start_date" DATE NOT NULL,
                                     "end_date" DATE,
                                     "comments" character varying,
                                     "equipment_item_id" int NOT NULL,
                                     CONSTRAINT "service_operation_pk" PRIMARY KEY ("id")
) WITH (
      OIDS=FALSE
      );



CREATE TABLE "client_account" (
                                  "id" serial NOT NULL,
                                  "phone_number" character varying NOT NULL UNIQUE,
                                  "name" character varying NOT NULL,
                                  "updated" TIMESTAMP NOT NULL,
                                  "created" TIMESTAMP NOT NULL,
                                  "address" character varying NOT NULL,
                                  "rating" int NOT NULL,
                                  "comments" character varying,
                                  CONSTRAINT "client_account_pk" PRIMARY KEY ("id")
) WITH (
      OIDS=FALSE
      );



CREATE TABLE "rent_operation" (
                                  "id" serial NOT NULL,
                                  "equipment_item_id" int NOT NULL,
                                  "client_account_id" int NOT NULL,
                                  "start_time" TIMESTAMP NOT NULL,
                                  "end_time" TIMESTAMP NOT NULL,
                                  "total_cost" bigint NOT NULL,
                                  "deposit" bigint NOT NULL,
                                  "comments" character varying,
                                  "updated" TIMESTAMP NOT NULL,
                                  "created" TIMESTAMP NOT NULL,
                                  "rent_status_id" int NOT NULL,
                                  CONSTRAINT "rent_operation_pk" PRIMARY KEY ("id")
) WITH (
      OIDS=FALSE
      );



CREATE TABLE "rent_status" (
                               "id" serial NOT NULL,
                               "code" character varying NOT NULL UNIQUE,
                               "name" character varying NOT NULL,
                               CONSTRAINT "rent_status_pk" PRIMARY KEY ("id")
) WITH (
      OIDS=FALSE
      );





ALTER TABLE "equipment_item" ADD CONSTRAINT "equipment_item_fk0" FOREIGN KEY ("equipment_group_id") REFERENCES "equipment_group"("id");
ALTER TABLE "equipment_item" ADD CONSTRAINT "equipment_item_fk1" FOREIGN KEY ("equipment_status_id") REFERENCES "equipment_status"("id");


ALTER TABLE "service_operation" ADD CONSTRAINT "service_operation_fk0" FOREIGN KEY ("service_type_id") REFERENCES "service_type"("id");
ALTER TABLE "service_operation" ADD CONSTRAINT "service_operation_fk1" FOREIGN KEY ("equipment_item_id") REFERENCES "equipment_item"("id");


ALTER TABLE "rent_operation" ADD CONSTRAINT "rent_operation_fk0" FOREIGN KEY ("equipment_item_id") REFERENCES "equipment_item"("id");
ALTER TABLE "rent_operation" ADD CONSTRAINT "rent_operation_fk1" FOREIGN KEY ("client_account_id") REFERENCES "client_account"("id");
ALTER TABLE "rent_operation" ADD CONSTRAINT "rent_operation_fk2" FOREIGN KEY ("rent_status_id") REFERENCES "rent_status"("id");