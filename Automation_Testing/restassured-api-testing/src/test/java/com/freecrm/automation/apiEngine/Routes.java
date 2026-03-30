package com.freecrm.automation.apiEngine;

public class Routes {

    // Base segments
    private static final String PET = "/pet";
    private static final String STORE = "/store";
    private static final String USER = "/user";

    // ── Pet endpoints ──────────────────────────────────────────
    public static final String PET_CREATE         = PET;
    public static final String PET_UPDATE         = PET;
    public static final String PET_FIND_BY_STATUS = PET + "/findByStatus";
    public static final String PET_FIND_BY_TAGS   = PET + "/findByTags";
    public static final String PET_BY_ID          = PET + "/{petId}";
    public static final String PET_UPDATE_BY_ID   = PET + "/{petId}";
    public static final String PET_DELETE         = PET + "/{petId}";


    // ── Store endpoints ────────────────────────────────────────
    public static final String STORE_INVENTORY    = STORE + "/inventory";
    public static final String STORE_ORDER_PLACE  = STORE + "/order";
    public static final String STORE_ORDER_BY_ID  = STORE + "/order/{orderId}";
    public static final String STORE_ORDER_DELETE = STORE + "/order/{orderId}";

    // ── User endpoints ─────────────────────────────────────────
    public static final String USER_CREATE              = USER;
    public static final String USER_CREATE_WITH_LIST    = USER + "/createWithList";
    public static final String USER_CREATE_WITH_ARRAY   = USER + "/createWithList";
    public static final String USER_LOGIN               = USER + "/login";
    public static final String USER_LOGOUT              = USER + "/logout";
    public static final String USER_BY_USERNAME         = USER + "/{username}";
    public static final String USER_UPDATE_BY_USERNAME  = USER + "/{username}";
    public static final String USER_DELETE_BY_USERNAME  = USER + "/{username}";
}
