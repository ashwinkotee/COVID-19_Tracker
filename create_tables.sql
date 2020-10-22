CREATE TABLE global_data(
  import_date DATE NOT NULL,
  today_confirmed NUMBER,
  today_deaths NUMBER,
  today_new_confirmed NUMBER,
  today_new_deaths NUMBER,
  today_new_open_cases NUMBER,
  today_new_recovered NUMBER,
  today_open_cases NUMBER,
  today_recovered NUMBER,
  today_vs_yesterday_confirmed NUMBER,
  today_vs_yesterday_deaths NUMBER,
  today_vs_yesterday_open_cases NUMBER,
  today_vs_yesterday_recovered NUMBER,
  yesterday_confirmed NUMBER,
  yesterday_deaths NUMBER,
  yesterday_open_cases NUMBER,
  yesterday_recovered NUMBER,
);

CREATE TABLE country_data(
  import_date date NOT NULL,
  country_id NUMBER NOT NULL,
  country VARCHAR2,
  country_es VARCHAR2,
  country_it VARCHAR2,
  today_confirmed NUMBER,
  today_deaths NUMBER,
  today_new_confirmed NUMBER,
  today_new_deaths NUMBER,
  today_new_open_cases NUMBER,
  today_new_recovered NUMBER,
  today_open_cases NUMBER,
  today_recovered NUMBER,
  today_vs_yesterday_confirmed NUMBER,
  today_vs_yesterday_deaths NUMBER,
  today_vs_yesterday_open_cases NUMBER,
  today_vs_yesterday_recovered NUMBER,
  yesterday_confirmed NUMBER,
  yesterday_deaths NUMBER,
  yesterday_open_cases NUMBER,
  yesterday_recovered NUMBER,
  CONSTRAINT country_pk PRIMARY KEY (country_id)
);

CREATE TABLE regional_data(
  import_date DATE NOT NULL,
  region_id NUMBER NOT NULL,
  country_id NUMBER NOT NULL,
  region_name VARCHAR2 NOT NULL,
  today_confirmed NUMBER,
  today_deaths NUMBER,
  today_new_confirmed NUMBER,
  today_new_deaths NUMBER,
  today_new_open_cases NUMBER,
  today_new_recovered NUMBER,
  today_open_cases NUMBER,
  today_recovered NUMBER,
  today_vs_yesterday_confirmed NUMBER,
  today_vs_yesterday_deaths NUMBER,
  today_vs_yesterday_open_cases NUMBER,
  today_vs_yesterday_recovered NUMBER,
  yesterday_confirmed NUMBER,
  yesterday_deaths NUMBER,
  yesterday_open_cases NUMBER,
  yesterday_recovered NUMBER,
  CONSTRAINT region_pk PRIMARY KEY (region_id),
  CONSTRAINT fk_country
    FOREIGN KEY (country_id)
    REFERENCES country_data(country_id)
);

CREATE TABLE subregional_data(
  import_date DATE NOT NULL,
  subregion_id NUMBER NOT NULL,
  region_id NUMBER NOT NULL,
  subregion_name VARCHAR2 NOT NULL,
  today_confirmed NUMBER,
  today_deaths NUMBER,
  today_new_confirmed NUMBER,
  today_new_deaths NUMBER,
  today_new_open_cases NUMBER,
  today_new_recovered NUMBER,
  today_open_cases NUMBER,
  today_recovered NUMBER,
  today_vs_yesterday_confirmed NUMBER,
  today_vs_yesterday_deaths NUMBER,
  today_vs_yesterday_open_cases NUMBER,
  today_vs_yesterday_recovered NUMBER,
  yesterday_confirmed NUMBER,
  yesterday_deaths NUMBER,
  yesterday_open_cases NUMBER,
  yesterday_recovered NUMBER,
  CONSTRAINT fk_region
    FOREIGN KEY (region_id)
    REFERENCES regional_data(region_id)
);

