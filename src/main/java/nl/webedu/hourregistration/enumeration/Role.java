package nl.webedu.hourregistration.enumeration;

public enum Role {
    ADMIN(1), MANAGER(2), EMPLOYEE(3);
    private int index;

    Role(int index){this.index = index;}

    public int getIndex() {return index;}

}