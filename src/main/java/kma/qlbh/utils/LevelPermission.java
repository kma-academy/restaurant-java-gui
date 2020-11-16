package kma.qlbh.utils;

/**
 *
 * @author Tran Duc Cuong<clonebmn2itt@gmail.com>
 */
public enum LevelPermission {
    BANNED_PERMISSION(0),
    MEMBER_PERMISSION(1),
    ADMIN_PERMISSION(2);
    private int id;

    LevelPermission(int id) {
        this.id = id;
    }

    public static LevelPermission getById(int id) {
        for (LevelPermission e : values()) {
            if(e.id == id) return e;
        }
        return MEMBER_PERMISSION;
    }

    public int getId() {
        return id;
    }

}
