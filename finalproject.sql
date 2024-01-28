--
-- PostgreSQL database dump
--

-- Dumped from database version 16.0
-- Dumped by pg_dump version 16.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: admin; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.admin (
    username character varying(50) NOT NULL,
    password character varying,
    fullname character varying(50)
);


ALTER TABLE public.admin OWNER TO postgres;

--
-- Name: score; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.score (
    id integer NOT NULL,
    score1 double precision NOT NULL,
    score2 double precision NOT NULL,
    score3 double precision NOT NULL
);


ALTER TABLE public.score OWNER TO postgres;

--
-- Name: student; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.student (
    id integer NOT NULL,
    full_name character varying(255) NOT NULL,
    date_of_birth date NOT NULL,
    gender character varying(10) NOT NULL,
    phone_number character varying(20),
    address character varying(255)
);


ALTER TABLE public.student OWNER TO postgres;

--
-- Name: studentdataview; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.studentdataview AS
 SELECT student.id,
    student.full_name,
    student.date_of_birth,
    student.gender,
    student.phone_number,
    student.address,
    score.score1,
    score.score2,
    score.score3
   FROM (public.student
     JOIN public.score ON ((student.id = score.id)));


ALTER VIEW public.studentdataview OWNER TO postgres;

--
-- Data for Name: admin; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.admin (username, password, fullname) FROM stdin;
admin	123456789	Trần Hoàng Nhật
\.


--
-- Data for Name: score; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.score (id, score1, score2, score3) FROM stdin;
512	8	7.5	10
143	8	7	8.75
957	7	7.75	9
48	6	5	6.75
434	10	5	3.5
382	10	6	8.5
348	10	8	8.5
123	10	9.5	10
789	8.5	10	10
567	10	10	9.75
875	10	8.75	9.25
267	8.6	8	7.5
984	8.5	9	8
\.


--
-- Data for Name: student; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.student (id, full_name, date_of_birth, gender, phone_number, address) FROM stdin;
512	Trần Thị Bình	2006-02-10	Female	0923456789	456 Đường Hai, Hà Nội
143	Lê Minh Cường	2006-03-14	Male	0723456789	324 Đường Ba, Hà Nội
957	Nguyễn Thị Phương	2006-06-09	Female	0955578901	846 Đường Sáu, Đà Nẵng
48	Võ Minh Giang	2006-07-08	Female	0956578901	923 Đường Bảy, Đà Nẵng
434	Bùi Thị Hải Yến	2006-07-08	Female	0956579901	827 Đường Tám, Đà Nẵng
382	Lê Văn Khôi	2006-08-12	Male	0956379901	284 Đường Chín, Huế
348	Phan Văn Minh	2006-08-09	Male	0956379991	093 Đường Mười, Huế
123	Đặng Văn Sơn	2006-08-15	Female	0755559391	385 Đường Tăng, Quảng Nam
789	Đỗ Thị Nga	2006-09-14	Female	0955559391	234 Đường Sá, Huế
567	Phạm Thị Thuận	2006-10-12	Female	0355559391	50 Đường Phèn, Quảng Nam
875	Phạm Thị Dung	2006-03-03	Female	0345678901	321 Đường Bốn, Nha Trang
267	Nguyễn Hữu Nhân	2024-07-26	Male	0379291578	Quảng Nam
984	Hoàng Văn Hậu	2006-04-14	Female	0955678901	321 Đường Năm, Đà Nẵng
\.


--
-- Name: admin admin_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.admin
    ADD CONSTRAINT admin_pkey PRIMARY KEY (username);


--
-- Name: score score_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.score
    ADD CONSTRAINT score_pkey PRIMARY KEY (id);


--
-- Name: student student_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT student_pkey PRIMARY KEY (id);


--
-- Name: score score_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.score
    ADD CONSTRAINT score_id_fkey FOREIGN KEY (id) REFERENCES public.student(id);


--
-- PostgreSQL database dump complete
--

