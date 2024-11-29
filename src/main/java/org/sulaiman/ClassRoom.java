package org.sulaiman;

public class ClassRoom {
    private int classRoomId;
    private String name;

    public ClassRoom(int classRoomId, String name) {
        this.classRoomId = classRoomId;
        this.name = name;
    }

    public int getClassRoomId() {
        return classRoomId;
    }

    public void setClassRoomId(int classRoomId) {
        this.classRoomId = classRoomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
