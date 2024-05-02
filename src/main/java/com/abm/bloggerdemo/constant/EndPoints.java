package com.abm.bloggerdemo.constant;


public class EndPoints {
    public static final String VERSION="/v1";

    //profiller:
    public static final String API="/api";
    public static final String DEV="/dev";
    public static final String TEST="/test";

    public static final String ROOT=API+VERSION;


    //entities:
    public static final String USER="/user";
    public static final String CATEGORIES="/categories";
    public static final String POST="/post";
    public static final String COMMENT="/comment";


    //methods:
    public static final String SAVE = "/save";
    public static final String UPDATE = "/update";
    public static final String DELETE = "/delete";
    public static final String FINDALL = "/findall";
    public static final String FINDBYID = "/findbyid";
    public static final String FINDBYNAMEANDLASTNAME = "/findbynameandlastname";
    public static final String FINDBYUSERID = "/findbyuserid";
    public static final String FINDBYPOSTID = "/findbypostid";
    public static final String FINDBYCATEGORIESID = "/findbycategoriesid";
    public static final String FINDALLBYCONTENTCONTAINING = "/findallbycontentcontaining";
    public static final String FINDALLBYCATEGORIESCONTAINING = "/findallbycategoriescontaining";
    public static final String FINDALLBYPOSTCATEGORYNAME = "/findAllPostByCategoriesNameContaining";
    public static final String FINDALLPOSTORDERBYPUBLISHEDDATE = "/findAllPostOrderByPublisheDate";

}